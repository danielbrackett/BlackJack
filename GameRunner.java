import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Scanner;

class GameRunner {

    private final Player dealer = new Player("Dealer");
    private final Player player = new Player("");
    private final Bet bet = new Bet();
    private final RoundEvaluator evaluator = new RoundEvaluator();
    private boolean stillPlaying = true;

    void run() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("What is your name?");
        String nameInput = userInput.nextLine();
        player.setPlayerName(nameInput);
        System.out.println("Let's play some BlackJack, " + nameInput + ".");
        System.out.println("The Stakes at this table are 5 tokens a hand.");

        Deck deck = new Deck();
        deck.shuffle();

        /*
         * if either busts then the round is over. while the player has more points than the dealer
         * and the dealer has at least 17 then the round is over.
         */
        while (stillPlaying && player.getTokens().getTotal() > 0) {
            if (player.getHand().getCardsInHand().isEmpty()) {
                if (player.isHandEnmpty() && bet.getPot() == 0) {
                    bet.addToPot(player.placeBet(bet.getBetAmount()));
                }
                deal(deck);
                System.out.println(dealer.getPlayerName() + "'s cards: " + dealer.getHand().getCardsInHand().get(0));
                System.out.println(player.getPlayerName() + "'s cards: " + player.getHand());
                Player winner = evalFirstRoundPlayersHands();
                if (winner != null) {
                    allHandsAreDiscarded(deck);
                    System.out.println(winner.getPlayerName() + " winner winner chicken dinner!");
                    askPlayerAnotherRoundDialogue(userInput);
                    continue;
                }
            }

            // logic to deal more cards to the player upon request. otherwise not. including logic to
            // stop dealing if the player busts(point value is over 21).
            boolean canStillHit = evaluator.isPlayersHandLT21(player);

            while (canStillHit) {
                boolean hit = hitOrStand(userInput);
                if (hit) {
                    timeToRefreshTheDeck(deck);
                    player.getHand().addCardToHand(deck.dealOneCardFromDeck());
                    canStillHit = evaluator.isPlayersHandLT21(player);
                    System.out.println(player.getPlayerName() + "'s cards + one: " + player.getHand());
                    if (evaluator.isBusted(player)) {
                        System.out.println("Busted !!");
                    }
                } else {
                    canStillHit = false;
                }
            }

            /**
             * if the dealer hasn't met the 17 threshold,
             * return true
             */
            while (evaluator.mustDealerTakeACard(dealer, player)) {
                final Card dealt = deck.dealOneCardFromDeck();
                final Hand dealerHand = dealer.getHand();
                dealerHand.addCardToHand(dealt);
                System.out.println("Dealer is dealt a new card.");
            }

            /**
             * did the player go bust?
             */
//            System.out.println("beginning the eval logic flow.");
            if (evaluator.isBusted(player)) {
                System.out.println("oops that's a bust buster. lol. Better Luck next time " + player.getPlayerName() + ".");
                printAllHands();
                allHandsAreDiscarded(deck);
                askPlayerAnotherRoundDialogue(userInput);
                /**
                 * did the player win?
                 */
            } else if (evaluator.didPlayerWin(player, dealer)) {
                printAllHands();
                System.out.println(player.getPlayerName() + " is the winner! of this round.");
                allHandsAreDiscarded(deck);
                askPlayerAnotherRoundDialogue(userInput);
                /**
                 * did the dealer win?
                 */
            } else if (evaluator.didDealerWin(player, dealer)) {
                printAllHands();
                System.out.println(dealer.getPlayerName() + " is the winner! of this round.");
                allHandsAreDiscarded(deck);
                askPlayerAnotherRoundDialogue(userInput);
                /**
                 * the dealer and the player have the same score the result is a push all parties get their bets back
                 *  no one wins.
                 */
            } else if (evaluator.didNoOneWin(player, dealer)) {
                printAllHands();
                System.out.println("No one is the winner! of this round. Please enjoy this round for free.");
                allHandsAreDiscarded(deck);
                askPlayerAnotherRoundDialogue(userInput);
            }
            timeToRefreshTheDeck(deck);
        }
//  Note; END OF run()
    }

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

    @Nullable
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
        System.out.println(player.getPlayerName() + " will you hit or stand");
        String text = userInput.nextLine();
        if (text.equalsIgnoreCase("hit") || text.equalsIgnoreCase("h")) {
            return true;
        } else if (text.equalsIgnoreCase("stand") || text.equalsIgnoreCase("s")) {
            return false;
        } else {
            System.out.println("Please try again. ");
            return hitOrStand(userInput);
        }
    }

    /**
     * this method allows for the player to stop play at the end of any round.
     *
     * @return returns true when there still is a player left?
     */
    private boolean playAnotherRound(@NotNull Scanner userInput) {
        System.out.println("Would you like to play another Round? 'yes' or 'no' ?");
        String text = userInput.nextLine();
        if (text.equalsIgnoreCase("yes") ||
                text.equalsIgnoreCase("y")) {
            return true;
        } else if (text.equalsIgnoreCase("no") ||
                text.equalsIgnoreCase("n")) {
            return false;
        } else {
            System.out.println("Please try again.");
            return playAnotherRound(userInput);
        }
    }

    private void printAllHands() {
        System.out.println(dealer.getPlayerName() + " " + dealer.getHand());
        System.out.println(player.getPlayerName() + " " + player.getHand());
    }

    private void askPlayerAnotherRoundDialogue(Scanner userInput) {
        if (playAnotherRound(userInput)) stillPlaying = true;
        else {
            System.out.println(player.getPlayerName() + ", goodbye for now.");
            stillPlaying = false;
        }
    }

    private void allHandsAreDiscarded(@NotNull Deck deck) {
        deck.moveCardsToDiscardPile(player.getHand().getCardsInHand());
        deck.moveCardsToDiscardPile(dealer.getHand().getCardsInHand());
        player.getHand().discardAllCardsFromHand();
        dealer.getHand().discardAllCardsFromHand();
    }

    private void timeToRefreshTheDeck(Deck deck) {
        if (deck.getDeckSize() < 10) {
            deck.addDiscardPileBackToDeck();
        }
    }
}