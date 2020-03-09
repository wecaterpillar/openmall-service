package org.openmall.mall.sms.dao;

import org.openmall.mall.sms.model.SmsCouponProductCategoryRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券和商品分类关系自定义Dao
 */
public interface SmsCouponProductCategoryRelationDao {
    int insertList(@Param("list")List<SmsCouponProductCategoryRelation> productCategoryRelationList);
}
