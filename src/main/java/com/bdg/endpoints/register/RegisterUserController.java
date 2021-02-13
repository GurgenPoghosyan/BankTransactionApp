package com.bdg.endpoints.register;

import com.bdg.service.bankaccount.BankAccountService;
import com.bdg.transform.request.register.RegisterRequest;
import com.bdg.transform.response.register.RegisterResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class RegisterUserController {

    private final BankAccountService bankAccountService;

    public RegisterUserController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/register")
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest){
        return bankAccountService.register(registerRequest);
    }
}
