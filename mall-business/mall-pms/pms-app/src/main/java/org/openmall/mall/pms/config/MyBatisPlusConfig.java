package org.openmall.mall.pms.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置
 */
@Configuration
@MapperScan({"org.openmall.mall.**.mapper","org.openmall.mall.**.dao"})
public class MyBatisPlusConfig {
}
