package spring.boot.training.bank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankCard {
    private User user;
    private LocalDate validity;
    private CardType cardType;
    private String cardNumber;
    private double balance;
}
