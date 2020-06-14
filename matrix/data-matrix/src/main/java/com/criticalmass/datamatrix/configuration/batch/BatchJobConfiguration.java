package com.criticalmass.datamatrix.configuration.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.ReferenceJobFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kushal
 */
@Configuration
public class BatchJobConfiguration {

  private static final Logger log = LogManager.getLogger();

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private JobRegistry jobRegistry;

  @Bean(value = "sampleTestJob")
  public Job sampleTestJob() throws DuplicateJobException {

    Job sampleTestJob =
        jobBuilderFactory
            .get("sampleTestJob")
            .incrementer(new RunIdIncrementer())
            .start(sampleTaskletStep())
            .build();

    ReferenceJobFactory referenceJobFactory = new ReferenceJobFactory(sampleTestJob);
    jobRegistry.register(referenceJobFactory);

    return sampleTestJob;
  }

  @Bean
  public Step sampleTaskletStep() {
    return stepBuilderFactory.get("sampleTaskletStep").tasklet(sampleTasklet()).build();
  }

  @Bean
  public Tasklet sampleTasklet() {
    log.info("Hello");
    return (contribution, chunkContext) -> {
      return RepeatStatus.FINISHED;
    };
  }
}
