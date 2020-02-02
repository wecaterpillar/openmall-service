package org.openmall.mall.portal.oms.domain;

import org.openmall.mall.oms.model.OmsOrder;
import org.openmall.mall.oms.model.OmsOrderItem;
import org.openmall.mall.oms.model.OmsOrder;
import org.openmall.mall.oms.model.OmsOrderItem;

import java.util.List;

/**
 * 包含订单商品信息的订单详情
 * Created by macro on 2018/9/4.
 */
public class OmsOrderDetail extends OmsOrder {
    private List<OmsOrderItem> orderItemList;

    public List<OmsOrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OmsOrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
