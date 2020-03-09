package org.openmall.mall.oms.dao;

import org.openmall.mall.oms.model.OmsOrderOperateHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单操作记录自定义Dao
 */
public interface OmsOrderOperateHistoryDao {
    int insertList(@Param("list") List<OmsOrderOperateHistory> orderOperateHistoryList);
}
