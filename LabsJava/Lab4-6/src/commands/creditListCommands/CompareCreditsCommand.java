package commands.creditListCommands;

import appLogic.CreditsService;
import baseClasses.Credit;
import commands.Command;

import java.util.List;
import java.util.Scanner;

public class CompareCreditsCommand implements Command {
    private CreditsService creditsService;
    private Scanner sc;

    public CompareCreditsCommand(Scanner sc, CreditsService creditsService) {
        this.sc = sc;
        this.creditsService = creditsService;
    }

    @Override
    public void execute() {
        creditsService.compareCredits(sc);
    }

    @Override
    public String getDescription() {
        return "Порівняння кредитів";
    }

}
