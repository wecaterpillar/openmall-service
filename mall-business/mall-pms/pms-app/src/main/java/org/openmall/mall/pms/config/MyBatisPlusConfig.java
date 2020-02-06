package org.openmall.mall.pms.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置
 */
@Configuration
@MapperScan({"org.openmall.mall.pms.mapper","org.openmall.mall.pms.dao",
        "org.openmall.mall.cms.mapper","org.openmall.mall.cms.dao"})
public class MyBatisPlusConfig {
}
