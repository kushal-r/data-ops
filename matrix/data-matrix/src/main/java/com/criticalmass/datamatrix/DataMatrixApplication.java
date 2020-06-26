package com.criticalmass.datamatrix;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataMatrixApplication {

  //Set UTC time zone for the application
  @PostConstruct
  void started() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

/*  @Resource
  JobLauncher jobLauncher;

  @Resource
  JobOperator jobOperator;

  @Resource
  JobExplorer jobExplorer;

  @Resource(name = "sampleTestJob")
  Job job;*/

  public static void main(String[] args) {
    SpringApplication.run(DataMatrixApplication.class, args);
  }
  

/*    log.info(jobExplorer.getLastJobInstance(job.getName()));

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

    log.info(jobOperator.getStepExecutionSummaries(executionId));*/
/*    JobExecution execution =
        jobLauncher.run(
            job,
            new JobParametersBuilder().
                getNextJobParameters(job)
                .toJobParameters());*/

}
