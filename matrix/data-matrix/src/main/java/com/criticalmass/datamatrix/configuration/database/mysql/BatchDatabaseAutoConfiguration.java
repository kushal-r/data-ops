package com.criticalmass.datamatrix.configuration.database.mysql;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableConfigurationProperties
@EnableJpaRepositories(
    entityManagerFactoryRef = "batchEntityManager", transactionManagerRef =
    "batchTransactionManager")
public class BatchDatabaseAutoConfiguration {

  /* ---------------- Fields ---------------- */
  private static final Logger log = LogManager.getLogger();

  @Resource
  private BatchDataSourceProperties batchDataSourceProperties;


  /* ---------------- Methods ---------------- */

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource batchDataSource() {
    return DataSourceBuilder.create().build();
  }


  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean batchEntityManager() {
    final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
        new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(batchDataSource());
    entityManagerFactoryBean.setPackagesToScan("com.criticalmass.datamatrix.entity.report");

    final HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
    entityManagerFactoryBean.setJpaVendorAdapter(hibernateAdapter);
    final Map<String, Object> hibernateProperties = new HashMap<String, Object>();
    hibernateProperties.put("hibernate.hbm2ddl.auto", batchDataSourceProperties.getDdlAuto());
    hibernateProperties.put("hibernate.dialect", batchDataSourceProperties.getDialect());
    hibernateProperties.put("hibernate.show-sql", batchDataSourceProperties.getShowSql());
    hibernateProperties
        .put("hibernate.naming-strategy", batchDataSourceProperties.getNamingStrategy());
    hibernateProperties
        .put("hibernate.generate_statistics", batchDataSourceProperties.getGenerateStatistics());
    entityManagerFactoryBean.setJpaPropertyMap(hibernateProperties);

    return entityManagerFactoryBean;
  }


  @Bean
  public PlatformTransactionManager batchTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(batchEntityManager().getObject());
    return transactionManager;
  }
}
