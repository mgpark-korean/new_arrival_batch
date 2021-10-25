
/*
 * Created by Mingi Park on 2021/10/24
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Dev Backend Team <mingi@bigin.io>, 2021/10/24
 */

package com.newarrival.job;

import com.newarrival.crawler.StussyProductCrawler;
import com.newarrival.dto.ProductDto;
import com.newarrival.reader.ProductCrawlingReader;
import javax.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * create on 2021/10/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Mingi Park
 * @version 1.0
 * @see
 * @since 1.0
 */
@Configuration
@Slf4j
public class ProductCrawlingConfiguration {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final EntityManagerFactory entityManagerFactory;

  public ProductCrawlingConfiguration(
      JobBuilderFactory jobBuilderFactory,
      StepBuilderFactory stepBuilderFactory,
      EntityManagerFactory entityManagerFactory) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.stepBuilderFactory = stepBuilderFactory;
    this.entityManagerFactory = entityManagerFactory;
  }

  @Bean
  public Job crawlingJob() throws Exception {
    return this.jobBuilderFactory.get("crawlingJob")
        .incrementer(new RunIdIncrementer())
        .start(this.stussyCrawlingStep())
        .build();
  }

  @Bean
  public Step stussyCrawlingStep() throws Exception {
    return this.stepBuilderFactory.get("stussyCrawlingStep")
        .<ProductDto, ProductDto>chunk(20)
        .reader(stussyCrawingWriter())
        .writer(stussyDBWriter())
        .build();
  }

  private ItemWriter<? super ProductDto> stussyDBWriter() throws Exception {
    JpaItemWriter<ProductDto> itemWriter = new JpaItemWriterBuilder<ProductDto>()
        .entityManagerFactory(entityManagerFactory)
        .build();
    itemWriter.afterPropertiesSet();
    return itemWriter;
  }

  public ProductCrawlingReader<ProductDto> stussyCrawingWriter() {
    return new ProductCrawlingReader(new StussyProductCrawler());
  }
}
