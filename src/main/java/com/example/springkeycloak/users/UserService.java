package com.example.springkeycloak.users;

import com.example.springkeycloak.keycloak.user.UserRepresentation;
import com.example.springkeycloak.keycloak.user.UserRepresentationService;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
class UserService {
  private final UserRepository userRepository;
  private final UserRepresentationService keycloakUserService;

  UserService(UserRepository userRepository, UserRepresentationService keycloakUserService) {
    this.userRepository = userRepository;
    this.keycloakUserService = keycloakUserService;
  }

  public UserDTO getUserById(String authorizationHeader, Long id) {
    User user = userRepository.findById(id).orElseThrow();
    UserRepresentation userRepresentation = null;
    if (user.getKeycloakUserId() != null) {
      userRepresentation =
          keycloakUserService.getUserById(authorizationHeader, user.getKeycloakUserId());
    }
    return toUserDTO(user, userRepresentation);
  }

  public UserDTO[] getUsers(String authorizationHeader) {
    Map<String, UserRepresentation> userRepresentations = new HashMap<>();
    Arrays.stream(keycloakUserService.getUsers(authorizationHeader))
        .forEach(
            (userRepresentation) ->
                userRepresentations.put(userRepresentation.id, userRepresentation));

    return userRepository.findAll().stream()
        .map(
            user -> {
              UserRepresentation userRepresentation =
                  userRepresentations.get(user.getKeycloakUserId());
              return toUserDTO(user, userRepresentation);
            })
        .distinct()
        .toArray(UserDTO[]::new);
  }

  private UserDTO toUserDTO(User user, UserRepresentation userRepresentation) {
    if (userRepresentation == null) {
      return new UserDTO(user.getId(), null, null, null, null, null, null);
    } else {
      return new UserDTO(
          user.getId(),
          userRepresentation.id,
          userRepresentation.username,
          userRepresentation.firstName,
          userRepresentation.lastName,
          userRepresentation.email,
          userRepresentation.enabled);
    }
  }
}
