package org.openmall.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置类
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"org.openmall.mall.mapper","org.openmall.mall.dao","org.openmall.mall.*.mapper","org.openmall.mall.*.dao"})
public class MyBatisPlusConfig {
}
