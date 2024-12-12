package src.commands.creditListCommands;

import src.appLogic.CreditsService;
import src.commands.Command;

import java.util.Scanner;

public class SearchCreditByParametrsCommand implements Command {
    private CreditsService creditsService;
    private Scanner sc;

    public SearchCreditByParametrsCommand(Scanner sc, CreditsService creditsService) {
        this.sc = sc;
        this.creditsService = creditsService;
    }

    @Override
    public void execute() {
        creditsService.searchCreditsByParametrs(sc);
    }

    @Override
    public String getDescription() {
        return "Пошук кредиту за параметрами";
    }
}
