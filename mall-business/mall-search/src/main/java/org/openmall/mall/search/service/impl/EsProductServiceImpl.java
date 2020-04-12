package org.openmall.mall.search.service.impl;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.openmall.mall.search.dao.EsProductDao;
import org.openmall.mall.search.domain.EsProduct;
import org.openmall.mall.search.domain.EsProductRelatedInfo;
import org.openmall.mall.search.dto.AggVO;
import org.openmall.mall.search.dto.QueryVO;
import org.openmall.mall.search.repository.EsProductRepository;
import org.openmall.mall.search.service.EsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 商品搜索管理Service实现类
 */
@Service
public class EsProductServiceImpl implements EsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);

    @Autowired
    private EsProductDao productDao;

    @Autowired
    private EsProductRepository productRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public int importAll() {
        List<EsProduct> esProductList = productDao.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct result = null;
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            result = productRepository.save(esProduct);
        }
        return result;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }

    @Override
    public Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize,Integer sort) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
        //过滤
        if (brandId != null || productCategoryId != null) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (brandId != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandId", brandId));
            }
            if (productCategoryId != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("productCategoryId", productCategoryId));
            }
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }
        //搜索
        if (StringUtils.isEmpty(keyword)) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("subTitle", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        //排序
        if(sort==1){
            //按新品从新到旧
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        }else if(sort==2){
            //按销量从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("sale").order(SortOrder.DESC));
        }else if(sort==3){
            //按价格从低到高
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        }else if(sort==4){
            //按价格从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        }else{
            //按相关度
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        }
        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
        return productRepository.search(searchQuery);
    }

    @Override
    public Page<EsProduct> recommend(Long id, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            String keyword = esProduct.getName();
            Long brandId = esProduct.getBrandId();
            Long productCategoryId = esProduct.getProductCategoryId();
            //根据商品标题、品牌、分类进行搜索
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(8)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("subTitle", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("brandId", brandId),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("productCategoryId", productCategoryId),
                    ScoreFunctionBuilders.weightFactorFunction(3)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            //用于过滤掉相同的商品
            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
            boolQueryBuilder.mustNot(QueryBuilders.termQuery("id",id));
            //构建查询条件
            NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
            builder.withQuery(functionScoreQueryBuilder);
            builder.withFilter(boolQueryBuilder);
            builder.withPageable(pageable);
            NativeSearchQuery searchQuery = builder.build();
            LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
            return productRepository.search(searchQuery);
        }
        return new PageImpl<>(null);
    }

    @Override
    public EsProductRelatedInfo searchRelatedInfo(String keyword) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //搜索条件
        if(StringUtils.isEmpty(keyword)){
            builder.withQuery(QueryBuilders.matchAllQuery());
        }else{
            builder.withQuery(QueryBuilders.multiMatchQuery(keyword,"name","subTitle","keywords"));
        }
        //聚合搜索品牌名称
        builder.addAggregation(AggregationBuilders.terms("brandNames").field("brandName"));
        //集合搜索分类名称
        builder.addAggregation(AggregationBuilders.terms("productCategoryNames").field("productCategoryName"));
        //聚合搜索商品属性，去除type=1的属性
        AbstractAggregationBuilder aggregationBuilder = AggregationBuilders.nested("allAttrValues","attrValueList")
                .subAggregation(AggregationBuilders.filter("productAttrs",QueryBuilders.termQuery("attrValueList.type",1))
                .subAggregation(AggregationBuilders.terms("attrIds")
                        .field("attrValueList.productAttributeId")
                        .subAggregation(AggregationBuilders.terms("attrValues")
                                .field("attrValueList.value"))
                        .subAggregation(AggregationBuilders.terms("attrNames")
                                .field("attrValueList.name"))));
        builder.addAggregation(aggregationBuilder);
        NativeSearchQuery searchQuery = builder.build();
        return elasticsearchTemplate.query(searchQuery, response -> {
            LOGGER.info("DSL:{}",searchQuery.getQuery().toString());
            return convertProductRelatedInfo(response);
        });
    }

    /**
     * 将返回结果转换为对象
     */
    private EsProductRelatedInfo convertProductRelatedInfo(SearchResponse response) {
        EsProductRelatedInfo productRelatedInfo = new EsProductRelatedInfo();
        Map<String, Aggregation> aggregationMap = response.getAggregations().getAsMap();
        //设置品牌
        Aggregation brandNames = aggregationMap.get("brandNames");
        List<String> brandNameList = new ArrayList<>();
        for(int i = 0; i<((Terms) brandNames).getBuckets().size(); i++){
            brandNameList.add(((Terms) brandNames).getBuckets().get(i).getKeyAsString());
        }
        productRelatedInfo.setBrandNames(brandNameList);
        //设置分类
        Aggregation productCategoryNames = aggregationMap.get("productCategoryNames");
        List<String> productCategoryNameList = new ArrayList<>();
        for(int i=0;i<((Terms) productCategoryNames).getBuckets().size();i++){
            productCategoryNameList.add(((Terms) productCategoryNames).getBuckets().get(i).getKeyAsString());
        }
        productRelatedInfo.setProductCategoryNames(productCategoryNameList);
        //设置参数
        Aggregation productAttrs = aggregationMap.get("allAttrValues");
        List<LongTerms.Bucket> attrIds = ((LongTerms) ((InternalFilter) ((InternalNested) productAttrs).getProperty("productAttrs")).getProperty("attrIds")).getBuckets();
        List<EsProductRelatedInfo.ProductAttr> attrList = new ArrayList<>();
        for (Terms.Bucket attrId : attrIds) {
            EsProductRelatedInfo.ProductAttr attr = new EsProductRelatedInfo.ProductAttr();
            attr.setAttrId((Long) attrId.getKey());
            List<String> attrValueList = new ArrayList<>();
            List<StringTerms.Bucket> attrValues = ((StringTerms) attrId.getAggregations().get("attrValues")).getBuckets();
            List<StringTerms.Bucket> attrNames = ((StringTerms) attrId.getAggregations().get("attrNames")).getBuckets();
            for (Terms.Bucket attrValue : attrValues) {
                attrValueList.add(attrValue.getKeyAsString());
            }
            attr.setAttrValues(attrValueList);
            if(!CollectionUtils.isEmpty(attrNames)){
                String attrName = attrNames.get(0).getKeyAsString();
                attr.setAttrName(attrName);
            }
            attrList.add(attr);
        }
        productRelatedInfo.setProductAttrs(attrList);
        return productRelatedInfo;
    }

    // 商品分页查询
    @Override
    public Page<EsProduct> search(QueryVO query, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return productRepository.search(this.addFilters(query), pageable);
    }

    // 过滤条件
    private BoolQueryBuilder addFilters(QueryVO query) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        List<AggVO.AttrGroup> attrGroups = query.getAttrs();
        if (attrGroups != null) {
            // 多属性过滤查询，如（内存、颜色、屏幕尺寸、版本等等）
            for (AggVO.AttrGroup attrGroup : attrGroups) {
                BoolQueryBuilder attrBoolQuery = QueryBuilders.boolQuery();
                // 匹配商品规格参数名
                attrBoolQuery.filter(QueryBuilders.matchQuery(EsProduct.FieldName.ATTRS_NAME, attrGroup.getAttrName()));
                // 匹配商品规格参数值，这里多值匹配用【termsQuery】注意区分【termQuery】
                attrBoolQuery.filter(QueryBuilders.termsQuery(EsProduct.FieldName.ATTRS_VALUE, attrGroup.getAttrValues()));
                // 使用NestedQuery查询（嵌套对象的过滤查询）
                NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery(EsProduct.FieldName.ATTRS, attrBoolQuery, ScoreMode.None);
                boolQueryBuilder.filter(nestedQueryBuilder);
            }
        }
        if (query.getId() != null) {
            // 商品Id查询
            boolQueryBuilder.filter(QueryBuilders.matchQuery(EsProduct.FieldName.ID, query.getId()).operator(Operator.AND));
        }
        if (!query.getBrands().isEmpty()) {
            // 品牌查询
            boolQueryBuilder.filter(QueryBuilders.termsQuery(EsProduct.FieldName.BRAND, query.getBrands()));
        }
        if (query.getCategory() != null) {
            // 分类查询
            boolQueryBuilder.filter(QueryBuilders.matchQuery(EsProduct.FieldName.CATEGORY, query.getCategory()).operator(Operator.AND));
        }
        if (!StringUtils.isEmpty(query.getName())) {
            // 商品名称查询（这里暂时没做分词处理）
            boolQueryBuilder.filter(QueryBuilders.matchQuery(EsProduct.FieldName.NAME, query.getName()).operator(Operator.AND));
        }
        String keyword = query.getKeyword();
        if(!StringUtils.isEmpty(keyword)){
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery(EsProduct.FieldName.NAME, keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery(EsProduct.FieldName.SUBTITLE, keyword),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery(EsProduct.FieldName.KEYWORDS, keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery(EsProduct.FieldName.BRAND, keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery(EsProduct.FieldName.CATEGORY, keyword),
                    ScoreFunctionBuilders.weightFactorFunction(3)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            boolQueryBuilder.filter(functionScoreQueryBuilder);
        }
        // TODO price between
        return boolQueryBuilder;
    }


    // 筛选条件聚合查询接口
    @Override
    public AggVO agg(QueryVO query) {
        Pageable pageable = PageRequest.of(0, 10);
        //检索条件
        BoolQueryBuilder boolQueryBuilder = this.addFilters(query);
        final String BRAND_AGG = "brand_agg";
        final String CATEGORY_AGG = "category_agg";
        final String ATTR_AGG = "attr_agg";
        final String ATTR_NAME_AGG = ATTR_AGG + "_name";
        final String ATTR_VALUE_AGG = ATTR_AGG+"_value";
        //聚合条件

        // 品牌聚合（取命中最多的前500个品牌）
        TermsAggregationBuilder brandAggBuilder = AggregationBuilders.terms(BRAND_AGG).field(EsProduct.FieldName.BRAND).size(500);
        // 分类聚合（取命中最多的前20个）
        TermsAggregationBuilder categoryAggBuilder = AggregationBuilders.terms(CATEGORY_AGG).field(EsProduct.FieldName.CATEGORY).size(20);
        // 内置对象-规格聚合
        NestedAggregationBuilder attrAggBuilder = AggregationBuilders.nested(ATTR_AGG, EsProduct.FieldName.ATTRS);
        // 先聚合规格名称
        TermsAggregationBuilder attrNameAggBuilder = AggregationBuilders.terms(ATTR_NAME_AGG).field(EsProduct.FieldName.ATTRS_NAME);
        // 再将名称分组后聚合规格值（取命中最多的前7个规格，聚合分组规格后再在每个分组规格中取命中最多的前15个参数值）
        attrNameAggBuilder.subAggregation(AggregationBuilders.terms(ATTR_VALUE_AGG).field(EsProduct.FieldName.ATTRS_VALUE).size(15)).size(7);
        attrAggBuilder.subAggregation(attrNameAggBuilder);
        //构建查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder) // 过滤条件
                .addAggregation(brandAggBuilder) // 品牌聚合
                .addAggregation(categoryAggBuilder) // 分类聚合
                .addAggregation(attrAggBuilder) // 规格参数聚合
                .withPageable(pageable) // 分页
                .build();
        AggregatedPage<EsProduct> search = (AggregatedPage)productRepository.search(searchQuery);
        AggVO aggVO = new AggVO();
        // 符合条件的商品总数量
        aggVO.setTotalNum(search.getTotalElements());

        // 品牌聚合结果
        Terms brandAgg = (Terms)search.getAggregation(BRAND_AGG);
        for (Terms.Bucket bucket : brandAgg.getBuckets()) {
            String brandName = bucket.getKeyAsString();
            aggVO.getBrands().add(brandName);
        }

        // 分类聚合结果
        Terms categoryAgg = (Terms)search.getAggregation(CATEGORY_AGG);
        for (Terms.Bucket bucket : categoryAgg.getBuckets()) {
            long categoryId = bucket.getKeyAsNumber().longValue();
            aggVO.getCategories().add(categoryId);
        }

        // 规格参数聚合结果
        InternalNested attrNested = (InternalNested)search.getAggregation(ATTR_AGG);
        Map<String, Aggregation> aggregationMap = attrNested.getAggregations().asMap();
        Terms attrNameAgg = (Terms)aggregationMap.get(ATTR_NAME_AGG);
        for (Terms.Bucket bucket : attrNameAgg.getBuckets()) {
            String attrName = bucket.getKeyAsString();
            AggVO.AttrGroup attrGroup = new AggVO.AttrGroup();
            attrGroup.setAttrName(attrName);
            Terms attrValueAgg = (Terms)bucket.getAggregations().asMap().get(ATTR_VALUE_AGG);
            for (Terms.Bucket subBucket : attrValueAgg.getBuckets()) {
                String attrValue = subBucket.getKeyAsString();
                attrGroup.getAttrValues().add(attrValue);
            }
            aggVO.getAttrs().add(attrGroup);
        }
        search.getAggregation(CATEGORY_AGG);
        return aggVO;
    }
}
