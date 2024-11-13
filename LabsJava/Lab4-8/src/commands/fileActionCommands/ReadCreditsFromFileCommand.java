package commands.fileActionCommands;

import appLogic.CreditsService;
import commands.Command;


public class ReadCreditsFromFileCommand implements Command {
    private String filePath;
    private CreditsService creditsService;

    public ReadCreditsFromFileCommand(String filePath, CreditsService creditsService) {
        this.filePath = filePath;
        this.creditsService = creditsService;
    }

    @Override
    public void execute() {
        creditsService.readCredits(filePath);
    }

    @Override
    public String getDescription() {
        return "Читати кредити з файлу";
    }
}
