package org.openmall.mall.base.service.impl;

import org.openmall.mall.base.model.Merchant;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default-merchant")
public class DefaultMerchant extends Merchant {

    public DefaultMerchant(){
        super(1000L, "1000","mall", true, true, false);
    }

}
