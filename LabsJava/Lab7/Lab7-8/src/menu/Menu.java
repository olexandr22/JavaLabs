package menu;

import appLogic.CreditsService;
import appLogic.UserService;
import commands.Command;
import commands.authentificationCommands.LogInCommand;
import commands.authentificationCommands.RegistrationCommand;
import emailService.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Menu implements Command{
    protected Scanner scanner;
    private static final Logger logger = LogManager.getLogger(Menu.class);
    private static final EmailService emailservice = new EmailService();

    protected CreditsService creditsService;
    protected UserService userService;
    protected String userFilePath;
    protected String creditsFilePath;

    protected boolean exit = false;
    protected Map<Integer, Command> menu = new HashMap<>();

    public Menu(Scanner scanner, CreditsService creditsService, UserService userService, String userFilePath, String creditsFilePath) {
        this.scanner = scanner;
        this.creditsService = creditsService;
        this.userService = userService;
        this.userFilePath = userFilePath;
        this.creditsFilePath = creditsFilePath;
    }

    @Override
    public void execute() {
        SubMenu subMenu = new SubMenu(scanner, creditsService, userService, userFilePath, creditsFilePath);
        fillMap(subMenu);
//
//        System.out.println("Ввійдіть в акаунт або зареєструйте його");
//        chooseCommand(authentification, "Авторизуйтесь");
//
//        exit = false;

        chooseCommand(menu, "\n--- Меню авторизації ---", subMenu);

    }

    public void fillMap(SubMenu subMenu) {

        menu.put(1, new LogInCommand(scanner, userFilePath, userService, subMenu)); // Логін користувача
        menu.put(2, new RegistrationCommand(scanner, userFilePath, userService)); // Реєстрація нового користувача

        //menu.put(7, new WriteCreditsToFileCommand(creditsFilePath, credits)); // Запис кредитів у файл
    }


    public void chooseCommand(Map<Integer, Command> map, String text, SubMenu subMenu) {
        while (!exit)
        {
            System.out.println(text);
            System.out.println("0: Вихід");
            for (var entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue().getDescription());
            }
            System.out.print("Оберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) {
                exit = true;
            }

            else {
                Command command = map.get(choice);

                try {
                    command.execute();
                    logger.info("Виконано команду: " + command.getDescription());
                    //throw new RuntimeException("Виявлено критичну помилку!");
                } catch (Exception e) {
                    logger.error("Критична помилка: ", e);
                    emailservice.sendErrorEmail("Критична помилка у програмі!", e.getMessage());
                }
            }
        }
    }

    @Override
    public String getDescription() {
        return "Main menu";
    }
}


