package com.criticalmass.datamatrix.configuration.database.postgres;

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
@EnableJpaRepositories(basePackages = "com.criticalmass.datamatrix.entity.matrix",
    entityManagerFactoryRef = "matrixEntityManager", transactionManagerRef =
    "matrixTransactionManager")
public class MatrixDatabaseAutoConfiguration {

  /* ---------------- Fields ---------------- */

  private static final Logger log = LogManager.getLogger();

  @Resource
  private DataMatrixDataSourceProperties matrixDataSourceProperties;


  /* ---------------- Methods ---------------- */

  @Bean
  @ConfigurationProperties(prefix = "spring.matrix-datasource")
  public DataSource matrixDataSource() {
    return DataSourceBuilder.create().build();
  }


  @Bean
  public LocalContainerEntityManagerFactoryBean matrixEntityManager() {
    final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
        new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(matrixDataSource());
    entityManagerFactoryBean.setPackagesToScan("com.criticalmass.datamatrix.entity.stock");

    final HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
    entityManagerFactoryBean.setJpaVendorAdapter(hibernateAdapter);
    final Map<String, Object> hibernateProperties = new HashMap<String, Object>();
    hibernateProperties.put("hibernate.hbm2ddl.auto", matrixDataSourceProperties.getDdlAuto());
    hibernateProperties.put("hibernate.dialect", matrixDataSourceProperties.getDialect());
    hibernateProperties.put("hibernate.show-sql", matrixDataSourceProperties.getShowSql());
    hibernateProperties
        .put("hibernate.naming-strategy", matrixDataSourceProperties.getNamingStrategy());
    hibernateProperties
        .put("hibernate.generate_statistics", matrixDataSourceProperties.getGenerateStatistics());

    entityManagerFactoryBean.setJpaPropertyMap(hibernateProperties);

    return entityManagerFactoryBean;
  }


  @Bean
  public PlatformTransactionManager matrixTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(matrixEntityManager().getObject());
    return transactionManager;
  }
}
