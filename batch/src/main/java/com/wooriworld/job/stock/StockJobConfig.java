package com.wooriworld.job.stock;

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
public class StockJobConfig {
  private static final String STOCK_JOB = "StockJob";
  private static final String STOCK_STEP = "StockStep";

  private final JobBuilderFactory landJobBuilderFactory;
  private final StepBuilderFactory landStepBuilderFactory;

  @Bean(name = STOCK_JOB)
  public Job stockJob() {
    return landJobBuilderFactory.get(STOCK_JOB)
        .start(stockStep())
        .build();
  }

  @Bean(name = STOCK_STEP)
  public Step stockStep() {
    return landStepBuilderFactory.get(STOCK_STEP)
        .tasklet(((contribution, chunkContext) -> {
          log.info(">>>>>>>>>>>>> STOCK_STEP <<<<<<<<<<<<<<<");
          return RepeatStatus.FINISHED;
        }))
        .build();
  }
}
