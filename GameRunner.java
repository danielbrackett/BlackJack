import java.util.ArrayList;

public class GameRunner {
//may split this up this to make it a black jack runner and a general game runner.

    public static void main(String[] args) {
        GameRunner game = new GameRunner();
        Deck deck = new Deck();
        deck.shuffle();
        game.deal(deck);
//        deck.printDeck();
//        System.out.println("one card print test");
        System.out.println(deck.getDeckSize());

//        deck.dealOneCardFromDeck();
    }

    private ArrayList<Player> players;
    int numberOfPlayers;

    /**
     * Default constructor, allows for 2 'players' one dealer and one gambler.
     */
    public GameRunner() {
        players = new ArrayList<>();
        createPlayers(2);
    }

    /**
     * Constructor for multi-player game.
     * allows up to 5 players plus 1 dealer all players play against the dealer.
     * @param numberOfPlayers allows for more than the default number of players.
     */
    public GameRunner(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        if (numberOfPlayers > 2 && numberOfPlayers < 6) {
            createPlayers(numberOfPlayers);
        }
    }

    /**
     * Create the number of players specified for a game.
     * @param numberOfPlayers
     */
    private void createPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        Player dealer = new Player("dealer");
        dealer.isDealer = true;
        players.add(dealer);
        numberOfPlayers--;
        for (int i = numberOfPlayers; i > 0; i--) {
            Player player = new Player("player " + i);
            players.add(player);
        }
    }

    /**
     * deals the first hand to each player and the dealer.
     * @param deckInPlay this is the deck currently being used to play a game.
     */
    public void deal(Deck deckInPlay) {
        if (deckInPlay.getDeckSize() > (3 * players.size())) {
            int twoCards = 0;
            while (twoCards < 2) {
                for (Player player: players) {
                    // the proceeding section might benefit from a refactor.
                    // as maybe; player.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
                    Card cardDealt = deckInPlay.dealOneCardFromDeck();
                    Hand hand = player.getHand();
                    hand.addCardToHand(cardDealt);
                }
                twoCards++;
            }
        }
    }

    /**
     * this method may be determined to be unnecessary. further work must be done.
     * however, will continue to play with sufficient cards for all players.
     * @return returns true when there still is a player left?
     */
    public boolean continuePlay() {
        return true;
    }
}
