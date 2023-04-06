package com.promineotech.jeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * Initiates a Spring boot application, runs through Tomcat.
 */
@SpringBootApplication
public class JeepSales {

  public static void main(String[] args) {
    SpringApplication.run(JeepSales.class, args);
    
    // For ease of use.
    // http://localhost:8080/swagger-ui.html
  }

}
