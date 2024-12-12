package commands.creditListCommands;

import appLogic.CreditsService;
import commands.Command;


public class ShowAllCreditsCommand implements Command {
    private CreditsService creditsService;

    public ShowAllCreditsCommand(CreditsService creditsService) {
        this.creditsService = creditsService;
    }

    @Override
    public void execute() {
        creditsService.showAllCredits();
    }

    @Override
    public String getDescription() {
        return "Показати всі кредити";
    }
}
