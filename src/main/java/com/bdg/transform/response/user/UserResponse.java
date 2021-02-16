package com.bdg.transform.response.user;

import com.bdg.common.enums.RoleType;
import com.bdg.entity.bankaccount.BankAccount;
import com.bdg.entity.role.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private BankAccount bankAccount;


}
