import appLogic.CreditsService;
import appLogic.UserService;
import commands.Command;
import commands.authentificationCommands.LogInCommand;
import commands.authentificationCommands.RegistrationCommand;
import commands.creditListCommands.CompareCreditsCommand;
import commands.creditListCommands.SearchCreditByParametrsCommand;
import commands.creditListCommands.ShowAllCreditsCommand;
import commands.fileActionCommands.ReadCreditsFromFileCommand;
import commands.userCreditCommans.CloseCreditCommand;
import commands.userCreditCommans.TakeCreditCommand;
import commands.userCreditCommans.UpdateCreditLineCommand;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    private CreditsService creditsService = new CreditsService();
    private UserService userService = new UserService();
    private String userFilePath = "Lab4-8/src/progFiles/userList.dat";
    private String creditsFilePath = "Lab4-8/src/progFiles/creditList.dat";
    private boolean exit = false;


    private Map<Integer, Command> menu = new HashMap<>();
    private Map<Integer, Command> authentification = new HashMap<>();


    public void mainMenu() {
        fillMaps();

        System.out.println("Ввійдіть в акаунт або зареєструйте його");
        chooseCommand(authentification, "Авторизуйтесь");

        exit = false;
        chooseCommand(menu, "\n--- Меню керування кредитами ---");

    }

    public void fillMaps(){
        authentification.put(1, new LogInCommand(scanner, userFilePath, userService)); // Логін користувача
        authentification.put(2, new RegistrationCommand(scanner, userFilePath, userService)); // Реєстрація нового користувача

        menu.put(1, new CompareCreditsCommand(scanner, creditsService)); // Порівняння кредитів
        menu.put(2, new SearchCreditByParametrsCommand(scanner, creditsService)); // Пошук кредиту за параметрами
        menu.put(3, new ShowAllCreditsCommand(creditsService)); // Показати всі кредити
        menu.put(4, new ReadCreditsFromFileCommand(creditsFilePath, creditsService)); // Читання кредитів з файлу
        menu.put(5, new CloseCreditCommand(scanner, userService, creditsService)); // Закриття кредиту
        menu.put(6, new TakeCreditCommand(scanner, userService, creditsService)); // Оформлення нового кредиту
        menu.put(7, new UpdateCreditLineCommand(scanner, userService)); // Оновлення кредитної лінії

        //menu.put(7, new WriteCreditsToFileCommand(creditsFilePath, credits)); // Запис кредитів у файл
    }


    public void chooseCommand(Map<Integer, Command> map, String text) {
        while (!exit)
        {
            System.out.println("0: Вихід");
            System.out.println(text);
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
                if (command != null) {
                    command.execute();
                }
            }
        }
    }
}


