package com.bdg.service.bankaccount;

import com.bdg.common.exception.AccountNotFoundException;
import com.bdg.common.exception.RoleNotFoundException;
import com.bdg.common.exception.UserNotFoundException;
import com.bdg.entity.bankaccount.BankAccount;
import com.bdg.entity.role.Role;
import com.bdg.entity.user.User;
import com.bdg.entity.accountcard.BankAccountCard;
import com.bdg.repository.bankaccount.BankAccountRepository;
import com.bdg.repository.role.RoleRepository;
import com.bdg.repository.user.UserRepository;
import com.bdg.service.bankaccountcard.BankAccountCardService;
import com.bdg.service.user.UserService;
import com.bdg.transform.request.account.AccountCreateRequest;
import com.bdg.transform.request.account.AccountUpdateRequest;
import com.bdg.transform.request.user.UserUpdateByAccountNumberRequest;
import com.bdg.transform.response.register.RegisterResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BankAccountService implements RegisterSupported<AccountCreateRequest, RegisterResponse> {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;
    private final BankAccountCardService bankAccountCardService;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository,
                              UserRepository userRepository,
                              RoleRepository roleRepository,
                              UserService userService,
                              BankAccountCardService bankAccountCardService) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
        this.bankAccountCardService = bankAccountCardService;
        this.roleRepository=roleRepository;
        this.userService=userService;
    }

    @Override
    public RegisterResponse register(AccountCreateRequest accountCreateRequest) {
        User user = userRepository.findById(accountCreateRequest.getId()).orElseThrow(() -> new UserNotFoundException(accountCreateRequest.getId()));
        BankAccountCard bankAccountCard = new BankAccountCard(user.getFirstName().toUpperCase(),
                user.getLastName().toUpperCase(),
                generateStringSymbols(16),
                generateStringSymbols(14));
        BankAccountCard savedBankAccountCard = bankAccountCardService.save(bankAccountCard);
        Role role=roleRepository.findByRoleType(accountCreateRequest.getRoleType()).orElseThrow(()->new RoleNotFoundException(accountCreateRequest.getRoleType()));
        user.setRoles(List.of(role));
        BankAccount bankAccount = new BankAccount(user.getFirstName(), user.getLastName(),
                user.getEmailAddress(), savedBankAccountCard);
        bankAccount.setRoles(List.of(role));
        BankAccount savedAccount = bankAccountRepository.save(bankAccount);
        user.setBankAccount(savedAccount);
        UserUpdateByAccountNumberRequest updateRequest=new UserUpdateByAccountNumberRequest();
        BeanUtils.copyProperties(user,updateRequest);
        userService.update(user.getId(), updateRequest);
        RegisterResponse registerResponse = new RegisterResponse();
        BeanUtils.copyProperties(savedAccount, registerResponse);
        registerResponse.setRoles(user.getRoles());
        return registerResponse;
    }

    private String generateStringSymbols(int count) {
        Random rand = new Random();
        StringBuilder symbols = new StringBuilder(count);
        for (int i = 0; i < count; i++)
            symbols.append(rand.nextInt(10));
        return symbols.toString();
    }
}
