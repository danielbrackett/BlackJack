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

//    public void addWinningsToTokens(int winnings) {
//        total += winnings;
//    }
//
//    public void subtractLossesFromTokens(int losses) { total += losses; }

}
