package com.bdg.service.bankaccountcard;

import com.bdg.entity.accountcard.BankAccountCard;
import com.bdg.repository.bankaccountcard.BankAccountCardRepository;
import org.springframework.stereotype.Service;

@Service
public class BankAccountCardService {

    private final BankAccountCardRepository bankAccountCardRepository;

    public BankAccountCardService(BankAccountCardRepository bankAccountCardRepository) {
        this.bankAccountCardRepository = bankAccountCardRepository;
    }

    public BankAccountCard save(BankAccountCard bankAccountCard){
        return bankAccountCardRepository.save(bankAccountCard);
    }
}
