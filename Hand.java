import java.util.ArrayList;

public class Hand {

    public final ArrayList<Card> cardsInHand;

    public Hand() {
        cardsInHand = new ArrayList<>(); //cards dealt to a player not to exceed 21.
    }

    public void addCardToHand(Card card) {
        this.cardsInHand.add(card);
    }

    public void discardAllCardsFromHand() {
        this.cardsInHand.clear();
    }

    public int valueOfCardsInHand() {
        int value = 0;
        for (Card card : this.cardsInHand) {
            value += card.getCardValueEnum().getCardPoints();
        }
        return value;
    }

    public int getHandSize() {
        return cardsInHand.size();
    }

    @Override
    public String toString() {
        String retVal = "";
        for (Card card : cardsInHand) {
            retVal += card.toString();
            if (cardsInHand.indexOf(card) != cardsInHand.size()) {
                retVal += ", ";
            }
        }
        retVal += ". Total value: " + valueOfCardsInHand();
        return retVal;
    }
}
