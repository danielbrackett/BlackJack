public class Bank {

    int total;
    int initialTotal = 0;

    public int getTotal() {
        return total;
    }

    public int calculateWinnings() {
        return getTotal() - initialTotal;
    }

    public void addMoneyToBank(int money) {
        total += money;
    }

}
