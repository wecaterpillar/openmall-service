package org.openmall.mall.base.service.impl;

import org.openmall.mall.base.model.Channel;
import org.openmall.mall.base.model.ChannelType;
import org.openmall.mall.base.model.Merchant;
import org.openmall.mall.base.service.MerchantService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MerchantServiceImpl implements MerchantService {
    /**
     * 获取平台默认商户
     *
     * @return
     */
    @Override
    public Merchant getDefaultMerchat() {
        return Merchant.getDefaultMerchant();
    }

    /**
     * 获取平台默认商店网站的配置
     *
     * @return
     */
    @Override
    public Map<String, Object> getDefaultEshopConfig() {
        Map<String, Object> mapConfig = new HashMap<>();
        // TODO 增加默认配置到配置文件中
        Merchant merchant = getDefaultMerchat();
        Channel channel = getDefaultEshopChannel(merchant);
        mapConfig.put("merchant", merchant);
        mapConfig.put("channel", channel);
        return mapConfig;
    }

    private Channel getDefaultEshopChannel(Merchant merchant){
        Channel channel = new Channel();
        // TODO 设置默认值
        channel.setMerchantId(merchant.getId());
        channel.setChannelType(ChannelType.ESHOP.getId());
        channel.setId(merchant.getId()*10000+channel.getChannelType()*1000+1);
        channel.setCurCode("JPY");
        channel.setLanguage("en");
        channel.setLanguages("en,ja,zh_TW");


        return channel;
    }
}
