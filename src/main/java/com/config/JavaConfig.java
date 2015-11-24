package com.config;

import com.application.ApplicationService;
import com.application.ApplicationServiceImpl;
import com.domain.StudentRepository;
import com.domain.StudentRepositoryImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.eclipse.persistence.sessions.factories.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


/**
 * Created by sasha on 02.10.15.
 */
@Configuration
@EnableTransactionManagement
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
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
    private EclipseLinkJpaVendorAdapter getEclipseLinkJpaVendorAdapter(){
        System.out.println("Roster 3 : EclipseLinkJpaVendorAdapter initialization ***************");

        EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.eclipse.persistence.platform.database.MySQLPlatform");
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setJpaVendorAdapter(getEclipseLinkJpaVendorAdapter());

        entityManager.setPersistenceXmlLocation("./resources/persistence.xml");
//        entityManager.setJpaDialect(new EclipseLinkJpaDialect());
        return entityManager;
    }

}
