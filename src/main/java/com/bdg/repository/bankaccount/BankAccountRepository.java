package com.bdg.repository.bankaccount;

import com.bdg.entity.bankaccount.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
}
