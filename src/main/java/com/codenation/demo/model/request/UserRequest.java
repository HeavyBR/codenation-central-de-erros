package com.codenation.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UserRequest {

    @NotEmpty
    @Email
    private String username;

    @NotEmpty
    private String password;

}
