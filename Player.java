class Player {

    private Hand hand;

    private String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
        setHand(new Hand());
        int bank = 100; //relates to the winnings. can they still play.
    }

    @Override
    public String toString() {
        return "Player{" + "playerName='" + playerName + '\'' + '}';
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Hand getHand() {
        return hand;
    }

    private void setHand(Hand hand) {
        this.hand = hand;
    }
}
