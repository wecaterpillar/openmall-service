package org.openmall.mall.base.service;

import org.openmall.mall.base.model.Merchant;

import java.util.Map;

/**
 * 商户服务API
 */
public interface MerchantService {

    /**
     * 获取平台默认商户
     * @return
     */
    public Merchant getDefaultMerchat();


    /**
     * 获取平台默认商店网站的配置
     * @return
     */
    Map<String, Object> getDefaultEshopConfig();
}
