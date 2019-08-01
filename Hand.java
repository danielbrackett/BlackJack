import java.util.ArrayList;

public class Hand {

    public int valueOfCards;
    public ArrayList<Card> cardsInHand;

    public Hand() {
        cardsInHand = new ArrayList<>(); //cards dealt to a player not to exceed 21.
    }

    public int valueOfCardsinHand() {


        return valueOfCards;
    }

    public int getNumberOfCardsInHand() {
        return  cardsInHand.size();
    }
}
