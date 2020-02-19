package com.criticalmass.datamatrix;

import java.time.LocalDateTime;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.support.PropertiesConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataMatrixApplication implements CommandLineRunner {

  private static final Logger log = LogManager.getLogger();

  @Resource JobLauncher jobLauncher;

  @Resource JobOperator jobOperator;

  @Resource JobExplorer jobExplorer;

  @Resource(name = "sampleTestJob")
  Job job;

  public static void main(String[] args) {
    SpringApplication.run(DataMatrixApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    log.info(jobExplorer.getLastJobInstance(job.getName()));

    long executionId =
        jobOperator.start(
            job.getName(),
            PropertiesConverter.propertiesToString(
                new JobParametersBuilder()
                    .addLong("timestamp", (long) LocalDateTime.now().getSecond())
                    .toJobParameters()
                    .toProperties()));
    // jobOperator.startNextInstance(job.getName());
    log.info("exec " + executionId);

    log.info(jobOperator.getStepExecutionSummaries(executionId));
    /*        JobExecution execution =
    jobLauncher.run(
        job,
        new JobParametersBuilder().
        getNextJobParameters(job)
            .toJobParameters());*/
  }
}
