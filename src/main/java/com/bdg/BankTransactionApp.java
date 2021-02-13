package com.bdg;

import com.bdg.common.enums.RoleType;
import com.bdg.entity.role.Role;
import com.bdg.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BankTransactionApp implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BankTransactionApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role admin=new Role(RoleType.ADMIN);
        Role user=new Role(RoleType.USER);
        roleRepository.saveAll(List.of(admin,user));
    }
}