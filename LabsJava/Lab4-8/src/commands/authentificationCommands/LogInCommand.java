package commands.authentificationCommands;

import appLogic.UserService;
import commands.Command;
import java.util.Scanner;

public class LogInCommand implements Command {
    private Scanner sc;
    private String filePath;
    private UserService userService;

    public LogInCommand(Scanner sc, String filePath, UserService userService) {
        this.sc = sc;
        this.filePath = filePath;
        this.userService = userService;
    }

    @Override
    public void execute() {
        userService.loginUser(sc, filePath);
    }

    @Override
    public String getDescription() {
        return "Логін користувача";
    }
}
