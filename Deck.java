import java.util.ArrayList;
import java.util.Random;


public class Deck{

    public Deck deck;
    public String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private ArrayList<Card> cards = new ArrayList<>(52);

    public Deck() {
        deck.createDeck();
    }

    public Deck shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            Random rand = new Random();
            int value = rand.nextInt(86317389);
            int swapIndex = value % this.getDeckSize();
            Card temp = cards.get(swapIndex);
            cards.set(swapIndex, currentCard);
            cards.set(i, temp);
        }
        return deck;
    }

    /*
    make one card of each value(1-13) for each suit.
    iterate over the suits and for each one make a value 1-13
    */
    public ArrayList<Card> createDeck() {
        for (String suitName : SUITS) {
            for (int i = 1; i <= 13; i++) {
                Card c = new Card(suitName, i);
                cards.add(c);
            }
        }
        return cards;
    }

    public int getDeckSize() {
        return cards.size();
    }

}
