package baseClasses;

public class CreditWithoutPercents extends Credit{
    public CreditWithoutPercents(String name, String bank, int time, int moneyAmount) {
        super(name, bank, 0, time, moneyAmount);
    }
}
