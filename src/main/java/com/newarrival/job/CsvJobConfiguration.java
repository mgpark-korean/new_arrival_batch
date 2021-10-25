
/*
 * Created by Mingi Park on 2021/10/25
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Dev Backend Team <mingi@bigin.io>, 2021/10/25
 */

package com.newarrival.job;

import com.newarrival.dto.ProductDto;
import javax.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;


/**
 * create on 2021/10/25. create by IntelliJ IDEA.
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
public class CsvJobConfiguration {

  private JobBuilderFactory jobBuilderFactory;
  private StepBuilderFactory stepBuilderFactory;
  private EntityManagerFactory entityManagerFactory;

  public CsvJobConfiguration(
      JobBuilderFactory jobBuilderFactory,
      StepBuilderFactory stepBuilderFactory,
      EntityManagerFactory entityManagerFactory) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.stepBuilderFactory = stepBuilderFactory;
    this.entityManagerFactory = entityManagerFactory;
  }

  @Bean
  public Job csvJob() throws Exception {
    return this.jobBuilderFactory.get("csvJob")
        .incrementer(new RunIdIncrementer())
        .start(this.productsCsvJob())
        .build();
  }

  @Bean
  public Step productsCsvJob() throws Exception {
    return this.stepBuilderFactory.get("productsCsvJob")
        .<ProductDto, ProductDto>chunk(20)
        .reader(productDbReader())
        .writer(productCSVWriter())
        .build();
  }

  private ItemWriter<? super ProductDto> productCSVWriter() throws Exception {
    DelimitedLineAggregator<ProductDto> lineAggregator = new DelimitedLineAggregator<>();
    lineAggregator.setDelimiter(",");

    BeanWrapperFieldExtractor<ProductDto> fieldExtractor = new BeanWrapperFieldExtractor<>();
    fieldExtractor.setNames(new String[]{
        "id", "name", "price", "thumbnail", "url"
    }); // field names
    lineAggregator.setFieldExtractor(fieldExtractor);

    FlatFileItemWriter<ProductDto> itemWriter = new FlatFileItemWriterBuilder<ProductDto>()
        .name("csvWriter")
        .encoding("UTF-8")
        .resource(new FileSystemResource("output/stussy.csv"))
        .lineAggregator(lineAggregator)
        .headerCallback(writer -> writer.write("상품번호,상품명,상품가격,상품이미지, 상품주소"))
        .append(true)
        .build();

    itemWriter.afterPropertiesSet();

    return itemWriter;
  }

  private ItemReader<? extends ProductDto> productDbReader() throws Exception {
    JpaCursorItemReader<ProductDto> itemReader = new JpaCursorItemReaderBuilder<ProductDto>()
        .name("JpaReader")
        .entityManagerFactory(entityManagerFactory)
        .queryString("select p from products p")
        .build();

    itemReader.afterPropertiesSet();
    return itemReader;
  }
}
