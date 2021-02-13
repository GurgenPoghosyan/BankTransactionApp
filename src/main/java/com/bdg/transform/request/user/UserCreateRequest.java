package com.bdg.transform.request.user;

import com.bdg.common.enums.RoleType;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserCreateRequest {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 3)
    private String password;

    @NotEmpty
    private String emailAddress;

    @NotNull
    private RoleType roleType;
}
