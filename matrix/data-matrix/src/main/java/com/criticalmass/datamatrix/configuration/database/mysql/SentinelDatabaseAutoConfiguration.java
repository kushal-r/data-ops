package com.criticalmass.datamatrix.configuration.database.mysql;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Date: 13/06/20
 *
 * @author Kushal Roy
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.criticalmass.datamatrix.repository.sentinel",
    entityManagerFactoryRef = "sentinelEntityManager", transactionManagerRef =
    "sentinelTransactionManager")
public class SentinelDatabaseAutoConfiguration {

  /* ---------------- Fields ---------------- */
  private static final Logger log = LogManager.getLogger();

  @Resource
  private SentinelDataSourceProperties sentinelDataSourceProperties;


  /* ---------------- Methods ---------------- */

  @Bean
  @ConfigurationProperties(prefix = "spring.sentinel-datasource")
  public DataSource sentinelDataSource() {
    return DataSourceBuilder.create().build();
  }


  @Bean
  public LocalContainerEntityManagerFactoryBean sentinelEntityManager() {
    final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
        new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(sentinelDataSource());
    entityManagerFactoryBean.setPackagesToScan("com.criticalmass.datamatrix.entity.sentinel");

    final HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
    entityManagerFactoryBean.setJpaVendorAdapter(hibernateAdapter);
    final Map<String, Object> hibernateProperties = new HashMap<String, Object>();
    hibernateProperties.put("hibernate.hbm2ddl.auto", sentinelDataSourceProperties.getDdlAuto());
    hibernateProperties.put("hibernate.dialect", sentinelDataSourceProperties.getDialect());
    hibernateProperties.put("hibernate.show-sql", sentinelDataSourceProperties.getShowSql());
    hibernateProperties
        .put("hibernate.naming-strategy", sentinelDataSourceProperties.getNamingStrategy());
    hibernateProperties
        .put("hibernate.generate_statistics", sentinelDataSourceProperties.getGenerateStatistics());

    entityManagerFactoryBean.setJpaPropertyMap(hibernateProperties);

    return entityManagerFactoryBean;
  }


  @Bean
  public PlatformTransactionManager sentinelTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(sentinelEntityManager().getObject());
    return transactionManager;
  }
}
