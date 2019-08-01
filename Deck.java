import java.util.ArrayList;
import java.util.Random;


public class Deck{

    private final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private ArrayList<Card> deck = new ArrayList<>(52);

    public Deck() {
        this.createDeck();
    }

    /*
    make one card of each value(1-13) for each suit.
    iterate over the suits and for each one make a value 1-13
    */
    private void createDeck() {
        for (String suitName : SUITS) {
            for (int i = 1; i <= 13; i++) {
                Card c = new Card(suitName, i);
                deck.add(c);
            }
        }
    }

    public void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < deck.size(); i++) {
            Card currentCard = deck.get(i);
            int value = rand.nextInt(86317389);
            int swapIndex = value % this.getDeckSize();
            Card temp = deck.get(swapIndex);
            deck.set(swapIndex, currentCard);
            deck.set(i, temp);
        }
    }

    public int getDeckSize() {
        return deck.size();
    }
    
    public void printDeck() {
        for (Card card : deck) {
            System.out.println(card.ToString());
        }
    }

}
