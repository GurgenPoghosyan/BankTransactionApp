package com.bdg.repository.bankusercard;

import com.bdg.entity.accountcard.BankAccountCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankUserCardRepository extends JpaRepository<BankAccountCard,Long> {
}
