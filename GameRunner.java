import org.jetbrains.annotations.NotNull;
import java.util.Scanner;

import java.util.ArrayList;

public class GameRunner {
//may split this up this to make it a black jack runner and a general game runner.


    private final Player dealer = new Player("Dealer");
    private final Player player = new Player("");
    private RoundEvaluator evaluator = new RoundEvaluator();
    private boolean stillPlaying = true;

    public Player getDealer() {
        return dealer;
    }

    public Player getPlayer() {
        return player;
    }

    public void run()
    {
        Scanner userInput = new Scanner(System.in);
        System.out.println("What is your name?");
        String playerName = userInput.nextLine();
        player.setPlayerName(playerName);
        System.out.println("Let's play some BlackJack, " + playerName);

        Deck deck = new Deck(); //
        deck.shuffle();

        while (stillPlaying) {
            deal(deck);
            System.out.println("Dealer cards: ");
            System.out.println(dealer.getHand());
            System.out.println("Player 1 cards: ");
            System.out.println(player.getHand());
            Player winner = evalFirstRoundPlayersHands();
            if (winner != null) {
                System.out.println(winner);
                continue;
            }
            System.out.println(playerName + " will you hit or stand");

            String hitOrStand = userInput.nextLine();

        }

//        deck.printDeck();
//        System.out.println("one card print test");
//        System.out.println(deck.getDeckSize());
//        deck.dealOneCardFromDeck();
    }

    /**
     * deals the first hand to each player and the dealer.
     * @param deckInPlay this is the deck currently being used to play a game.
     */
    public boolean deal(@NotNull Deck deckInPlay) {
        if (deckInPlay.getDeckSize() <= 6) {
            return false;
        }

        player.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        dealer.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        player.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        dealer.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        return true;
    }

    public Player evalFirstRoundPlayersHands() {
        Player winner = evaluator.isThereBlackJack(player, dealer);
        if ( winner != null) {
            return winner;
        }
        if (evaluator.isDealerUnableToHitAndPlayerWins(player, dealer)) {
            return player;
        }
        return null;
    }

    public void isNatural21(@NotNull Player player) {
        if (player.getHand().valueOfCardsInHand() == 21 && player.getHand().getHandSize() == 2) {
//            player.getHand().setWinningHand(true);
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
    public void announceTheWinner(@NotNull Player player) {
        System.out.println(player.getPlayerName() +  " Wins!!");
    }
}
