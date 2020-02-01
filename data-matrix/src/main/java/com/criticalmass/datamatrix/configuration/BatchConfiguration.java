package com.criticalmass.datamatrix.configuration;

import javax.sql.DataSource;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
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

  @Autowired private DataSource dataSource;

  @Bean
  public DataSourceInitializer dataSourceInitializer() {

    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    databasePopulator.addScript(batchDropSchema);
    databasePopulator.addScript(batchCreateSchema);
    databasePopulator.setIgnoreFailedDrops(true);

    DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource);
    initializer.setDatabasePopulator(databasePopulator);

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
}
