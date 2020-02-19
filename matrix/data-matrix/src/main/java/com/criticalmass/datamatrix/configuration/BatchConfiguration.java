package com.criticalmass.datamatrix.configuration;

import javax.sql.DataSource;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

/** @author kushal */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Value("classpath:org/springframework/batch/core/schema-drop-mysql.sql")
  private Resource batchDropSchema;

  @Value("classpath:org/springframework/batch/core/schema-mysql.sql")
  private Resource batchCreateSchema;

  @Value("${spring.batch.initializeDB}")
  private boolean isInitializerEnabled;

  @Autowired private DataSource dataSource;

  // @Autowired JobExplorer jobExplorer;

  @Autowired JobRegistry jobRegistry;

  @Bean
  public DataSourceInitializer dataSourceInitializer() {

    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    databasePopulator.addScript(batchDropSchema);
    databasePopulator.addScript(batchCreateSchema);
    databasePopulator.setIgnoreFailedDrops(true);

    DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource);
    initializer.setDatabasePopulator(databasePopulator);
    initializer.setEnabled(isInitializerEnabled);

    return initializer;
  }

  private JobRepository getJobRepository() throws Exception {

    JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
    factory.setDataSource(dataSource);
    factory.setTransactionManager(getTransactionManager());
    factory.afterPropertiesSet();
    return (JobRepository) factory.getObject();
  }

  private PlatformTransactionManager getTransactionManager() {

    return new ResourcelessTransactionManager();
  }

  public JobLauncher getJobLauncher() throws Exception {

    SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
    jobLauncher.setJobRepository(getJobRepository());
    jobLauncher.afterPropertiesSet();
    return jobLauncher;
  }

  @Bean
  public JobExplorer getJobExplorer() throws Exception {

    final JobExplorerFactoryBean bean = new JobExplorerFactoryBean();
    bean.setDataSource(dataSource);
    bean.setJdbcOperations(new JdbcTemplate(dataSource));
    bean.afterPropertiesSet();
    return bean.getObject();
  }

  @Bean
  public JobOperator getJobOperator() throws Exception {

    SimpleJobOperator jobOperator = new SimpleJobOperator();
    jobOperator.setJobExplorer(getJobExplorer());
    jobOperator.setJobLauncher(getJobLauncher());
    jobOperator.setJobRegistry(jobRegistry);
    jobOperator.setJobRepository(getJobRepository());
    return jobOperator;
  }
}
