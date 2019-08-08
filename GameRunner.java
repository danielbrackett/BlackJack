import org.jetbrains.annotations.NotNull;
import java.util.Scanner;

import java.util.ArrayList;

public class GameRunner {
//may split this up this to make it a black jack runner and a general game runner.

    public static void main(String[] args) {
        GameRunner game = new GameRunner();
        Scanner userInput = new Scanner(System.in);
        System.out.println("What is your name?");
        String playerName = userInput.nextLine();
        game.players.get(1).setPlayerName(playerName);
        System.out.println("Let's play some BlackJack, " + playerName);
        Deck deck = new Deck();
        deck.shuffle();
        game.deal(deck);
        System.out.println(playerName + " will you hit or stand");
        String hitOrStand = userInput.nextLine();

        game.evalPlayersHands(game.players);

//        deck.printDeck();
//        System.out.println("one card print test");
//        System.out.println(deck.getDeckSize());
//        deck.dealOneCardFromDeck();
    }

    private ArrayList<Player> players;
    private int numberOfPlayers;

    /**
     * Default constructor, allows for 2 'players' one dealer and one gambler.
     */
    private GameRunner() {
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
     * @param numberOfPlayers takes that number of players from the constructor. if no int is specified then the
     *                        default constructor makes 2 players: 1 dealer & 1 player.
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
    private void deal(@NotNull Deck deckInPlay) {
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

    public void evalPlayersHands(@NotNull ArrayList<Player> players) {
        //only adding everything up for the moment.
        int totalInHand = 0;
        int dealerPoints = players.get(0).getHand().valueOfCardsInHand();
        System.out.println(players.get(0).getPlayerName() + " " + dealerPoints + " points");
        for (int i = 1; i < players.size(); i++) {
            int points = players.get(i).getHand().valueOfCardsInHand();
            this.isNatural21(players.get(i));
            System.out.println(players.get(i).getPlayerName() + " " + points + " points");
            }
    }

    public void isNatural21(@NotNull Player player) {
        if (player.getHand().valueOfCardsInHand() == 21 && player.getHand().getHandSize() == 2) {
            player.getHand().setWinningHand(true);
            this.announceTheWinner(player);
        }
    }

    public void hitOrStand(String text) {
        return;
    }

//    public boolean bustOrNot (Player player) {
//        int points = player.getHand().valueOfCardsInHand();
//        if (points <= 21) {
//                System.out.println(player.getPlayerName() + " " + points + " points");
//        }
//        return valGT21(player);
//    }

//    public boolean valGT21(@NotNull Player player) {
//
//        if(player.getHand().cardsInHand.contains(Card))
//        return true;
//    }

    /**
     * this method may be determined to be unnecessary. further work must be done.
     * however, will continue to play with sufficient cards for all players.
     * @return returns true when there still is a player left?
     */
    public boolean continuePlay() {
        return true;
    }

    /**
     * call this method when a player wins.
     */
    public void announceTheWinner(Player player) {
        System.out.println(player.getPlayerName() +  " Wins!!");
        }
    }
}
