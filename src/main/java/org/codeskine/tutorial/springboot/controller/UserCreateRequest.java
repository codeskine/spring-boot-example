package org.codeskine.tutorial.springboot.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "User create request")
class UserCreateRequest {

  @NotNull
  @Parameter(description = "Name of the user we want to create")
  private final String firstName;

  @NotNull
  @Parameter(description = "Last name of the user we want to create")
  private final String lastName;

  @Email
  @NotNull
  @Parameter(description = "The email of the user we want to create")
  private final String email;
}
