package src.commands.creditListCommands;

import src.appLogic.CreditsService;
import src.commands.Command;

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
