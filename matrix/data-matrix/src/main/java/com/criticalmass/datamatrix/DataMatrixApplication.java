package com.criticalmass.datamatrix;

import com.criticalmass.datamatrix.repository.sentinel.CustomerRepository;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Transactional("sentinelTransactionManager")
public class DataMatrixApplication implements CommandLineRunner {

  private static final Logger log = LogManager.getLogger();

/*  @Resource
  JobLauncher jobLauncher;

  @Resource
  JobOperator jobOperator;

  @Resource
  JobExplorer jobExplorer;

  @Resource(name = "sampleTestJob")
  Job job;*/

  @Resource
  private CustomerRepository customerRepository;

  public static void main(String[] args) {
    SpringApplication.run(DataMatrixApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

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
}
