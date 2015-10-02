package com.config;

import com.application.ApplicationService;
import com.application.ApplicationServiceImpl;
import com.domain.StudentRepository;
import com.domain.StudentRepositoryImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 * Created by sasha on 02.10.15.
 */
@Configuration
@PropertySource({ "application.properties" })
public class JavaConfig {
    @Autowired
    private Environment env;
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
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
    @Bean
    public ApplicationService applicationService(){
        return new ApplicationServiceImpl(repository());
    }
    @Bean
    public StudentRepository repository(){
        return new StudentRepositoryImpl();
    }

    @Bean
    @Profile("test")
    public DataSource testDataSource(){
        return null;
    }
}
