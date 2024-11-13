import phone.Phone;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static Phone[] phones = generateAbonents();

    public static void main(String[] args) {
//        printLocalCallOver();
//
//        printIntersityUsers();
//
//        printAccountNumberInRange();

        printAvgLocalCallTime();

    }

    public static Phone[] generateAbonents(){
        Phone[] phones = new Phone[5];

        phones[0] = new Phone(1, "Іванов", "Іван", "Іванович" ,27834, 120, 15);
        phones[1] = new Phone(2, "Петров", "Петро", "Петрович", 23456, 80, 0);
        phones[2] = new Phone(3, "Сидоров", "Сидір", "Сидорович", 34567, 150, 30);
        phones[3] = new Phone(4, "Коваленко", "Олександр", "Олександрович", 45678, 60, 0);
        phones[4] = new Phone(5, "Гриценко", "Григорій", "Григорович", 56789, 200, 25);

        return phones;
    }

    public static void printLocalCallOver() {
        System.out.println("Введіть кількість міських розмов: ");
        int time = scanner.nextInt();
        System.out.println("Абоненти з часом міських розмов понад " + time + ":");
        for (Phone item : phones) {
            item.printBiggerLocalCallTime(time);
        }
    }

    public static void printIntersityUsers() {
        System.out.println("Абоненти, які користуються міжміськими розмовами: ");
        for (Phone item : phones) {
            if(item.getIntercityCallTime() != 0){
                System.out.println(item);
            }
        }
    }

    public static void printAccountNumberInRange() {
        int startOfRange, endOfRange;
        System.out.println("Введіть діапазон, у якому мають знаходитись номери: ");
        startOfRange = scanner.nextInt();
        endOfRange = scanner.nextInt();
        for(Phone item : phones){
            if(item.isInRange(startOfRange, endOfRange)){
                System.out.println(item);
            }
        }
    }

    public static void printAvgLocalCallTime() {
        int avg = 0;
        for(Phone item : phones){
            avg += item.getLocalCallTime();
        }
        System.out.println("Середнє значення міських розмов: " + avg/phones.length);
    }
}

