package com.newarrival;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class NewArrivalBatchApplication {

  public static void main(String[] args) {
    SpringApplication.run(NewArrivalBatchApplication.class, args);
  }

}
