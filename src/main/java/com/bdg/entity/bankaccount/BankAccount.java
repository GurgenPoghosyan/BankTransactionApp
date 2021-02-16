package com.bdg.entity.bankaccount;

import com.bdg.entity.role.Role;
import com.bdg.entity.accountcard.BankAccountCard;
import com.bdg.entity.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @ManyToMany
    private List<Role> roles=new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    private BankAccountCard bankAccountCard;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Transaction> transactions=new ArrayList<>();

    public BankAccount(String firstName, String lastName, String emailAddress, BankAccountCard bankAccountCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.bankAccountCard = bankAccountCard;
    }
}
