import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

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

        /*
         * if either busts then the round is over. while the player has more points than the dealer
         * and the dealer has at least 17 then the round is over.
         */
        while (stillPlaying) {
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

            /*
             * logic to deal more cards to the player upon request. otherwise not. including logic to
             * stop dealing if the player busts(point value is over 21).
             */
            boolean anotherCard = evaluator.isPlayersHandLT21(player);
//            System.out.println("Could the player be dealt another card? " + anotherCard);

            while (anotherCard) {
                System.out.println(getPlayer().getPlayerName() + " will you hit or stand");
                String decision = userInput.nextLine();
                boolean hit = hitOrStand(decision);
                if (hit) {
                    player.getHand().addCardToHand(deck.dealOneCardFromDeck());
                    System.out.println(getPlayer().getPlayerName() + "'s cards + one: ");
                    System.out.println(player.getHand());
                    if (evaluator.isBusted(player)) {
                        System.out.println("Busted !!");
                        anotherCard = false;
                    }
                } else {
                    System.out.println(getPlayer().getPlayerName() + "'s cards + None: ");
                    System.out.println(player.getHand());
                    anotherCard = false;
                }
            }

            /*
             * if the dealer hasn't met the 17 threshold,
             * return true
             */
            while (!evaluator.isBusted(player) && evaluator.mustDealerTakeACard(dealer, player)) {
                dealer.getHand().addCardToHand(deck.dealOneCardFromDeck());
                System.out.println("Dealer is dealt a new card.");
            }

            /*
             * did the player go bust?
             */
            System.out.println("begining the eval logic flow.");
            if (evaluator.isBusted(player)) {
                System.out.println("oops that's a bust buster. lol. Better Luck next time " + player.getPlayerName());
                System.out.println(getDealer().getPlayerName() + " " + dealer.getHand());
                System.out.println(getPlayer().getPlayerName() + " " + player.getHand());
                AllHandsAreDiscarded();
                System.out.println("Would you like to play another Round? 'yes' or 'no' ?");
                String nextRound = userInput.nextLine();
                if (playAnotherRound(nextRound)) stillPlaying = true;
                else {
                    System.out.println(playerName + ", goodbye for now.");
                    stillPlaying = false;
                }
                /*
                 * did the player win?
                 */
            } else if (evaluator.didPlayerWin(player, dealer)) {
                System.out.println(playerName + " is the winner! of this round.");
                System.out.println(dealer.getHand());
                System.out.println(player.getHand());
                AllHandsAreDiscarded();
                System.out.println("Would you like to play another Round? 'yes' or 'no' ?");
                String nextRound = userInput.nextLine();
                if (playAnotherRound(nextRound)) stillPlaying = true;
                else {
                    System.out.println(playerName + ", goodbye for now.");
                    stillPlaying = false;
                }
                /*
                 * the dealer won.
                 */
            } else {
                System.out.println(dealer.getPlayerName() + " is the winner! of this round.");
                System.out.println(dealer.getHand());
                System.out.println(player.getHand());
                AllHandsAreDiscarded();
                System.out.println("Would you like to play another Round? 'yes' or 'no' ?");
                String nextRound = userInput.nextLine();
                if (playAnotherRound(nextRound)) stillPlaying = true;
                else {
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
        if (deckInPlay.getDeckSize() <= 6) return;

        player.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        dealer.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        player.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        dealer.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
    }

    public Player evalFirstRoundPlayersHands() {
        Player winner = evaluator.isThereBlackJack(player, dealer);
        if (winner != null) return winner;
        if (evaluator.isDealerUnableToHitAndPlayerWins(player, dealer)) return player;
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
//            System.out.println("TRUE! Player decided to hit");
            return true;
        } else if (text.equalsIgnoreCase("stand") || text.equalsIgnoreCase("s")) {
//            System.out.println("FALSE! Player is standing their ground");
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
//            System.out.println("TRUE! Player decided to play another hand");
            return true;
        } else if (text.equalsIgnoreCase("no") || text.equalsIgnoreCase("n")) {
//            System.out.println("FALSE! Player has quit.");
            return false;
        } else {
            System.out.println("Please try again. Do you want to \"Hit\" or \"Stand\"?");
            Scanner userInput = new Scanner(System.in);
            String text2 = userInput.nextLine();
            return playAnotherRound(text2);
        }
    }

    private boolean askPlayerAnotherRoundDialogue(Scanner userInput) {
        System.out.println(getDealer().getPlayerName() + " " + dealer.getHand());
        System.out.println(getPlayer().getPlayerName() + " " + player.getHand());
        AllHandsAreDiscarded();
        System.out.println("Would you like to play another Round? 'yes' or 'no' ?");
        String nextRound = userInput.nextLine();
        if (playAnotherRound(nextRound))  return stillPlaying = true;
        else {
            System.out.println(getPlayer().getPlayerName() + ", goodbye for now.");
            return stillPlaying = false;
        }
    }

    private void AllHandsAreDiscarded() {
        player.getHand().discardAllCardsFromHand();
        dealer.getHand().discardAllCardsFromHand();
    }


//    /**
//     * call this method when a player wins.
//     */
//    public void announceTheWinner(@NotNull Player player) {
//        System.out.println(player.getPlayerName() + " Wins!!");
//    }
}
