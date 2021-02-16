package com.bdg.transform.request.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Data
public class UserUpdateRequest extends UpdateRequest {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String emailAddress;
}
