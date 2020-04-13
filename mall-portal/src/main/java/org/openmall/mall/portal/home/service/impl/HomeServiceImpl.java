package org.openmall.mall.portal.home.service.impl;

import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.lang.Strings;
import org.openmall.mall.base.service.MerchantService;
import org.openmall.mall.cms.mapper.CmsHelpMapper;
import org.openmall.mall.cms.mapper.CmsSubjectMapper;
import org.openmall.mall.cms.mapper.CmsTopicMapper;
import org.openmall.mall.cms.model.*;
import org.openmall.mall.pms.mapper.PmsProductCategoryMapper;
import org.openmall.mall.pms.mapper.PmsProductMapper;
import org.openmall.mall.pms.model.PmsProduct;
import org.openmall.mall.pms.model.PmsProductCategory;
import org.openmall.mall.pms.model.PmsProductCategoryExample;
import org.openmall.mall.pms.model.PmsProductExample;
import org.openmall.mall.portal.home.dao.HomeDao;
import org.openmall.mall.portal.home.domain.FlashPromotionProduct;
import org.openmall.mall.portal.home.domain.HomeContentResult;
import org.openmall.mall.portal.home.domain.HomeFlashPromotion;
import org.openmall.mall.portal.home.domain.HomeLayerContent;
import org.openmall.mall.portal.home.service.HomeService;
import org.openmall.mall.portal.util.DateUtil;
import org.openmall.mall.sms.mapper.SmsFlashPromotionMapper;
import org.openmall.mall.sms.mapper.SmsFlashPromotionSessionMapper;
import org.openmall.mall.sms.mapper.SmsHomeAdvertiseMapper;
import org.openmall.mall.sms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 首页内容管理Service实现类
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;
    @Autowired
    private HomeDao homeDao;
    @Autowired
    private SmsFlashPromotionMapper flashPromotionMapper;
    @Autowired
    private SmsFlashPromotionSessionMapper promotionSessionMapper;
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

    @Autowired
    private CmsSubjectMapper subjectMapper;
    @Autowired
    private CmsTopicMapper topicMapper;
    @Autowired
    private CmsHelpMapper helpMapper;

    @Autowired
    private MerchantService merchantService;


    public Map<String, Object> getChannelConfig(){
        Map<String, Object> mapConfig = merchantService.getDefaultEshopConfig();
        return mapConfig;
    }
    @Override
    public HomeContentResult content() {
        return content(null);
    }
    @Override
    public HomeContentResult content(Map<String, Object> webConfig) {
        HomeContentResult result = new HomeContentResult();
        //获取首页广告
        result.setAdvertiseList(getHomeAdvertiseList());

        if(webConfig!=null && webConfig.containsKey("layers")){
            loadLayerContent(result, webConfig);
        }

        //获取推荐品牌
        //result.setBrandList(homeDao.getRecommendBrandList(0,4));
        //获取秒杀信息
        //result.setHomeFlashPromotion(getHomeFlashPromotion());(
        //获取新品推荐
        result.setNewProductList(homeDao.getNewProductList(0,4));
        //获取人气推荐
        result.setHotProductList(homeDao.getHotProductList(0,4));
        //获取推荐专题
        result.setSubjectList(homeDao.getRecommendSubjectList(0,4));
        return result;
    }

    private void loadLayerContent(HomeContentResult result, Map<String, Object> webConfig){
        if(webConfig==null || !webConfig.containsKey("layers")){
            return;
        }
        List<HomeLayerContent> layers = (List)webConfig.get("layers");
        // TODO load layer's data
        for(HomeLayerContent layer: layers){
            int type = layer.getType();
            //楼层类型，1-专题分类，2-话题分类，3-帮助分类，4-广告位，10-秒杀活动，11-新品推荐，12-人气推荐，13-品牌推荐，14-专题推荐，15-优选专区
            if(type==1){
                //1-专题分类
                List listData = this.getSubjectList(Long.valueOf(layer.getRefIds()), layer.getCount(), 1);
                layer.setData(listData);
            }else if(type==2){
                //2-话题分类
                List listData = this.getTopicList(Long.valueOf(layer.getRefIds()), layer.getCount(), 1);
                layer.setData(listData);
            }else if(type==3){
                //3-帮助分类
                List listData = this.getHelpList(Long.valueOf(layer.getRefIds()), layer.getCount(), 1);
                layer.setData(listData);
            }else if(type==4){
                //4-广告位
                String[] ids = Strings.split(layer.getRefIds(), ",");
                if(ids!=null){
                    List listData = new ArrayList();
                    for(String id: ids){
                        SmsHomeAdvertise ad = this.advertiseMapper.selectByPrimaryKey(Long.valueOf(id));
                        if(ad!=null){
                            listData.add(ad);
                        }
                    }
                    layer.setData(listData);
                }
            }else if(type==11){

            }else if(type==12){
            }else if(type==13){
            }else if(type==14){
            }else if(type==15){

            }
        }
        result.setLayerContents(layers);
    }

    @Override
    public List<PmsProduct> recommendProductList(Integer pageSize, Integer pageNum) {
        // TODO: 2019/1/29 暂时默认推荐所有商品
        PageHelper.startPage(pageNum,pageSize);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria()
                .andDeleteStatusEqualTo(0)
                .andPublishStatusEqualTo(1);
        return productMapper.selectByExample(example);
    }

    @Override
    public List<PmsProductCategory> getProductCateList(Long parentId) {
        // TODO 增加首页导航大类展开所需要的内容，包含所关联的专题
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.createCriteria()
                .andShowStatusEqualTo(1)
                .andParentIdEqualTo(parentId);
        example.setOrderByClause("sort desc");
        return productCategoryMapper.selectByExample(example);
    }

    @Override
    public List<CmsSubject> getSubjectList(Long cateId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        CmsSubjectExample example = new CmsSubjectExample();
        CmsSubjectExample.Criteria criteria = example.createCriteria();
        criteria.andShowStatusEqualTo(1);
        if(cateId!=null){
            criteria.andCategoryIdEqualTo(cateId);
        }
        return subjectMapper.selectByExample(example);
    }

    @Override
    public List<CmsTopic> getTopicList(Long cateId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        CmsTopicExample example = new CmsTopicExample();
        CmsTopicExample.Criteria criteria = example.createCriteria();
        //criteria.andShowStatusEqualTo(1);
        criteria.andStartTimeLessThanOrEqualTo(new Date());
        criteria.andEndTimeGreaterThanOrEqualTo(new Date());
        if(cateId!=null){
            criteria.andCategoryIdEqualTo(cateId);
        }
        return topicMapper.selectByExample(example);
    }

    @Override
    public List<CmsHelp> getHelpList(Long cateId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        CmsHelpExample example = new CmsHelpExample();
        CmsHelpExample.Criteria criteria = example.createCriteria();
        criteria.andShowStatusEqualTo(1);
        if(cateId!=null){
            criteria.andCategoryIdEqualTo(cateId);
        }
        return helpMapper.selectByExample(example);
    }

    private HomeFlashPromotion getHomeFlashPromotion() {
        HomeFlashPromotion homeFlashPromotion = new HomeFlashPromotion();
        //获取当前秒杀活动
        Date now = new Date();
        SmsFlashPromotion flashPromotion = getFlashPromotion(now);
        if (flashPromotion != null) {
            //获取当前秒杀场次
            SmsFlashPromotionSession flashPromotionSession = getFlashPromotionSession(now);
            if (flashPromotionSession != null) {
                homeFlashPromotion.setStartTime(flashPromotionSession.getStartTime());
                homeFlashPromotion.setEndTime(flashPromotionSession.getEndTime());
                //获取下一个秒杀场次
                SmsFlashPromotionSession nextSession = getNextFlashPromotionSession(homeFlashPromotion.getStartTime());
                if(nextSession!=null){
                    homeFlashPromotion.setNextStartTime(nextSession.getStartTime());
                    homeFlashPromotion.setNextEndTime(nextSession.getEndTime());
                }
                //获取秒杀商品
                List<FlashPromotionProduct> flashProductList = homeDao.getFlashProductList(flashPromotion.getId(), flashPromotionSession.getId());
                homeFlashPromotion.setProductList(flashProductList);
            }
        }
        return homeFlashPromotion;
    }

    //获取下一个场次信息
    private SmsFlashPromotionSession getNextFlashPromotionSession(Date date) {
        SmsFlashPromotionSessionExample sessionExample = new SmsFlashPromotionSessionExample();
        sessionExample.createCriteria()
                .andStartTimeGreaterThan(date);
        sessionExample.setOrderByClause("start_time asc");
        List<SmsFlashPromotionSession> promotionSessionList = promotionSessionMapper.selectByExample(sessionExample);
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }

    private List<SmsHomeAdvertise> getHomeAdvertiseList() {
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.createCriteria().andTypeEqualTo(1).andStatusEqualTo(1);
        example.setOrderByClause("sort desc");
        return advertiseMapper.selectByExample(example);
    }

    //根据时间获取秒杀活动
    private SmsFlashPromotion getFlashPromotion(Date date) {
        Date currDate = DateUtil.getDate(date);
        SmsFlashPromotionExample example = new SmsFlashPromotionExample();
        example.createCriteria()
                .andStatusEqualTo(1)
                .andStartDateLessThanOrEqualTo(currDate)
                .andEndDateGreaterThanOrEqualTo(currDate);
        List<SmsFlashPromotion> flashPromotionList = flashPromotionMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(flashPromotionList)) {
            return flashPromotionList.get(0);
        }
        return null;
    }

    //根据时间获取秒杀场次
    private SmsFlashPromotionSession getFlashPromotionSession(Date date) {
        Date currTime = DateUtil.getTime(date);
        SmsFlashPromotionSessionExample sessionExample = new SmsFlashPromotionSessionExample();
        sessionExample.createCriteria()
                .andStartTimeLessThanOrEqualTo(currTime)
                .andEndTimeGreaterThanOrEqualTo(currTime);
        List<SmsFlashPromotionSession> promotionSessionList = promotionSessionMapper.selectByExample(sessionExample);
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }
}
