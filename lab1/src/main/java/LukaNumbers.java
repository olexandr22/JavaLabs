
import java.util.Scanner;

/**
 * Клас для визначення, які з перших m чисел Люка є трикутними числами.
 * <p>
 * Числа Люка - це послідовність, подібна до чисел Фібоначчі, але з іншими початковими значеннями.
 * Трикутні числа - це числа, які можуть бути представлені у вигляді трикутника з крапок.
 * </p>
 * <p>
 * Приклад:
 * Якщо m = 10, то цей клас перевірить, які з перших 10 чисел Люка є трикутними.
 * </p>
 *
 * @author Olexandr Posatskyi
 * @version 1.0
 */
public class LukaNumbers {
    /**
     * Лічильник порядкового номера числа Люка.
     */
    private int indexOfLuka;

    /**
     * @param indexOfLuka Задання кількості чисел Люка, які мають бути згенеровані
     */
    private void setIndexOfLuka(int indexOfLuka) {
        this.indexOfLuka = indexOfLuka;
    }

    /**
     * Повертає поточне значення лічильника.
     *
     * @return Значення лічильника count.
     */
    public int getIndex() {
        return indexOfLuka;
    }

    /**
     * Перевіряє, чи є задане число трикутним.
     * <p>
     * Трикутне число - це число, яке можна подати у вигляді n*(n+1)/2 для деякого n.
     * </p>
     *
     * @param x Число, яке треба перевірити.
     * @return true, якщо число є трикутним, і false, якщо ні.
     */
    public static boolean isTriangular(int x) {
        int n = (int) (Math.sqrt(2 * x));
        return n * (n + 1) / 2 == x;
    }

    /**
     * Перевіряє кожне число Люка, чи є воно трикутним, і повертає список таких чисел.
     * <p>
     * Метод перевіряє кожне число Люка до m-го (включно) і додає його до списку, якщо воно є трикутним.
     * </p>
     *
     * @return Список трикутних чисел Люка.
     */
    public int[] generateLukaNumbers(int countOfNumbersToGenerate) {
        int[] lukaNums = new int[countOfNumbersToGenerate];
        lukaNums[0] = 2;
        lukaNums[1] = 1;
        for (int i = 2; i < countOfNumbersToGenerate; i++) {
            int temp = lukaNums[i - 1];
            lukaNums[i-1] += lukaNums[i - 2];
            lukaNums[i - 2] = temp;
        }
        return lukaNums;
    }

    /**
     * Основний метод для запуску програми.
     * <p>
     * Користувач вводить значення m, після чого відбувається перевірка перших m чисел Люка на те,
     * чи є вони трикутними. Результати виводяться в консоль.
     * </p>
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
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
