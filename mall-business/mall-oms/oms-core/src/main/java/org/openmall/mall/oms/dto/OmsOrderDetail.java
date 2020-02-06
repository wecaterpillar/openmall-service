package org.openmall.mall.oms.dto;

import org.openmall.mall.oms.model.OmsOrder;
import org.openmall.mall.oms.model.OmsOrderItem;
import org.openmall.mall.oms.model.OmsOrderOperateHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单详情信息
 */
public class OmsOrderDetail extends OmsOrder {
    @Getter
    @Setter
    private List<OmsOrderItem> orderItemList;
    @Getter
    @Setter
    private List<OmsOrderOperateHistory> historyList;
}
