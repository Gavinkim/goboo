package com.wooriworld.job.land;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LandJobConfig {
  private static final String LAND_JOB = "LandJob";
  private static final String LAND_STEP = "LandStep";

  private final JobBuilderFactory landJobBuilderFactory;
  private final StepBuilderFactory landStepBuilderFactory;

  @Bean(name = LAND_JOB)
  public Job landJob() {
    return landJobBuilderFactory.get(LAND_JOB)
        .start(landStep())
        .build();
  }

  @Bean(name = LAND_STEP)
  public Step landStep() {
    return landStepBuilderFactory.get(LAND_STEP)
        .tasklet(((contribution, chunkContext) -> {
          log.info(">>>>>>>>>>>>> LAND_STEP <<<<<<<<<<<<<<<");
          return RepeatStatus.FINISHED;
        }))
        .build();
  }
}
