package com.bdg.transform.request.account;

import com.bdg.common.enums.RoleType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountCreateRequest {

    @NotNull
    private Long id;

    @NotNull
    private RoleType roleType;
}
