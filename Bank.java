public class Bank {

    private int balance;

    Bank() {
        this.balance = 1000; //simoleons
    }

    int getBalance() {
        return balance;
    }

    int withdraw(int amount) {
        return balance -= amount;
    }

    int deposit(int amount) {
        return balance += amount;
    }
}
