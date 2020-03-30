package org.openmall.mall.base.service;

import java.util.Map;

/**
 * 业务渠道服务API
 */
public interface ChannelService {

    /**
     * 获取渠道配置信息
     * @param channelId
     * @return
     */
    Map<String, String> getChannelConfig(String channelId);

}
