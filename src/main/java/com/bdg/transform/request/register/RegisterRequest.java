package com.bdg.transform.request.register;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterRequest {

    @NotNull
    private Long id;
}
