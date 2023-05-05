package com.example.demo.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    private String email;
    @NotEmpty(message = "Please enter your name")
    private String firstnsme;
    @NotEmpty(message = "Please enter your firstname")
    private String lastname;
    @NotEmpty(message = "Please enter your use rname")
    private String username;
    @NotEmpty(message = "Please enter your password")
    @Size(min = 6)
    private String password;
    private String confirmPassword;

}
