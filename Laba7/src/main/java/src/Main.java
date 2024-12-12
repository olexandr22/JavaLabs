package src;

import src.appLogic.CreditsService;
import src.appLogic.UserService;
import src.menu.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CreditsService creditsService = new CreditsService();
        UserService userService = new UserService();
        String userFilePath = "src/progFiles/userList.dat";
        String creditsFilePath = "src/progFiles/creditList.dat";

        Menu menu = new Menu(scanner, creditsService, userService, userFilePath, creditsFilePath);
        menu.execute();
    }
}