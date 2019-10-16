class Player {

    private Hand hand;
    private String playerName;
    protected Tokens tokens;

    public Player(String playerName) {
        this.playerName = playerName;
        setHand(new Hand());
        setTokens(new Tokens());
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

    public boolean isHandEnmpty() {
        return hand.isHandEmpty();
    }

    public Tokens getTokens() { return tokens; }

    private void setHand(Hand hand) {
        this.hand = hand;
    }

    private void setTokens(Tokens tokens) { this.tokens = tokens; }

    public int placeBet(int amount) {
        tokens.total = tokens.total - amount;
        return amount;
    }
}
