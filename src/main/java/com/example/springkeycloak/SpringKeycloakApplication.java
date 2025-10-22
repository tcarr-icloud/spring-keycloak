package com.example.springkeycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Set the following environment variables:
 * KEYCLOAK_AUTH-SERVER-URL=http://localhost:9090;
 * KEYCLOAK_REALM=demo;
 * SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER-URI=http://localhost:9090/realms/demo;
 * SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_JWK-SET-URI=http://localhost:9090/realms/demo/protocol/openid-connect/certs;
 * SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/spring-keycloak;
 * SPRING_DATASOURCE_USERNAME=postgres;
 * SPRING_DATASOURCE_PASSWORD=password
 *
 */
@SpringBootApplication
public class SpringKeycloakApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKeycloakApplication.class, args);
    }

}
