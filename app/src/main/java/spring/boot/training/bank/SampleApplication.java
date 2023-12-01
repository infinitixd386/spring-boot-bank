package spring.boot.training.bank;

import spring.boot.training.bank.service.AuthenticationException;
import spring.boot.training.bank.service.DataService;
import spring.boot.training.bank.view.ConsoleView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleApplication implements CommandLineRunner {
    @Autowired
    private DataService dataService;
    @Autowired
    private ConsoleView consoleView;

    private static final Log LOG = LogFactory.getLog(SampleApplication.class.getName());

    @Override
    public void run(String... args) {
        do {
            try {
                consoleView.welcomeMessage(dataService.authenticateUser(consoleView.askForCredentials()));
            } catch (AuthenticationException ex) {
                LOG.info(ex.getMessage());
            }
        }while(dataService.getLoggedInUser() == null);
        LOG.info("User logged in successfully.");
        consoleView.printUserBankCards(dataService.findBankCardsByUser());
        LOG.info("User's bank cards printed out successfully.");
        consoleView.printExitMessage(dataService.getLoggedInUser());
        LOG.info("User logged out.");
    }
}
