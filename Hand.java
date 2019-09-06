import java.util.ArrayList;

public class Hand {

    private final ArrayList<Card> cardsInHand;

    public Hand() {
        cardsInHand = new ArrayList<>();
    }

    public void addCardToHand(Card card) {
        this.cardsInHand.add(card);
    }

    public void discardAllCardsFromHand() {
        this.cardsInHand.clear();
    }

    public void putInDiscardPile(Deck deck) {
        deck.discardPile.addAll(cardsInHand);
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    /**
     * this valueOfCardsInHand is simple and known to work. It accounts for the dual nature of the ACE card.
     *
     * @return the value of the cards in a given players Hand Obj.
     */
    public int valueOfCardsInHand() {
        int value = 0;
        for (Card card : this.cardsInHand) {
            value += card.getCardValueEnum().getCardPoints();
        }
        if (value <= 21) {
            return value;
        }
        int numberOfAces = 0;
        for (Card card : this.cardsInHand) {
            if (card.getCardValueEnum() == CardValueEnum.ACE) {
                numberOfAces += 1;
            }
        }
        int numberOfSubtractableAces = numberOfAces;
        while (value > 21 && numberOfSubtractableAces > 0) {
            value -= 10;
            numberOfSubtractableAces -= 1;
        }
        return value;
    }

    @Override
    public String toString() {
        StringBuilder retVal = new StringBuilder();
        for (Card card : cardsInHand) {
            retVal.append(card.toString());
            if (cardsInHand.indexOf(card) != (cardsInHand.size() - 1)) {
                retVal.append(", ");
            }
        }
        retVal.append(". Total value: ").append(valueOfCardsInHand());
        return retVal.toString();
    }
}
