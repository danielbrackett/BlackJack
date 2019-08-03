import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Deck{

    private ArrayList<Card> deck = new ArrayList<>(52);

    public Deck() {
        this.createDeck();
    }

    /**
     * make one card of each value(1-13) for each suit.
     *     iterate over the suits and for each one make a value 1-13
     */
    private void createDeck() {
        for (Suit suitName: Suit.values()) {
            for (int i = 0; i <= 12; i++) {
                Card c = new Card(suitName, CardValueEnum.values()[i]);
                deck.add(c);
            }
        }
    }

    /**
     * self built shuffle method.
     */
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

    /**
     * taking advantage of the Collections.shuffle() Thanks Java!
     */
    public void easyShuffle() {
        Collections.shuffle(deck);
    }

    public Card dealOneCardFromDeck() {
        Card cardDealt = deck.get(0);
        deck.remove(0);
        System.out.println(cardDealt.ToString()+ " which was just dealt.");
        return cardDealt;
    }

    /**
     *
     * @return the total number of cards remaining in the deck in play.
     */
    public int getDeckSize() {
        return deck.size();
    }

    /**
     * Prints all the cards in the deck to the terminal.
     */
    public void printDeck() {
        for (Card card : deck) {
            System.out.println(card.ToString());
        }
    }

}
