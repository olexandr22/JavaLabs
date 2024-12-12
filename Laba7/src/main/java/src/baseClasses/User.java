package src.baseClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String password;
    private List<Credit> credits;
    private int successfulCredits;
    private int creditLine;


    public User(String name, int creditLine, int successfulCredits, String password, List<Credit> credits) {
        this.name = name;
        this.creditLine = creditLine;
        this.successfulCredits = successfulCredits;
        this.password = password;
        this.credits = credits;
    }

    public User(){
        this.successfulCredits = 0;
        this.credits = new ArrayList<Credit>();
    }

    // Getters and Setters


    public void setPassword(String password) {
        this.password = password;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(int creditLine) {
        this.creditLine = creditLine;
    }

    public int getSuccessfulCredits() {
        return successfulCredits;
    }

    public void setSuccessfulCredits(int successfulCredits) {
        this.successfulCredits = successfulCredits;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", credits=" + credits +
                ", successfulCredits=" + successfulCredits +
                ", creditLine=" + creditLine +
                '}';
    }

    public void addSuccessfulCredit() {
        successfulCredits++;
    }
}
