package com.bdg.transform.request.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Data
public class UserUpdateRequest {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 3)
    private String password;

    @NotEmpty
    private String emailAddress;
}
