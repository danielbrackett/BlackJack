import java.util.List;

class Card{

    private final String suit;
    private final int value;
    private final List<String> suits = new gstList<String>;

    /* Constructor */
    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

        public String getSuit(Card c){
            return c.suit;
        }

        public int getValue(Card c){
            return c.value;
        }

    }