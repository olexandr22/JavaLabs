package src.commands.authentificationCommands;

import src.appLogic.UserService;
import src.commands.Command;
import src.menu.SubMenu;

import java.util.Scanner;

public class LogInCommand implements Command {
    private Scanner sc;
    private String filePath;
    private UserService userService;
    private SubMenu smenu;

    public LogInCommand(Scanner sc, String filePath, UserService userService, SubMenu menu) {
        this.sc = sc;
        this.filePath = filePath;
        this.userService = userService;
        this.smenu = menu;
    }

    @Override
    public void execute() {
        userService.loginUser(sc, filePath);
        smenu.execute();
    }

    @Override
    public String getDescription() {
        return "Логін користувача";
    }
}
