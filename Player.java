class Player {

    private Bank bank;
    private Hand hand;
    private String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
        setHand(new Hand());
        setBank(new Bank());
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }

    public Hand getHand() {
        return hand;
    }

    private void setHand(Hand hand) {
        this.hand = hand;
    }

    public boolean isHandEmpty() {
        return hand.isHandEmpty();
    }

    public Bank getBank() {
        return bank;
    }

    private void setBank(Bank bank) {
        this.bank = bank;
    }
}
