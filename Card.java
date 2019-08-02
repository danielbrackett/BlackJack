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
            if (this.getValue() >= 2 && this.getValue() <= 10) {
                return "the " + this.getValue() + " of " + this.getSuit();
            } else if (this.getValue() == 1) {
                return "the Ace of " + this.getSuit();
            } else if (this.getValue() == 11) {
                return "the Jack of " + this.getSuit();
            } else if (this.getValue() == 12) {
                return "the Queen of " + this.getSuit();
            }  else if (this.getValue() == 13) {
                return "the King of " + this.getSuit();
            } else {
                return "Card Value Error. value is outside of acceptable range.";
            }
        }

    private final String suit;
    private final int value;
}
