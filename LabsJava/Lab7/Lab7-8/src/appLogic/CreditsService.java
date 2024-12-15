package appLogic;

import baseClasses.Credit;
import commands.creditListCommands.ShowAllCreditsCommand;
import menu.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class CreditsService {
    private List<Credit> credits = new ArrayList<Credit>();
    private static final Logger logger = LogManager.getLogger(CreditsService.class);

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public void showAllCredits() {
        if(credits.isEmpty()) {
            System.out.println("No credits found");
        }
        else {
            int index = 1;
            for (Credit credit : credits) {
                System.out.println(index++ + ") " + credit.toString());
            }
        }
    }

    public void compareCredits(Scanner scanner) {
        Credit credit1;
        Credit credit2;

        System.out.println("Виберіть два кредити: ");

        try {
            int index1 = scanner.nextInt();
            scanner.nextLine(); // Очищуємо буфер
            int index2 = scanner.nextInt();
            scanner.nextLine(); // Очищуємо буфер

            if (index1 > 0 && index1 <= credits.size() && index2 > 0 && index2 <= credits.size()) {
                credit1 = credits.get(index1 - 1);
                credit2 = credits.get(index2 - 1);

                System.out.println("\t\tПорівняння кредитів " + credit1.getName() + " та " + credit2.getName());
                System.out.println("Банк: \t" + credit1.getBank() + "\t|" + credit2.getBank());
                System.out.println("Проценти: \t" + credit1.getPercents() + "\t|" + credit2.getPercents());
                System.out.println("Час: \t" + credit1.getTime() + "\t|" + credit2.getTime());
                System.out.println("Максимальна сума: \t" + credit1.getMoneyAmount() + "\t|" + credit2.getMoneyAmount());
            } else {
                System.out.println("Неправильний індекс кредиту.");
            }

        } catch (Exception e) {
            logger.error("Критична помилка: ", e);
        }
    }

    public void searchCreditsByParametrs(Scanner scanner) {
        try {
            System.out.println("Введіть параметр, по якому ви хочете знайти кредити\n1 - Проценти\n2 - Час\n3 - Максимальна сума");
            int option = scanner.nextInt();
            System.out.println("По зростанню?\t 1 - Tak \t 0 - Hi");
            int asc = scanner.nextInt();
            switch (option) {
                case 1:
                    sortByComparator(asc, Comparator.comparingDouble(Credit::getPercents));
                    break;
                case 2:
                    sortByComparator(asc, Comparator.comparingDouble(Credit::getTime));
                    break;
                case 3:
                    sortByComparator(asc, Comparator.comparingDouble(Credit::getMoneyAmount));
                    break;
                default:
                    System.out.println("Немає такого параметра");
            }
        }
        catch (Exception e) {
            logger.error("Критична помилка: ", e);
        }
    }

    public void sortByComparator(int asc, Comparator<Credit> comparator) {
        credits.sort(comparator);
        if(asc == 0){
            Collections.reverse(credits);
        }
        showAllCredits();
    }

    public void readCredits(String filePath) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            credits = (List<Credit>) objectIn.readObject();
            System.out.println("Дані успішно зчитані з файлу");
            showAllCredits();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("Критична помилка: ", e);
        }
    }
}
