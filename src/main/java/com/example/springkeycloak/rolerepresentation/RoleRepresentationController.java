package com.example.springkeycloak.rolerepresentation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/roles")
class RoleRepresentationController {
  @Value("${keycloak.auth-server-url}")
  private String authServerUrl;

  @Value("${keycloak.realm}")
  private String realm;

  @GetMapping("/{id}")
  public RoleRepresentation getRole(
      @RequestHeader("Authorization") String authorizationHeader, @PathVariable String id) {
    return RestClient.create()
        .get()
        .uri(authServerUrl + "/admin/realms/" + realm + "/roles/" + id)
        .header("Authorization", authorizationHeader)
        .retrieve()
        .body(RoleRepresentation.class);
  }

  @GetMapping
  public RoleRepresentation[] getRoles(@RequestHeader("Authorization") String authorizationHeader) {
    return RestClient.create()
        .get()
        .uri(authServerUrl + "/admin/realms/" + realm + "/roles")
        .header("Authorization", authorizationHeader)
        .retrieve()
        .body(new ParameterizedTypeReference<>() {});
  }
}
