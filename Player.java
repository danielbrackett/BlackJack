class Player {

    public boolean isDealer;
    private Hand hand;
    private final String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
        setHand(new Hand());
        int bank = 100; //relates to the winnings. can they still play.
    }

    public void stand() {

    }

    public void hit() {

    }

    public void doubleDown() {

    }

    public void printPlayerName() {
        System.out.print(this.playerName);
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isWinningHand() {
        return getHand().isWinning();
    }

    private void setHand(Hand hand) {
        this.hand = hand;
    }
}
