package org.openmall.mall.portal.home.domain;

import lombok.Getter;
import lombok.Setter;
import org.openmall.mall.cms.model.CmsSubject;
import org.openmall.mall.pms.model.PmsBrand;
import org.openmall.mall.pms.model.PmsProduct;
import org.openmall.mall.sms.model.SmsHomeAdvertise;

import java.util.List;

/**
 * 首页内容返回信息封装
 */
@Getter
@Setter
public class HomeContentResult {

    //轮播广告
    private List<SmsHomeAdvertise> advertiseList;

    //楼层内容-广告/专题
    private List<HomeLayerContent> layerContents;

    //当前秒杀场次
    private HomeFlashPromotion homeFlashPromotion;
    //新品推荐
    private List<PmsProduct> newProductList;
    //人气推荐
    private List<PmsProduct> hotProductList;
    //推荐专题
    private List<CmsSubject> subjectList;
    //推荐品牌
    private List<PmsBrand> brandList;
}
