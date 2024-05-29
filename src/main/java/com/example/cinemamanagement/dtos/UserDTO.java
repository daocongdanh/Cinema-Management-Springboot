package com.example.cinemamanagement.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "firstName must be not blank")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "lastName must be not blank")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "phone must be not blank")
    private String phone;

    @NotBlank(message = "email must be not blank")
    private String email;

    private boolean gender;

    @NotBlank(message = "username must be not blank")
    private String username;

    @NotBlank(message = "password must be not blank")
    private String password;

    private boolean active;
}
