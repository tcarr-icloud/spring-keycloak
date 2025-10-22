package com.example.springkeycloak.clientrepresentation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/clients")
class ClientRepresentationController {
  @Value("${keycloak.auth-server-url}")
  private String authServerUrl;

  @Value("${keycloak.realm}")
  private String realm;

  @GetMapping("/{id}")
  public ClientRepresentation getClient(
      @RequestHeader("Authorization") String authorizationHeader, @PathVariable String id) {
    return RestClient.create()
        .get()
        .uri(authServerUrl + "/admin/realms/" + realm + "/clients/" + id)
        .header("Authorization", authorizationHeader)
        .retrieve()
        .body(ClientRepresentation.class);
  }

  @GetMapping
  public ClientRepresentation[] getAll(@RequestHeader("Authorization") String authorizationHeader) {
    return RestClient.create()
        .get()
        .uri(authServerUrl + "/admin/realms/" + realm + "/clients")
        .header("Authorization", authorizationHeader)
        .retrieve()
        .body(new ParameterizedTypeReference<>() {});
  }
}
