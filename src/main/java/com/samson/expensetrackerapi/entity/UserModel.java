package com.samson.expensetrackerapi.entity;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
