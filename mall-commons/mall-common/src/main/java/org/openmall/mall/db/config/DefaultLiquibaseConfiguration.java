package org.openmall.mall.db.config;

import liquibase.integration.spring.SpringLiquibase;
import liquibase.util.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.util.List;

public class DefaultLiquibaseConfiguration implements BeanFactoryAware, InitializingBean {

    private BeanFactory beanFactory;

    @Autowired(required = false)
    private DataSource dataSource;

    @Value("${liquibase.modules}")
    private String liquiModules;


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (beanFactory instanceof DefaultListableBeanFactory) {
            this.beanFactory = beanFactory;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        checkLiquibaseModules();
    }

    protected void checkLiquibaseModules(){
        if (StringUtils.isEmpty(liquiModules)) {
            return;
        }
        List<String> modules = StringUtils.splitAndTrim(liquiModules, ",");
        for (String module : modules) {
            SpringLiquibase liquibase = liquibase(module);
        }
    }

    protected SpringLiquibase liquibase(String module) {
        String changelogPath = "classpath*:liquibase/" + module.replaceAll("-", "/").replaceAll("_", "/") + "/master.yaml";
        try{
            // TODO 检查如何确认配置文件存在
            Resource r = new DefaultResourceLoader().getResource(changelogPath);
            if(r==null || !r.exists()){
                return null;
            }
        }catch (Throwable t){
            t.printStackTrace();
            return null;
        }

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(changelogPath);
        liquibase.setDataSource(dataSource);
        liquibase.setShouldRun(true);
        liquibase.setResourceLoader(new DefaultResourceLoader());
        String moduleTable = module.replaceAll("-", "_").replaceAll("/", "_");
        liquibase.setDatabaseChangeLogTable("changelog_" + moduleTable);
        liquibase.setDatabaseChangeLogLockTable("changelog_" + moduleTable + "_lock");


        if (this.beanFactory != null) {
            ((DefaultListableBeanFactory) this.beanFactory).registerSingleton("liquibase" + StringUtils.upperCaseFirst(module.replaceAll("/", "")), liquibase);
        }

        return liquibase;
    }


}
