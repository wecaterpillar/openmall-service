package org.openmall.mall.base.service;

import org.openmall.mall.base.model.Merchant;

/**
 * 商户服务API
 */
public interface MerchantService {

    /**
     * 获取平台默认商户
     * @return
     */
    public Merchant getDefaultMerchat();


}
