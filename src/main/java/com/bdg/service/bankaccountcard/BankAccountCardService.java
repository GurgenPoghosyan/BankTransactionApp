package com.bdg.service.bankaccountcard;

import com.bdg.entity.accountcard.BankAccountCard;
import com.bdg.repository.bankusercard.BankUserCardRepository;
import org.springframework.stereotype.Service;

@Service
public class BankAccountCardService {

    private final BankUserCardRepository bankUserCardRepository;

    public BankAccountCardService(BankUserCardRepository bankUserCardRepository) {
        this.bankUserCardRepository = bankUserCardRepository;
    }

    public BankAccountCard save(BankAccountCard bankAccountCard){
        return bankUserCardRepository.save(bankAccountCard);
    }
}
