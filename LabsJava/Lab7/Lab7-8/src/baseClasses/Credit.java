package baseClasses;

import java.io.Serializable;

public class Credit implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String name;
    protected String bank;
    protected double percents;
    protected int time;
    protected int moneyAmount;

    public Credit(String name, String bank, double percents, int time, int moneyAmount) {
        this.name = name;
        this.bank = bank;
        this.percents = percents;
        this.time = time;
        this.moneyAmount = moneyAmount;
    }
    public Credit() {

    }

    public String getName() {
        return name;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public int getTime() {
        return time;
    }

    public double getPercents() {
        return percents;
    }

    public String getBank() {
        return bank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setPercents(int percents) {
        this.percents = percents;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "name='" + name + '\'' +
                ", bank='" + bank + '\'' +
                ", percents=" + percents +
                ", time=" + time +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
