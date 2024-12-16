package AdditionalFunctions;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import droids.AtackerDroid;
import droids.Droid;
import droids.SniperDroid;
import droids.TankDroid;
import battle.Battle;

public class Menu {
    static List<Droid> droids = new ArrayList<>();
    static String battleResult;

    public static void startMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do{
            displayOptions();
            System.out.println("Зробіть свій вибір");
            choice = scanner.nextInt();
            switch(choice){
                case 1:
                    createDroid();
                    break;
                case 2:
                    displayDroids();
                    break;
                case 3:
                    start1v1Battle();
                    break;
                case 4:
                    startTeamBattle();
                    break;
                case 5:
                    writeBattleTofile();
                    break;
                case 6:
                    displayBattleFromFile();
                    break;
                case 7:
                    writeToBinaryFile();
                    break;
                case 8:
                    readFromBinaryFile();
                    break;
                case 9:
                    deleteDroid();
                    break;
                case 10:
                    System.out.println("Дякую за увагу!");
                    return;
                default:
                    System.out.println("Не ламайте програму!");
            }
        }while(choice != 10);

    }
    public static void displayOptions(){
        System.out.println("\t-----Головне меню програми-----");
        System.out.println("[1] - Створити дроїда");
        System.out.println("[2] - Показати список створених дроїдів");
        System.out.println("[3] - Запустити бій 1 на 1 (вибрати дроїдів, які будуть змагатися)");
        System.out.println("[4] - Запустити бій команда на команду");
        System.out.println("[5] - Записати проведений бій у файл;");
        System.out.println("[6] - Відтворити проведений бій зі збереженого файлу;");
        System.out.println("[7] - Записати даний список дроїдів до бінарного файлу");
        System.out.println("[8] - Зчитати дані з бінарного файлу");
        System.out.println("[9] - Видалити дроїда за індексом");
        System.out.println("[10] - Вийти з програми");
    }

    public static void createDroid(){
        Droid userDroid;
        Scanner scanner = new Scanner(System.in);
        System.out.println("(1) - Ввести всі дані самостійно\n(2) - Ввести тільки ім'я(програма автоматично визначить дані дрона, згідно його типу)");
        int choice = scanner.nextInt();
        System.out.println("Введіть тип дроїда\n[1] - Tank\n[2] - Atacker\n[3] - Sniper");
        int type = scanner.nextInt();
        System.out.println("Введіть ім'я для дроїда");
        String name = scanner.next();

        int[] userData = enterData(choice);
        switch(type){
            case 1:
                userDroid = new TankDroid(name);
                if(userData != null){
                    userDroid.setHealth(userData[0]);
                    userDroid.setDamage(userData[1]);
                }
                droids.add(userDroid);
                break;
            case 2:
                userDroid = new AtackerDroid(name);
                if(userData != null){
                    userDroid.setHealth(userData[0]);
                    userDroid.setDamage(userData[1]);
                }
                droids.add(userDroid);
                break;
            case 3:
                userDroid = new SniperDroid(name);
                if(userData != null){
                    userDroid.setHealth(userData[0]);
                    userDroid.setDamage(userData[1]);
                }
                droids.add(userDroid);
                break;
            default:
                System.out.println("Ви помилилися при вводі інформації");
                break;
            }
    }

    public static int[] enterData(int choice){
        Scanner scanner = new Scanner(System.in);
        int[] data = new int[2];
        if (choice == 1){
            System.out.println("Почергово введіть здоров'я та шкоду дроїда");
            data[0] = scanner.nextInt();
            data[1] = scanner.nextInt();
        }
        else {
            data = null;
        }
        return data;
    }

    public static void displayDroids(){
        if(droids.isEmpty()){
            System.out.println("Зараз немає створених дроїдів");
        }
        else {
            System.out.println("Список дроїдів");
            for (int i = 0; i < droids.size(); i++) {
                System.out.println(i + 1 + "-ий: " + droids.get(i));
            }
        }
    }

    public static void start1v1Battle(){
        Scanner scanner = new Scanner(System.in);
        displayDroids();
        System.out.println("Виберіть дроїдів(за індексом) для поєдинку 1 на 1, якшо вони створені");
        int index1 = scanner.nextInt();
        int index2 = scanner.nextInt();
        Battle battle = new Battle();
        battleResult = battle.fight1vs1(droids.get(index1 - 1), droids.get(index2 - 1));
    }

    public static void startTeamBattle(){
        Scanner scanner = new Scanner(System.in);
        displayDroids();
        System.out.println("Скільки дроїдів буде у 1 та 2 команді");
        int team1Size = scanner.nextInt();
        int team2Size = scanner.nextInt();
        Droid[] droidsTeam1 = new Droid[team1Size];
        Droid[] droidsTeam2 = new Droid[team2Size];
        System.out.println("Виберіть дроїдів(за індексом) для командного поєдинку, якшо вони створені");
        for(int i = 0; i < team1Size; i++){
            int index = scanner.nextInt();
            droidsTeam1[i] = droids.get(index - 1);
        }
        System.out.println("Друга команда");
        for(int i = 0; i < team2Size; i++){
            int index = scanner.nextInt();
            droidsTeam2[i] = droids.get(index - 1);
        }

        Battle battle = new Battle();
        battleResult = battle.TeamFight(droidsTeam1, droidsTeam2);
    }

    public static void writeBattleTofile(){
        try (FileWriter writer = new FileWriter("Lab3/src/ProgFiles/battleResult.txt")) {
            writer.write(battleResult);
            System.out.println("Дані успішно записані до файлу");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayBattleFromFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader("Lab3/src/ProgFiles/battleResult.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToBinaryFile(){
        if (!droids.isEmpty()){
            try (FileOutputStream fileOut = new FileOutputStream("Lab3/src/ProgFiles/droidList.dat");
                 ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

                objectOut.writeObject(droids);
                System.out.println("Дані успішно записані до файлу");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readFromBinaryFile(){
        try (FileInputStream fileIn = new FileInputStream("Lab3/src/ProgFiles/droidList.dat");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            droids = (ArrayList<Droid>) objectIn.readObject();
            System.out.println("Дані успішно зчитані з файлу");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        displayDroids();
    }

    public static void deleteDroid(){
        Scanner scanner = new Scanner(System.in);
        displayDroids();
        System.out.println("Введіть індекс дроїда, якого ви хочете видалити");
        int index = scanner.nextInt() - 1;
        if(droids.get(index) != null){
            droids.remove(index);
        }
        else{
            System.out.println("Не знайшлось такого дроїда");
        }
    }
}
