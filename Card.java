import java.util.ArrayList;

public class Card{

    /* Constructor */

    public Card(String suit, int value) {
        this.suit = suit; //
        this.value = value; //values are 1-13 the game should calculate the value for each card since many games
        // assign values to cards differently.
    }
        public String getSuit() { return suit; }

        public int getValue() { return value; }

        public String ToString() {
            return "the " + this.getValue() + " of " + this.getSuit();
        }

    private final String suit;
    private final int value;
}
