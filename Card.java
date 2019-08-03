import java.util.ArrayList;

public class Card{

    /* Constructor */

    public Card(Suit suit, CardValueEnum cardValue) {
        this.suit = suit; //
        this.cardValue = cardValue; //values are 1-13 the game should calculate the value for each card since many games
        // assign values to cards differently.
    }
        public Suit getSuit() { return suit; }

        public CardValueEnum getCardValueEnum() { return cardValue; }

        public String ToString() {
                return "the " + this.getCardValueEnum() + " of " + this.getSuit();
        }

    private final Suit suit;
    private final CardValueEnum cardValue;
}
