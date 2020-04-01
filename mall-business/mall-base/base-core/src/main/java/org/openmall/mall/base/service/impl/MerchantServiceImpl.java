package org.openmall.mall.base.service.impl;

import org.openmall.mall.base.model.Channel;
import org.openmall.mall.base.model.ChannelType;
import org.openmall.mall.base.model.Merchant;
import org.openmall.mall.base.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@EnableConfigurationProperties(DefaultMerchant.class)
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private  DefaultMerchant defaultMerchant;
    /**
     * 获取平台默认商户
     *
     * @return
     */
    @Override
    public Merchant getDefaultMerchat() {
        return this.defaultMerchant;
    }

    /**
     * 获取平台默认商店网站的配置
     *
     * @return
     */
    @Override
    public Map<String, Object> getDefaultEshopConfig() {
        Map<String, Object> mapConfig = new HashMap<>();
        Merchant merchant = getDefaultMerchat();
        Channel channel = getDefaultEshopChannel(merchant);
        mapConfig.put("merchant", merchant);
        mapConfig.put("channel", channel);
        return mapConfig;
    }

    private Channel getDefaultEshopChannel(Merchant merchant){
        Channel channel = null;
        if(merchant!=null && merchant.getChanneles()!=null && !merchant.getChanneles().isEmpty()){
            for(Channel c1: merchant.getChanneles()){
                if(ChannelType.ESHOP.getId() == c1.getType() && c1.isEnable()){
                    channel = c1;
                    break;
                }
            }
        }
        if(channel==null){
            channel = new Channel();
            channel.setMerchantId(merchant.getId());
            channel.setType(ChannelType.ESHOP.getId());
            channel.setId(merchant.getId()*10000+channel.getType()*1000+1);
            channel.setCurCode("JPY");
            channel.setLanguage("en");
            channel.setLanguages("en,ja,zh_TW");
        }
        return channel;
    }
}
