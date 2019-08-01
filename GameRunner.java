import java.util.ArrayList;

public class GameRunner {
//may split this up this to make it a black jack runner and a general game runner.

    public static void main(String[] args) {
        GameRunner game = new GameRunner();
        Deck deck = new Deck();
        deck.shuffle();
//        game.deal(deck);


        deck.printDeck();
    }

    private ArrayList<Player> players;

    public GameRunner() {
        players = new ArrayList<>();
        createPlayers(2);
    }

    /*
    allows up to 5 players plus 1 dealer all players play against the dealer.
     */
    public GameRunner(int players) {
        if (players > 2 && players < 6) {
            createPlayers(players);
        }
    }
    /*
    Create the number of players of a game the game lasts until the
     */
    public void createPlayers(int numberOfPlayers) {
        Player dealer = new Player();
        dealer.isDealer = true;
        numberOfPlayers--;
        for (int i = numberOfPlayers; i > 0; i--) {
            Player player = new Player();
            players.add(player);
        }
    }

    /*
    deals the first hand to each player and the dealer.
     */
//    public void deal(Deck deckInPlay) {
//        if (deckInPlay.getDeckSize() > 3 * players.size()) {
//            while (player.)
//            for (Player player: players) {
//
//            }
//        }
//    }

        /*
        will continue to play with sufficient cards for all players.

         */
    public boolean continuePlay() {
        return true;
    }
}
