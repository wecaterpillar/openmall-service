package org.openmall.mall.base.service.impl;

import org.openmall.mall.base.service.ChannelService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChannelServivceImpl implements ChannelService {
    /**
     * 获取渠道配置信息
     *
     * @param channelId
     * @return
     */
    @Override
    public Map<String, String> getChannelConfig(String channelId) {
        return null;
    }
}
