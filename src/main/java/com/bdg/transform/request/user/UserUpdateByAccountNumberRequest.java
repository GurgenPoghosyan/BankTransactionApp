package com.bdg.transform.request.user;

import com.bdg.entity.bankaccount.BankAccount;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserUpdateByAccountNumberRequest extends UpdateRequest {
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private BankAccount bankAccount;

    @NotEmpty
    private String emailAddress;
}
