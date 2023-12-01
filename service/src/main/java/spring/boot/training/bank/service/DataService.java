package spring.boot.training.bank.service;

import spring.boot.training.bank.domain.BankCard;
import spring.boot.training.bank.domain.CardType;
import spring.boot.training.bank.domain.Credentials;
import spring.boot.training.bank.domain.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataService {

    private List<User> users;
    private List<BankCard> bankCards;
    private User loggedInUser;

    public DataService() {
        populateUsers();
        populateBankCards();
    }

    private void populateUsers() {
        users = new ArrayList<>();
        users.add(new User("Peter Kis", new Credentials("peter.kis", "123")));
        users.add(new User("Adam Nagy ", new Credentials("adam.nagy", "123")));
    }

    private void populateBankCards() {
        bankCards = new ArrayList<>();
        bankCards.add(new BankCard(users.get(0), LocalDate.of(2023, 10, 7), CardType.MASTER_CARD, "32525", 42000));
        bankCards.add(new BankCard(users.get(1), LocalDate.of(2023, 7, 10), CardType.VISA, "56232", 98000));
    }


    public User authenticateUser(Credentials credentials) throws AuthenticationException {
        User authenticatedUser = users.stream()
                .filter(user -> user.getCredentials().equals(credentials))
                .findFirst().orElseThrow(() -> new AuthenticationException("Username or password is wrong."));
        loggedInUser = authenticatedUser;
        return authenticatedUser;
    }

    public List<BankCard> findBankCardsByUser() {
        return bankCards.stream()
                .filter(bankCard -> bankCard.getUser().equals(loggedInUser))
                .toList();
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
