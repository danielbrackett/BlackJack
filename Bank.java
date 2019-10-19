public class Bank {

    private int balance;

    Bank() {
        this.balance = 100; //simoleons
    }

    int getBalance() {
        return balance;
    }

    void withdraw(int amount) {
        balance -= amount;
    }

    void deposit(int amount) {
        balance += amount;
    }
}
