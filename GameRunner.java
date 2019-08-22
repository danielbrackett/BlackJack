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
        String nameInput = userInput.nextLine();
        getPlayer().setPlayerName(nameInput);
        System.out.println("Let's play some BlackJack, " + nameInput + ".");

        Deck deck = new Deck(); //
        deck.shuffle();
        deck.printDeck();

        /*
         * if either busts then the round is over. while the player has more points than the dealer
         * and the dealer has at least 17 then the round is over.
         */
        while (stillPlaying) {
            if (player.getHand().getCardsInHand().isEmpty()) {
                deal(deck);
                System.out.println(getDealer().getPlayerName() + "'s cards: ");
                System.out.println(dealer.getHand().getCardsInHand().get(0));
                System.out.println(getPlayer().getPlayerName() + "'s cards: ");
                System.out.println(player.getHand());
                Player winner = evalFirstRoundPlayersHands();
                if (winner != null) {
                    allHandsAreDiscarded(deck);
                    System.out.println(winner.getPlayerName() + " winner winner chicken dinner!");
                    continue;
                }
            }

            // logic to deal more cards to the player upon request. otherwise not. including logic to
            // stop dealing if the player busts(point value is over 21).
            boolean canStillHit = evaluator.isPlayersHandLT21(player);
//            System.out.println("Could the player be dealt another card? " + anotherCard);

            while (canStillHit) {
                boolean hit = hitOrStand(userInput);
                if (hit) {
                    player.getHand().addCardToHand(deck.dealOneCardFromDeck());
                    System.out.println(getPlayer().getPlayerName() + "'s cards + one: ");
                    System.out.println(player.getHand());
                    if (evaluator.isBusted(player)) {
                        System.out.println("Busted !!");
                        canStillHit = false;
                    }
                } else {
                    System.out.println(getPlayer().getPlayerName() + "'s cards + None: ");
                    System.out.println(player.getHand());
                    canStillHit = false;
                }
            }

            /*
             * if the dealer hasn't met the 17 threshold,
             * return true
             */
            while (evaluator.mustDealerTakeACard(dealer, player)) {
                final Card dealt = deck.dealOneCardFromDeck();
                final Hand dealerHand = dealer.getHand();
                dealerHand.addCardToHand(dealt);
                System.out.println("Dealer is dealt a new card.");
            }

            /*
             * did the player go bust?
             */
            System.out.println("beginning the eval logic flow.");
            if (evaluator.isBusted(player)) {
                System.out.println("oops that's a bust buster. lol. Better Luck next time " + player.getPlayerName());
                printAllHands();
                allHandsAreDiscarded(deck);
                askPlayerAnotherRoundDialogue(userInput);
                /*
                 * did the player win?
                 */
            } else if (evaluator.didPlayerWin(player, dealer)) {
                printAllHands();
                System.out.println(player.getPlayerName() + " is the winner! of this round.");
                allHandsAreDiscarded(deck);
                askPlayerAnotherRoundDialogue(userInput);
                /*
                 * did the dealer win?
                 */
            } else if (evaluator.didDealerWin(player, dealer)) {
                printAllHands();
                System.out.println(dealer.getPlayerName() + " is the winner! of this round.");
                allHandsAreDiscarded(deck);
                askPlayerAnotherRoundDialogue(userInput);
                /*
                 * the dealer and the player have the same score the result is a push all parties get there bets back
                 *  no one wins.
                 */
            } else  if (evaluator.didNoOneWin(player, dealer)){
                printAllHands();
                System.out.println("No one is the winner! of this round. Please enjoy this round for free.");
                allHandsAreDiscarded(deck);
                askPlayerAnotherRoundDialogue(userInput);
            }
        }
//  Note; END OF run()
    }


//
//    public boolean canDeal(@NotNull Deck deckInPlay) {
//        return (deckInPlay.getDeckSize() > 6);
//    }

    /**
     * deals the first hand to each player and the dealer.
     *
     * @param deckInPlay this is the deck currently being used to play the game.
     */
    private void deal(@NotNull Deck deckInPlay) {
        player.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        dealer.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        player.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
        dealer.getHand().addCardToHand(deckInPlay.dealOneCardFromDeck());
    }

    private Player evalFirstRoundPlayersHands() {
        Player winner = evaluator.isThereBlackJack(player, dealer);
        if (winner != null) return winner;
        if (evaluator.isDealerUnableToHitAndPlayerWins(player, dealer)) return player;
        return null;
    }

    /**
     * This method allows the player to request another card or not.
     *
     * @param userInput = userInput; 'hit' or 'stand'
     * @return a boolean; true = hit while false = stand. All other options = recursive call to same.
     */
    private boolean hitOrStand(Scanner userInput) {
        System.out.println(getPlayer().getPlayerName() + " will you hit or stand");
        String text = userInput.nextLine();
        if (text.equalsIgnoreCase("hit") || text.equalsIgnoreCase("h")) {
//            System.out.println("TRUE! Player decided to hit");
            return true;
        } else if (text.equalsIgnoreCase("stand") || text.equalsIgnoreCase("s")) {
//            System.out.println("FALSE! Player is standing their ground");
            return false;
        } else {
            System.out.println("Please try again. ");
            return hitOrStand(userInput);
        }
    }

    /**
     * this method may be determined to be unnecessary. further work must be done.
     * however, will continue to play with sufficient cards for all players.
     *
     * @return returns true when there still is a player left?
     */
    private boolean playAnotherRound(Scanner userInput) {
        System.out.println("Would you like to play another Round? 'yes' or 'no' ?");
        String text = userInput.nextLine();
        if (text.equalsIgnoreCase("yes") || text.equalsIgnoreCase("y")) {
//            System.out.println("TRUE! Player decided to play another hand");
            return true;
        } else if (text.equalsIgnoreCase("no") || text.equalsIgnoreCase("n")) {
//            System.out.println("FALSE! Player has quit.");
            return false;
        } else {
            System.out.println("Please try again. ");
            return playAnotherRound(userInput);
        }
    }

    private void printAllHands() {
        System.out.println(getDealer().getPlayerName() + " " + dealer.getHand());
        System.out.println(getPlayer().getPlayerName() + " " + player.getHand());
    }

    private void askPlayerAnotherRoundDialogue(Scanner userInput) {
        if (playAnotherRound(userInput)) stillPlaying = true;
        else {
            System.out.println(getPlayer().getPlayerName() + ", goodbye for now.");
            stillPlaying = false;
        }
    }

    private void allHandsAreDiscarded(@NotNull Deck deck) {
        deck.moveCardsToDiscardPile(player.getHand().getCardsInHand());
        deck.moveCardsToDiscardPile(dealer.getHand().getCardsInHand());
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
