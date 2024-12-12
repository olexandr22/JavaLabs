package commands.userCreditCommans;

import appLogic.CreditsService;
import appLogic.UserService;
import commands.Command;
import java.util.Scanner;

public class TakeCreditCommand implements Command {
    private Scanner sc;
    private UserService userService;
    private CreditsService creditsService;

    public TakeCreditCommand(Scanner sc, UserService userService, CreditsService creditsService) {
        this.sc = sc;
        this.userService = userService;
        this.creditsService = creditsService;
    }

    @Override
    public void execute() {
        userService.takeCredit(sc, creditsService);
    }

    @Override
    public String getDescription() {
        return "Оформити новий кредит";
    }
}