import java.util.*;

class Deck {
    //add a discard pile. so that if it is necessary to add the discard pile to the remaining cards in the deck and
    // then shuffle the hybrid deck and then deal as necessary to
    // finish a round.

    final ArrayList<Card> discardPile = new ArrayList<>();
    private final ArrayDeque<Card> deck = new ArrayDeque<>(52);

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

    private void createMultipleDecks() {
        /*
        join <N> decks together to make a multi-deck of size <N> * 52
         */
    }

    /**
     * home brewed shuffle method. dump the ArrayDeque<Card> -> Card[] -> ArrayList<Card>
     * then shuffle, then return
     * the ArrayDeque.
     */
    public void shuffle() {
        ArrayList<Card> deckAL = new ArrayList<>(Arrays.asList(deck.toArray(new Card[51])));
        Random rand = new Random();
        for (int i = 0; i < deckAL.size(); i++) {
            Card currentCard = deckAL.get(i);
            int value = rand.nextInt(86317389);
            int swapIndex = value % getDeckSize();
            Card temp = deckAL.get(swapIndex);
            deckAL.set(swapIndex, currentCard);
            deckAL.set(i, temp);
        }
        deck.clear();
        deck.addAll(deckAL);
    }

    /**
     * home brewed shuffle method. dump the ArrayDeque<Card> -> Card[]
     * then shuffle, then return
     * the ArrayDeque.
     */
    public void shuffle2() {
        Card[] deckToShuffle = deck.toArray(new Card[51]);
        Random rand = new Random();
        for (int i = 0; i < deckToShuffle.length; i++) {
            Card currentCard = deckToShuffle[i];
            int value = rand.nextInt();
            int swapIndex = value % deckToShuffle.length;
            Card temp = deckToShuffle[swapIndex];
            deckToShuffle[swapIndex] = currentCard;
            deckToShuffle[i] = temp;
        }
        deck.clear();
        deck.addAll(Arrays.asList(deckToShuffle));// sweet!!
    }

    /**
     * taking advantage of the Collections.shuffle() Thanks Java!
     */
    public void easyShuffle() {
        ArrayList<Card> deckAL = new ArrayList<>(Arrays.asList(deck.toArray(new Card[51])));
        Collections.shuffle(deckAL);
        deck.clear();
        deck.addAll(deckAL);
    }

    public Card dealOneCardFromDeck() {
        return deck.poll();
    }

    /**
     * @return the total number of cards remaining in the deck in play.
     */
    public int getDeckSize() {
        return deck.size();
    }

    /**
     * @return the total number of cards remaining in the deck in play.
     */
    public int getDiscardPileSize() {
        return discardPile.size();
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

    void moveCardsToDiscardPile(ArrayList<Card> cardsInHand) {
        discardPile.addAll(cardsInHand);
    }

    void addDiscardPileBackToDeck() {
        deck.addAll(discardPile);
        discardPile.clear();
        this.shuffle();
    }
}
