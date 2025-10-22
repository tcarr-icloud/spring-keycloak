package com.example.springkeycloak.userrepresentation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/users")
class UserRepresentationController {
  @Value("${keycloak.auth-server-url}")
  private String authServerUrl;

  @Value("${keycloak.realm}")
  private String realm;

  @GetMapping("/{id}")
  UserRepresentation getUser(
      @RequestHeader("Authorization") String authorizationHeader, @PathVariable("id") String id) {
    return RestClient.create()
        .get()
        .uri(authServerUrl + "/admin/realms/" + realm + "/users/" + id)
        .header("Authorization", authorizationHeader)
        .retrieve()
        .body(UserRepresentation.class);
  }

  @GetMapping
  UserRepresentation[] getUsers(@RequestHeader("Authorization") String authorizationHeader) {
    return RestClient.create()
        .get()
        .uri(authServerUrl + "/admin/realms/" + realm + "/users")
        .header("Authorization", authorizationHeader)
        .retrieve()
        .body(new ParameterizedTypeReference<>() {});
  }
}
