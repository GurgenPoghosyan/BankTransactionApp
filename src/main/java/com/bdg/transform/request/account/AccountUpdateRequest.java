package com.bdg.transform.request.account;

import com.bdg.entity.accountcard.BankAccountCard;
import com.bdg.entity.role.Role;
import com.bdg.entity.transaction.Transaction;
import com.bdg.transform.request.user.UpdateRequest;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccountUpdateRequest extends UpdateRequest {

    private String firstName;

    private String lastName;

    private String emailAddress;

    private List<Role> roles=new ArrayList<>();

    private BankAccountCard bankAccountCard;

    private List<Transaction> transactions=new ArrayList<>();
}
