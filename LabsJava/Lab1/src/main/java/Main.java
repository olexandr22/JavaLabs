package main.java;

import java.util.Scanner;

import static main.java.LukaNumbers.isTriangular;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LukaNumbers lukaNum = new LukaNumbers();

        System.out.println("Введіть значення m: ");
        lukaNum.setIndexOfLuka(scanner.nextInt());

        for (int number : lukaNum.generateLukaNumbers(lukaNum.getIndex())) {
            if(isTriangular(number) && number != 0) {
                System.out.println("Знайдено трикутне число: " + number);
            }
        }

        scanner.close();
    }
}
