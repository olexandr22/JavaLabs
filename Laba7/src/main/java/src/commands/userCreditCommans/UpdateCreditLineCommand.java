package src.commands.userCreditCommans;

import src.appLogic.UserService;
import src.commands.Command;

import java.util.Scanner;

public class UpdateCreditLineCommand implements Command {
    private Scanner sc;
    private UserService userService;

    public UpdateCreditLineCommand(Scanner sc, UserService userService) {
        this.sc = sc;
        this.userService = userService;
    }

    @Override
    public void execute() {
        userService.updateCreditLine(sc);
    }

    @Override
    public String getDescription() {
        return "Оновити кредитну лінію";
    }
}
