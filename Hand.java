import java.util.ArrayList;

public class Hand {

     final ArrayList<Card> cardsInHand;
    private boolean isWinningHand;
    private int valueOfCards;

    public Hand() {
        cardsInHand = new ArrayList<>(); //cards dealt to a player not to exceed 21.
    }

    public void addCardToHand(Card card) {
        this.cardsInHand.add(card);
    }

    public int valueOfCardsInHand() {
        for (Card card: this.cardsInHand) {
            valueOfCards += card.getCardValueEnum().getCardPoints();
        }
        return valueOfCards;
    }

    public void printCardsInHand() {
        for (Card card: cardsInHand) {
            System.out.println(card.ToString());
        }
    }

    public boolean isWinning() {
        return isWinningHand;
    }

    public void setWinningHand(boolean b) {
        isWinningHand = b;
    }


    public int getHandSize() {
        return  cardsInHand.size();
    }
}
