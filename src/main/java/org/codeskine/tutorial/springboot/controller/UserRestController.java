package org.codeskine.tutorial.springboot.controller;

import static org.codeskine.tutorial.springboot.security.OpenAPISecurityConfig.AUTHENTICATION_TYPE;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.net.URI;
import java.util.Map;
import javax.validation.Valid;
import org.codeskine.tutorial.springboot.DemoMetadata;
import org.codeskine.tutorial.springboot.model.User;
import org.codeskine.tutorial.springboot.repository.UserRepository;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserRestController {

  private final UserRepository repository;
  private final DemoMetadata metadata;

  @Autowired
  public UserRestController(UserRepository repository, DemoMetadata demoMetadata) {
    this.repository = repository;
    this.metadata = demoMetadata;
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create new user on db")
  @SecurityRequirement(name = AUTHENTICATION_TYPE)
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User created with success")})
  public ResponseEntity<URI> createUser(@RequestBody @Valid UserCreateRequest request,
      final UriComponentsBuilder uriBuilder) {
    final var user = repository.save(
        User.of(request.getFirstName(), request.getLastName(), request.getEmail()));

    final var uriComponents = fromController(uriBuilder, getClass())
        .path("/{id}").buildAndExpand(user.getId());

    return ResponseEntity.created(uriComponents.toUri()).build();
  }

  @Operation(summary = "Get all users from db")
  @SecurityRequirement(name = AUTHENTICATION_TYPE)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<User> findAll(@ParameterObject @PageableDefault(size = 20)
  @SortDefault.SortDefaults({
      @SortDefault(sort = "name", direction = Sort.Direction.DESC),
      @SortDefault(sort = "id", direction = Sort.Direction.ASC)
  }) Pageable pageable) {
    return repository.findAll(pageable);
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Get a User by ID")
  @SecurityRequirement(name = AUTHENTICATION_TYPE)
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the user",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = @Content)})
  public ResponseEntity<User> findById(
      @Parameter(description = "email of the user to be searched") @PathVariable("id") String id) {
    return repository.findById(id)
        .map(ResponseEntity::ok)
        .orElseThrow(UserNotFoundException::new);
  }

  @GetMapping(path = "/greetings", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> greetings(JwtAuthenticationToken principal) {
    return ResponseEntity.ok(
        Map.of("message",
            String.format("HI!!!! %s %s, welcome to %s!!!!",
                principal.getTokenAttributes().get("given_name"),
                principal.getTokenAttributes().get("family_name"), metadata.getCity())));
  }



}
