import appLogic.CreditsService;
import appLogic.UserService;
import menu.Menu;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Програму запущено!");

        Scanner scanner = new Scanner(System.in);

        CreditsService creditsService = new CreditsService();
        UserService userService = new UserService();
        String userFilePath = "Lab4-6/src/progFiles/userList.dat";
        String creditsFilePath = "Lab4-6/src/progFiles/creditList.dat";

        Menu menu = new Menu(scanner, creditsService, userService, userFilePath, creditsFilePath);
        menu.execute();
    }
}