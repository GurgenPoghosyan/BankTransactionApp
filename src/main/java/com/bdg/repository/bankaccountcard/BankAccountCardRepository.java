package com.bdg.repository.bankaccountcard;

import com.bdg.entity.accountcard.BankAccountCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountCardRepository extends JpaRepository<BankAccountCard,Long> {
}
