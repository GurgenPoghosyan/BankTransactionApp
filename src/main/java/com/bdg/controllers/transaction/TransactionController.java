package com.bdg.controllers.transaction;

import com.bdg.common.exception.UserNotFoundException;
import com.bdg.entity.user.User;
import com.bdg.repository.user.UserRepository;
import com.bdg.service.transaction.TransactionService;
import com.bdg.transform.request.trannsaction.TransactionRequest;
import com.bdg.transform.response.transaction.TransactionResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserRepository userRepository;

    public TransactionController(TransactionService transactionService,
                                 UserRepository userRepository) {
        this.transactionService = transactionService;
        this.userRepository=userRepository;
    }

    @PostMapping("/{id}/transaction")
    public TransactionResponse beginTransaction(@PathVariable Long id, @RequestBody TransactionRequest transactionRequest){
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        transactionRequest.setUser_id(user.getId());
        return transactionService.createTransaction(id, transactionRequest);
    }

}
