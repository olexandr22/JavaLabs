package appLogic;

import baseClasses.Credit;
import baseClasses.User;
import commands.creditListCommands.ShowAllCreditsCommand;
import commands.fileActionCommands.WriteUserInfoCommand;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private User user = new User();

    public void closeCredit(Scanner scanner, CreditsService creditService) {
        List<Credit> credits = user.getCredits();
        if(credits.isEmpty()){
            System.out.println("У вас немає кредитів");
        }
        else {
            int i = 1;
            for (Credit credit : credits) {
                System.out.println(i++ + ") " + credit);
            }
            System.out.println("Який кредит ви хочете погасити?: ");
            int number = scanner.nextInt();

            if(credits.get(number - 1) != null){
                credits.remove(credits.get(number-1));
                user.setCredits(credits);
                user.addSuccessfulCredit();
                System.out.println(user);
            }
            else{
                System.out.println("Неправильний індекс");
            }
        }
    }
    public void takeCredit(Scanner scanner, CreditsService creditService){
        List<Credit> credits = creditService.getCredits();

        ShowAllCreditsCommand showAllCreditsCommand = new ShowAllCreditsCommand(creditService);
        showAllCreditsCommand.execute();

        System.out.println("Введіть номер кредиту, який ви хочете взяти: ");
        int number = scanner.nextInt() - 1;

        List<Credit> userCredits = user.getCredits();
        userCredits.add(credits.get(number));
        user.setCredits(userCredits);
        System.out.println(user);
    }

    public void updateCreditLine(Scanner scanner){
        System.out.println("Введіть нову кредитну лінію");
        int newCreditLine = scanner.nextInt();
        user.setCreditLine(newCreditLine);
        System.out.println(user);
    }

    public void registerUser(Scanner scanner, String filePath){
        System.out.println("По черзі введіть такі дані про акаунт:\n" +
                "Ім'я, пароль, свою кредитну лінію");

        String name = scanner.nextLine();
        String password = scanner.nextLine();
        int creditLine = scanner.nextInt();

        user.setName(name);
        user.setPassword(password);
        user.setCreditLine(creditLine);

        WriteUserInfoCommand write = new WriteUserInfoCommand(user, filePath);
        write.execute();
    }

    public void loginUser(Scanner scanner, String filePath){
        System.out.println("Введіть своє ім'я: ");
        String name = scanner.nextLine();
        System.out.println("Введіть свій пароль");
        String password = scanner.nextLine();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                User readedUser;
                readedUser = (User) ois.readObject();

                if(readedUser.getName().equals(name) && readedUser.getPassword().equals(password)) {
                    user = readedUser;
                    System.out.println("Вітаю, ви увійшли в акаунт\n" + user);
                    break;
                }
            }
        } catch (EOFException e) {
            System.out.println("Читання з файлу завершено.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
