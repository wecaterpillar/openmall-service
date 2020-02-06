package org.openmall.mall.ums.dao;

import org.openmall.mall.ums.domain.SmsCouponHistoryDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员优惠券领取历史自定义Dao
 */
public interface SmsCouponHistoryDao {
    List<SmsCouponHistoryDetail> getDetailList(@Param("memberId") Long memberId);
}
