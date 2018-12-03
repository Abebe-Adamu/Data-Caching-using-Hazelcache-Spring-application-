package com.dickson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Created by Async
 */
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class Application {

  /**
   * Main method
   * @param args
   */
  public static void main(String[] args) {
    System.setProperty("applicationName", "hazelcache");
    System.setProperty("spring.application.name", "hazelcache" );
    SpringApplication.run(Application.class, args);
  }
}
