import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public void run() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("What is your name?");
        String playerName = userInput.nextLine();
        getPlayer().setPlayerName(playerName);
        System.out.println("Let's play some BlackJack, " + playerName + ".");

        Deck deck = new Deck(); //
        deck.shuffle();

        while (stillPlaying) { //if either busts then play is over. if
            if (player.getHand().cardsInHand.isEmpty()) {
                deal(deck);
                System.out.println(getDealer().getPlayerName() + "'s cards: ");
                System.out.println(dealer.getHand().cardsInHand.get(0));
                System.out.println(getPlayer().getPlayerName() + "'s cards: ");
                System.out.println(player.getHand());
                Player winner = evalFirstRoundPlayersHands();
                if (winner != null) {
                    System.out.println(winner.getPlayerName() + " winner winner chicken dinner!");
                    continue;
                }
            }

            AtomicBoolean anotherCard = new AtomicBoolean(evaluator.isPlayersHandLT21(player));
            System.out.println(anotherCard + " = another card");

            if (anotherCard.get()) {
                System.out.println(getPlayer().getPlayerName() + " will you hit or stand");
                String decision = userInput.nextLine();
                AtomicBoolean hit = new AtomicBoolean(hitOrStand(decision));
                if (hit.get()) {
                    player.getHand().addCardToHand(deck.dealOneCardFromDeck());
                    System.out.println(getPlayer().getPlayerName() + "'s cards + one: ");
                    System.out.println(player.getHand());
                    if (evaluator.isBusted(player)) {
                        System.out.println("b+Busted !!");
                        anotherCard.set(false); //IDE doesn't understand me.  this is very much used.
                    }
                } else {
                    System.out.println(getPlayer().getPlayerName() + "'s cards + None: ");
                    System.out.println(player.getHand());
                    if (evaluator.didPlayerWin(player, dealer)) {
                        hit.set(false);
                        anotherCard.set(false);
                    }
                }
            }

            if (dealer.getHand().valueOfCardsInHand() < 17) {
                dealer.getHand().addCardToHand(deck.dealOneCardFromDeck());

            }

            if (evaluator.isBusted(player)) {
                System.out.println("oops that's a bust buster. lol");
                AllHandsAreDiscarded();
                System.out.println("would you like to play another Round? 'yes' or 'no' ?");
                String nextRound = userInput.nextLine();
                if (playAnotherRound(nextRound)) {
                    stillPlaying = true;
                } else {
                    System.out.println(playerName + ", goodbye for now.");
                    stillPlaying = false;
                }
            } else  if (evaluator.didPlayerWin(player, dealer)) {
                System.out.println(playerName + " is the winner! of this round.");
                AllHandsAreDiscarded();
                System.out.println("would you like to play another Round? 'yes' or 'no' ?");
                String nextRound = userInput.nextLine();
                if (playAnotherRound(nextRound)) {
                    stillPlaying = true;
                } else {
                    System.out.println(playerName + ", goodbye for now.");
                    stillPlaying = false;
                }
            } else {
                System.out.println(dealer.getPlayerName() + " is the winner! of this round.");
                AllHandsAreDiscarded();
                System.out.println("would you like to play another Round? 'yes' or 'no' ?");
                String nextRound = userInput.nextLine();
                if (playAnotherRound(nextRound)) {
                    stillPlaying = true;
                } else {
                    System.out.println(playerName + ", goodbye for now.");
                    stillPlaying = false;
                }
            }

        }

    }

    /**
     * deals the first hand to each player and the dealer.
     *
     * @param deckInPlay this is the deck currently being used to play a game.
     */
    public void deal(@NotNull Deck deckInPlay) {
        if (deckInPlay.getDeckSize() <= 6) {
            return;
        }

        player.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        dealer.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        player.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        dealer.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
    }

    public Player evalFirstRoundPlayersHands() {
        Player winner = evaluator.isThereBlackJack(player, dealer);
        if (winner != null) {
            return winner;
        }
        if (evaluator.isDealerUnableToHitAndPlayerWins(player, dealer)) {
            return player;
        }
        return null;
    }

    /**
     * This method allows the player to request another card or not.
     *
     * @param text = userInput; 'hit' or 'stand'
     * @return a boolean; true = hit while false = stand. All other options = recursive call to same.
     */
    private boolean hitOrStand(String text) {
        if (text.equalsIgnoreCase("hit") || text.equalsIgnoreCase("h")) {
            System.out.println("TRUE! Player decided to hit");
            return true;
        } else if (text.equalsIgnoreCase("stand") || text.equalsIgnoreCase("s")) {
            System.out.println("FALSE! Player is standing their ground");
            return false;
        } else {
            System.out.println("Please try again. Do you want to \"Hit\" or \"Stand\"?");
            Scanner userInput = new Scanner(System.in);
            String text2 = userInput.nextLine();
            return hitOrStand(text2);
        }
    }

    /**
     * this method may be determined to be unnecessary. further work must be done.
     * however, will continue to play with sufficient cards for all players.
     *
     * @return returns true when there still is a player left?
     */
    private boolean playAnotherRound(String text) {
        if (text.equalsIgnoreCase("yes") || text.equalsIgnoreCase("y")) {
            System.out.println("TRUE! Player decided to play another hand");
            return true;
        } else if (text.equalsIgnoreCase("no") || text.equalsIgnoreCase("n")) {
            System.out.println("FALSE! Player has quit.");
            return false;
        } else {
            System.out.println("Please try again. Do you want to \"Hit\" or \"Stand\"?");
            Scanner userInput = new Scanner(System.in);
            String text2 = userInput.nextLine();
            return playAnotherRound(text2);
        }
    }

    private void AllHandsAreDiscarded() {
        player.getHand().discardAllCardsFromHand();
        dealer.getHand().discardAllCardsFromHand();
    }



    /**
     * call this method when a player wins.
     */
    public void announceTheWinner(@NotNull Player player) {
        System.out.println(player.getPlayerName() + " Wins!!");
    }
}
