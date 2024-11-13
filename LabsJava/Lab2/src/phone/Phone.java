package phone;

public class Phone {
    private int id;
    private String lastName;
    private String firstName;
    private String fatherName;
    private int accountNumber;
    private int localCallTime;
    private int intercityCallTime;

    // Конструктор
    public Phone(int id, String lastName, String firstName, String patronymic, int accountNumber, int localCallTime, int intercityCallTime) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.fatherName = patronymic;
        this.accountNumber = accountNumber;
        this.localCallTime = localCallTime;
        this.intercityCallTime = intercityCallTime;
    }

    // Гетери
    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getLocalCallTime() {
        return localCallTime;
    }

    public int getIntercityCallTime() {
        return intercityCallTime;
    }

    // Сетери
    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFatherName(String patronymic) {
        this.fatherName = patronymic;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setLocalCallTime(int localCallTime) {
        this.localCallTime = localCallTime;
    }

    public void setIntercityCallTime(int intercityCallTime) {
        this.intercityCallTime = intercityCallTime;
    }

    // Метод toString
    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", localCallTime=" + localCallTime +
                ", intercityCallTime=" + intercityCallTime +
                '}';
    }

    public void printBiggerLocalCallTime(int time) {
        if(localCallTime > time){
            System.out.println(this);
        }
    }

    public boolean isInRange(int start, int end) {
        if(accountNumber > start && accountNumber < end){
            return true;
        }
        return false;
    }
}
