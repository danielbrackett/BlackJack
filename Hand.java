import java.util.ArrayList;

public class Hand {

    private final ArrayList<Card> cardsInHand;

    public Hand() {
        cardsInHand = new ArrayList<>(); //cards dealt to a player not to exceed 21.
    }

    public void addCardToHand(Card card) {
        this.cardsInHand.add(card);
    }

    public void discardAllCardsFromHand() {
        this.cardsInHand.clear();
    }

//    public void putInDiscardPile(Deck deck) {
//        deck.moveCardsToDisCardPile(cardsInHand);
//    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    /**
     * this valueOfCardsInHand is simple and known to work but does't account for the cae card and it's dual nature.
     * added a new valueOfCardsInHand2() to take into account when 1 or more ACE card forces the
     * value of the cardsInHand over 21.
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
//        if (value > 21) {
//            value = valueOfCardsInHand2(value);
//        }
        return value;
    }

    /**
     * This valueOfCardsInHand2 is to account for the sometimes low value of teh ACE card.
     *
     * @return this will return the value of a Hand Obj. playing at least one ACE as low.
     */
    public int valueOfCardsInHand2(int originalValue) {
        int value = originalValue;
        while (value > 21) {
            for (Card card : this.cardsInHand) {
                if (card.getCardValueEnum() == CardValueEnum.ACE) {
                    value -= 10;
                }
            }
        }
        return value;
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
