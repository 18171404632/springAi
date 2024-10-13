package com.newkey.ai.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 *  数据源相关bean
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        enableDefaultTransactions = false,
        entityManagerFactoryRef = "entityManagerFactoryCur",//实体管理引用
        transactionManagerRef = "transactionManagerCur",//事务管理引用
        basePackages = {"com.newkey.ai.dao"}) //设置联机库数据源所应用到的包
public class DataSourceBeans {
    @Value("${spring.jpa.database.database-platform}")
    private String dialect;

    //注入渠道数据源
    @Autowired
    @Qualifier("newkeyAiDataSource")
    private DataSource newkeyAiDataSource;

    @Bean(name = "entityManagerFactoryCur")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryCur() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(newkeyAiDataSource);
        em.setPackagesToScan(new String[]{"com.newkey.ai.entity"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", dialect);
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "transactionManagerCur")
    public PlatformTransactionManager transactionManagerCur() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryCur().getObject());
        return transactionManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplateCur() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(newkeyAiDataSource);
        return jdbcTemplate;
    }
}
