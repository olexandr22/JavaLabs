package commands.authentificationCommands;

import appLogic.UserService;
import commands.Command;

import java.util.Scanner;

public class RegistrationCommand implements Command {
    private Scanner sc;
    private UserService userService;
    private String filePath;

    public RegistrationCommand(Scanner sc, String filePath, UserService userService) {
        this.sc = sc;
        this.filePath = filePath;
        this.userService = userService;
    }

    @Override
    public void execute() {
        userService.registerUser(sc, filePath);
    }

    @Override
    public String getDescription() {
        return "Реєстрація нового користувача";
    }

}
