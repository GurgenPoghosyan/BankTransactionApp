package com.bdg.service.bankaccount;

import com.bdg.common.exception.UserNotFoundException;
import com.bdg.entity.bankaccount.BankAccount;
import com.bdg.entity.role.Role;
import com.bdg.entity.user.User;
import com.bdg.entity.accountcard.BankAccountCard;
import com.bdg.repository.bankaccount.BankAccountRepository;
import com.bdg.repository.user.UserRepository;
import com.bdg.service.bankaccountcard.BankAccountCardService;
import com.bdg.transform.request.register.RegisterRequest;
import com.bdg.transform.response.register.RegisterResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BankAccountService implements RegisterSupported<RegisterRequest, RegisterResponse> {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;
    private final BankAccountCardService bankAccountCardService;

    public BankAccountService(BankAccountRepository bankAccountRepository,
                              UserRepository userRepository,
                              BankAccountCardService bankAccountCardService) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
        this.bankAccountCardService = bankAccountCardService;
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = userRepository.findById(registerRequest.getId()).orElseThrow(() -> new UserNotFoundException(registerRequest.getId()));
        BankAccountCard bankAccountCard = new BankAccountCard(user.getFirstName().toUpperCase(),
                user.getLastName().toUpperCase(),
                generateStringSymbols(16),
                generateStringSymbols(14));
        bankAccountCard = bankAccountCardService.save(bankAccountCard);
        BankAccount bankAccount = new BankAccount(user.getId(), user.getFirstName(), user.getLastName(),
                user.getEmailAddress(), user.getRoles(), bankAccountCard);
        BankAccount savedAccount = bankAccountRepository.save(bankAccount);
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
