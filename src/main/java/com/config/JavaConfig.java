package com.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by sasha on 02.10.15.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@PropertySource({ "application.properties" })
@ComponentScan("com")
public class JavaConfig {
    @Autowired
    private Environment env;
//    @Bean
//    public SessionFactory sessionFactory(){
//        SessionFactory sessionFactory = new SessionFactory()
//    }
    @Bean
    public DataSource dataSource()
    {
        System.out.println(env.getProperty("jdbc.url"));
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
    @Bean
    public EntityManagerFactory entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(getEclipseLinkJpaVendorAdapter());
        Map<String, String> jpaProperties = new HashMap<String, String>();
        jpaProperties.put("eclipselink.weaving", "false");
        //jpaProperties.put("eclipselink.ddl-generation", "create-tables");
        //jpaProperties.put("javax.persistence.jdbc.driver","org.apache.derby.jdbc.EmbeddedDriver");
        jpaProperties.put("jpaDialect"," org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect");
        factory.setJpaPropertyMap(jpaProperties);

        factory.setPackagesToScan("com.domain");
        factory.setDataSource(dataSource());
        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        factory.setJpaDialect(new EclipseLinkJpaDialect());

        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        //txManager.setDataSource(dataSource());
        return txManager;
    }
    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
    private EclipseLinkJpaVendorAdapter getEclipseLinkJpaVendorAdapter(){
        System.out.println("EclipseLinkJpaVendorAdapter initialization ***************");

        EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        //vendorAdapter.setDatabasePlatform("org.eclipse.persistence.platform.database.MySQLPlatform");
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }


}
