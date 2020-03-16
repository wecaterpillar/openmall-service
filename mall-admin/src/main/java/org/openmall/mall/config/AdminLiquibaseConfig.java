package org.openmall.mall.config;

import org.openmall.mall.db.config.DefaultLiquibaseConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminLiquibaseConfig extends DefaultLiquibaseConfiguration {

    @Override
    public void afterPropertiesSet() throws Exception {
        checkLiquibaseModules();
    }
}

