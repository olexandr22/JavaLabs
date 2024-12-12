package src.menu;

import src.appLogic.CreditsService;
import src.appLogic.UserService;
import src.commands.Command;
import src.commands.creditListCommands.CompareCreditsCommand;
import src.commands.creditListCommands.SearchCreditByParametrsCommand;
import src.commands.creditListCommands.ShowAllCreditsCommand;
import src.commands.fileActionCommands.ReadCreditsFromFileCommand;
import src.commands.userCreditCommans.CloseCreditCommand;
import src.commands.userCreditCommans.TakeCreditCommand;
import src.commands.userCreditCommans.UpdateCreditLineCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SubMenu implements Command {
    protected Scanner scanner;

    protected CreditsService creditsService;
    protected UserService userService;
    protected String userFilePath;
    protected String creditsFilePath;

    protected boolean exit = false;
    protected Map<Integer, Command> menuMap = new HashMap<>();

    public SubMenu(Scanner scanner, CreditsService creditsService, UserService userService, String userFilePath, String creditsFilePath) {
        this.scanner = scanner;
        this.creditsService = creditsService;
        this.userService = userService;
        this.userFilePath = userFilePath;
        this.creditsFilePath = creditsFilePath;
    }


    public void fillMap(){
        menuMap.put(1, new CompareCreditsCommand(scanner, creditsService)); // Порівняння кредитів
        menuMap.put(2, new SearchCreditByParametrsCommand(scanner, creditsService)); // Пошук кредиту за параметрами
        menuMap.put(3, new ShowAllCreditsCommand(creditsService)); // Показати всі кредити
        menuMap.put(4, new ReadCreditsFromFileCommand(creditsFilePath, creditsService)); // Читання кредитів з файлу
        menuMap.put(5, new CloseCreditCommand(scanner, userService, creditsService)); // Закриття кредиту
        menuMap.put(6, new TakeCreditCommand(scanner, userService, creditsService)); // Оформлення нового кредиту
        menuMap.put(7, new UpdateCreditLineCommand(scanner, userService)); // Оновлення кредитної лінії
    }

    @Override
    public String getDescription() {
        return "Sub menu";
    }

    public void chooseCommand(Map<Integer, Command> map, String text) {
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
                System.exit(0);
            }
            else {
                Command command = map.get(choice);
                if (command != null) {
                    command.execute();
                }
            }
        }
    }

    @Override
    public void execute() {
        fillMap();

        chooseCommand(menuMap, "\n--- Меню керування кредитами ---");
    }
}
