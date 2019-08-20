import org.jetbrains.annotations.Contract;

public class Card {

    private final Suit suit;
    private final CardValueEnum cardValue;

    /**
     * This is the constructor for the Card Obj.
     *
     * @param suit      this is the suit of a card, CLUBS, DIAMONDS, HEARTS, SPADES
     * @param cardValue this is the value for each card for the game of BlackJack.
     */
    @Contract(pure = true)
    public Card(final Suit suit, final CardValueEnum cardValue) {
        this.suit = suit; //
        this.cardValue = cardValue;
    }

    public Suit getSuit() {
        return suit;
    }

    public CardValueEnum getCardValueEnum() {
        return cardValue;
    }

    @Override
    public String toString() {
        return "the " + this.getCardValueEnum() + " of " + this.getSuit();
    }
}
