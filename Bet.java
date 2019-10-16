public class Bet {

    private final int amount = 5;
    private int pot = 0;

    public int getBetAmount() {
        return amount;
    }

    public int getPot() {
        return pot;
    }

    public void addToPot(int amount) {
        pot = amount + this.getPot();
    }
}
