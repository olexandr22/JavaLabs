package commands.userCreditCommans;

import appLogic.CreditsService;
import appLogic.UserService;
import baseClasses.Credit;
import baseClasses.User;
import commands.Command;
import commands.creditListCommands.ShowAllCreditsCommand;

import java.util.List;
import java.util.Scanner;

public class CloseCreditCommand implements Command {
    private Scanner sc;
    private UserService userService;
    private CreditsService creditsService;

    public CloseCreditCommand(Scanner sc, UserService userService, CreditsService creditsService) {
        this.sc = sc;
        this.userService = userService;
        this.creditsService = creditsService;
    }

    @Override
    public void execute() {
        userService.closeCredit(sc, creditsService);
    }

    @Override
    public String getDescription() {
        return "Закрити кредит";
    }
}
