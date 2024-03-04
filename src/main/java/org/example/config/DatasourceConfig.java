package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.yml")
@EnableJpaRepositories(basePackages = "org.example.repository")
@EnableTransactionManagement
public class DatasourceConfig {
    private static final String ENTITY_PACKAGE = "org.example.model";

    @Value("${datasource.driver}")
    private String driverClassName;

    @Value("${datasource.url}")
    private String jdbcUrl;

    @Value("${datasource.user}")
    private String username;

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${hibernate.dialect-class}")
    private String dialect;

    @Value("${hibernate.show-sql}")
    private String showSql;

    @Value("${hibernate.hbm2ddl}")
    private String hbm2ddl;

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(maxPoolSize);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource hikariDataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(hikariDataSource);
        entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGE);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(jpaProperties());
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    public Properties jpaProperties() {
        Properties hiberProperties = new Properties();
        hiberProperties.put("hibernate.dialect", dialect);
        hiberProperties.put("hibernate.show_sql", showSql);
        hiberProperties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        return hiberProperties;
    }
}