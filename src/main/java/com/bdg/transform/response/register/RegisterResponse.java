package com.bdg.transform.response.register;

import com.bdg.entity.role.Role;
import com.bdg.entity.accountcard.BankAccountCard;
import lombok.Data;

import java.util.List;

@Data
public class RegisterResponse {
    private String firstName;

    private String lastName;

    private String emailAddress;

    private List<Role> roles;

    private BankAccountCard bankAccountCard;
}
