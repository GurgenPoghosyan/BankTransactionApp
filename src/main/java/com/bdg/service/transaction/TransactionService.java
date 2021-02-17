package com.bdg.service.transaction;

import com.bdg.common.enums.Status;
import com.bdg.common.enums.TransactionType;
import com.bdg.common.exception.BalanceNotAvailableException;
import com.bdg.common.exception.UserNotFoundException;
import com.bdg.entity.transaction.Transaction;
import com.bdg.entity.user.User;
import com.bdg.repository.transaction.TransactionRepository;
import com.bdg.repository.user.UserRepository;
import com.bdg.service.bankaccount.BankAccountService;
import com.bdg.service.user.UserService;
import com.bdg.transform.request.account.AccountUpdateRequest;
import com.bdg.transform.request.trannsaction.TransactionRequest;
import com.bdg.transform.request.user.UserUpdateByAccountNumberRequest;
import com.bdg.transform.response.transaction.TransactionResponse;
import com.bdg.transform.response.transaction.TransactionsListResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository ;
    private final UserService userService;
    private final BankAccountService bankAccountService;

    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository,
                              BankAccountService bankAccountService,
                              UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.bankAccountService=bankAccountService;
    }


    public TransactionResponse createTransaction(Long id, TransactionRequest transactionRequest) {
        User owner = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Transaction transaction = new Transaction(transactionRequest.getAccountId(),
                LocalDateTime.now(), transactionRequest.getTransactionAmount(),
                transactionRequest.getTransactionType(), owner.getId());
        transaction.setStatus(Status.PENDING);
        doTransaction(id, transactionRequest.getAccountId(),
                transactionRequest.getTransactionAmount(),
                transactionRequest.getTransactionType());
        //--------------------------------------------------------------------
        transaction.setStatus(Status.APPROVED);
        Transaction savedTransaction = transactionRepository.save(transaction);

        owner.getBankAccount().getTransactions().add(savedTransaction);
        AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
        BeanUtils.copyProperties(owner,accountUpdateRequest);

        userService.update(owner.getId(), accountUpdateRequest);

        TransactionResponse transactionResponse = new TransactionResponse();
        BeanUtils.copyProperties(savedTransaction, transactionResponse);
        transactionResponse.setUser_id(owner.getId());
        return transactionResponse;
    }

    public void doTransaction(Long userId1, Long userId2, double amount, TransactionType transactionType) {
        if (transactionType == TransactionType.DEPOSIT) {
            User user1 = userRepository.findById(userId1).orElseThrow(() -> new UserNotFoundException(userId1));
            if(user1.getBankAccount().getBankAccountCard().getBalance()<amount)
                throw new BalanceNotAvailableException();
            user1.getBankAccount().getBankAccountCard().setBalance(user1.getBankAccount().getBankAccountCard().getBalance() - amount);
            UserUpdateByAccountNumberRequest updateRequest = new UserUpdateByAccountNumberRequest();
            BeanUtils.copyProperties(user1, updateRequest);
            userService.update(userId1, updateRequest);
            User user2 = userRepository.findById(userId2).orElseThrow(() -> new UserNotFoundException(userId2));
            user2.getBankAccount().getBankAccountCard().setBalance(user2.getBankAccount().getBankAccountCard().getBalance() + amount);
            BeanUtils.copyProperties(user2, updateRequest);
            userService.update(userId2, updateRequest);
        }
        if (transactionType == TransactionType.WITHDRAWAL) {
            User user1 = userRepository.findById(userId1).orElseThrow(() -> new UserNotFoundException(userId1));
            user1.getBankAccount().getBankAccountCard().setBalance(user1.getBankAccount().getBankAccountCard().getBalance() + amount);
            UserUpdateByAccountNumberRequest updateRequest = new UserUpdateByAccountNumberRequest();
            BeanUtils.copyProperties(user1, updateRequest);
            userService.update(userId1, updateRequest);
            User user2 = userRepository.findById(userId2).orElseThrow(() -> new UserNotFoundException(userId2));
            user2.getBankAccount().getBankAccountCard().setBalance(user2.getBankAccount().getBankAccountCard().getBalance() - amount);
            BeanUtils.copyProperties(user2, updateRequest);
            userService.update(userId2, updateRequest);
        }
    }

    public TransactionsListResponse get(Long id) {
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        TransactionsListResponse transactionsListResponse=new TransactionsListResponse();
        transactionsListResponse.setTransactions(user.getBankAccount().getTransactions());
        return transactionsListResponse;
    }
}
