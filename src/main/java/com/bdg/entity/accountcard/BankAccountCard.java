package com.bdg.entity.accountcard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="t_account_cards")
public class BankAccountCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String cardFirstName;

    @Column(name = "last_name", nullable = false)
    private String cardLastName;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    public BankAccountCard(String cardFirstName, String cardLastName, String cardNumber, String accountNumber) {
        this.cardFirstName = cardFirstName;
        this.cardLastName = cardLastName;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
    }
}
