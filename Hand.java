import java.util.ArrayList;

public class Hand {

    public int valueOfCards;

    public Hand() {
        boolean isDealer; //necessary if there is a tie between hands, the dealer wins.
        ArrayList<Card> cardsInHand = new ArrayList<>(); //cards dealt to a player not to exceed 21.
    }

    public int valueOfCardsinHand() {

        return valueOfCards;
    }

}
