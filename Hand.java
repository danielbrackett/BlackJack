import java.util.ArrayList;

public class Hand {

    public int valueOfCards;
    public ArrayList<Card> cardsInHand;

    public Hand() {
        cardsInHand = new ArrayList<>(); //cards dealt to a player not to exceed 21.
    }

    public void addCardToHand(Card card) {
        this.cardsInHand.add(card);
    }

    public int valueOfCardsinHand() {
        return valueOfCards;
    }

    public void printCardsInHand() {
        for (Card card: cardsInHand) {
            System.out.println(card.ToString());
        }
    }

    public int getHandSize() {
        return  cardsInHand.size();
    }
}
