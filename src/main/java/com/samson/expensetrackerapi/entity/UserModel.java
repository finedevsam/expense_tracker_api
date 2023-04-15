package com.samson.expensetrackerapi.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {

    @NotBlank(message = "Name should not be empty")
    private String name;

    @NotNull(message = "Email should not be empty")
    @Email(message = "Enter valid email")
    private String email;

    @NotNull(message = "Password must not be empty")
    @Size(min = 8, message = "Password must not be less than 8 character")
    private String password;

    private Long age = 0L;
}
