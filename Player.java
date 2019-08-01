public class Player {

    public boolean isDealer;
    private Hand hand;

    public Player(){
        setHand(new Hand());
        int bank = 100; //relates to the winnings. can they still play.
    }

    public void stand() {

    }

    public void hit() {

    }

    public void doubleDown() {

    }

    protected Hand getHand() {
        return hand;
    }

    protected void setHand(Hand hand) {
        this.hand = hand;
    }
}
