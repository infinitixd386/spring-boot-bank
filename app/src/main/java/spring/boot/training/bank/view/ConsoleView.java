package spring.boot.training.bank.view;

import spring.boot.training.bank.domain.BankCard;
import spring.boot.training.bank.domain.Credentials;
import spring.boot.training.bank.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ConsoleView {
    Scanner scanner = new Scanner(System.in);
    private static final String SEPARATOR = System.lineSeparator();

    public Credentials askForCredentials() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        printEmptyLine();
        return new Credentials(username, password);
    }

    public void welcomeMessage(User user) {
        System.out.println("Welcome " + user.getFullName() + "!");
        printEmptyLine();
    }

    public void printUserBankCards(List<BankCard> bankCards) {
        System.out.println("User's BankCards: ");
        AtomicInteger index = new AtomicInteger(1);
        bankCards.forEach(bankCard -> {
            System.out.println(index.getAndIncrement() + "." + SEPARATOR
                    + "Card Number: " + bankCard.getCardNumber() + SEPARATOR
                    + "Validity: " + bankCard.getValidity() + SEPARATOR
                    + "Card Type: " + bankCard.getCardType() + SEPARATOR
                    + "Balance: " + bankCard.getBalance() + "Ft");
            printEmptyLine();
        });
    }

    public void printExitMessage(User user) {
        System.out.println("Press q to exit...");
        String enter;
        do {
            enter = scanner.nextLine();
        } while (!enter.equals("q"));
        System.out.println("See you next time " + user.getFullName() + "!");
    }

    public void printEmptyLine() {
        System.out.println();
    }

}
