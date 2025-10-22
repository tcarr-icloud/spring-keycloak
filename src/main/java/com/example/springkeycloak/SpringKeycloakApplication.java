package com.example.springkeycloak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKeycloakApplication {
  private static final Logger log = LoggerFactory.getLogger(SpringKeycloakApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(SpringKeycloakApplication.class, args);
  }
}
