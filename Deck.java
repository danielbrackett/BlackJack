import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Deck {
    //add a discard pile. so that if it is necessary to add the discard pile to the remaining cards in the deck and
    // then shuffle the hybrid deck and then deal as necessary to
    // finish a round.

    private final ArrayList<Card> deck = new ArrayList<>(52);
    private final ArrayList<Card> discardPile = new ArrayList<>();
    //refactor to a Queue. Problem; Cannot easily
//    shuffle a queue. Options keep as ArrayList<Card>, switch to a LinkedList<Card> to take advantage of poll(),
//    peek() and offer() methods, switch to a Queue but to shuffle the 'Deck' obj. 1. convert to an Obj[] 2.
//    shuffle, 3. foreach or ?Iterator? it back into a queue. Why switch to a Queue? to deny malicious access to
//    the deck, make it harder to cheat.
//    private final List<Card> deck = new LinkedList<Card>;

    public Deck() {
        this.createDeck();
    }

    /**
     * make one card of each value(1-13) for each suit.
     * iterate over the suits and for each one make a value 1-13
     */
    private void createDeck() {
        for (Suit suitName : Suit.values()) {
            for (int i = 0; i <= 12; i++) {
                final Card c = new Card(suitName, CardValueEnum.values()[i]);
                deck.add(c);
            }
        }
    }

    /**
     * home brewed shuffle method.
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
//        System.out.println(cardDealt + " which was just dealt.");
        return cardDealt;
    }

    /**
     * @return the total number of cards remaining in the deck in play.
     */
    public int getDeckSize() {
        return deck.size();
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    /**
     * Prints all the cards in the deck to the terminal.
     */
    public void printDeck() {
        for (Card card : deck) {
            System.out.println(card);
        }
    }


    public void moveCardsToDiscardPile(ArrayList<Card> cardsInHand) {
        discardPile.addAll(cardsInHand);
    }
}
