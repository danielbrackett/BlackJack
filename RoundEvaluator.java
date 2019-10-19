public class RoundEvaluator {

    /*
     * Java Default constructor is used.
     */

    public Player isThereBlackJack(Player player, Player dealer) {
        if (player.getHand().valueOfCardsInHand() == 21) {
            if (dealer.getHand().valueOfCardsInHand() == 21) {
                return null;
            }
            return player;
        }
        if (dealer.getHand().valueOfCardsInHand() == 21) {
            return dealer;
        }
        return null;
    }

    public boolean isDealerUnableToHitAndPlayerWins(Player player, Player dealer) {
        return dealer.getHand().valueOfCardsInHand() >= 17 && player.getHand().valueOfCardsInHand() >
                dealer.getHand().valueOfCardsInHand();
    }

    public boolean isPlayersHandLT21(Player player) {
        return player.getHand().valueOfCardsInHand() < 21;
    }

    public boolean isBusted(Player player) {
        return player.getHand().valueOfCardsInHand() > 21;
    }

    public boolean mustDealerTakeACard(Player dealer, Player player) {
        return dealer.getHand().valueOfCardsInHand() < 17
                && !this.isBusted(player);
    }

    public boolean didPlayerWin(Player player, Player dealer) {
        final boolean playerScoreHigher = player.getHand().valueOfCardsInHand() >
                dealer.getHand().valueOfCardsInHand();
        final boolean dealerBust = this.isBusted(dealer);
        final boolean playerBust = this.isBusted(player);
        return !playerBust && (dealerBust || playerScoreHigher);
    }

    public boolean didDealerWin(Player player, Player dealer) {
        final boolean dealerScoreHigher = dealer.getHand().valueOfCardsInHand() >
                player.getHand().valueOfCardsInHand();
        final boolean dealerBust = this.isBusted(dealer);
        final boolean playerBust = this.isBusted(player);
        return !dealerBust && (playerBust || dealerScoreHigher);
    }

    public boolean didNoOneWin(Player player, Player dealer) {
        return dealer.getHand().valueOfCardsInHand() ==
                player.getHand().valueOfCardsInHand();
    }

    public boolean isBalanceSufficient(Player player, int betAmount) {
        return player.getBank().getBalance() >= betAmount;
    }

    /*
    will look into refactoring this code, I'd like to be able to set a final amount for the payout and settlement of bets
    in a way that would not necessitate the passing of this value. thinking...
     */
    public void payDealer(Player player, Player dealer, int betAmount) {
        player.getBank().withdraw(betAmount);
        dealer.getBank().deposit(betAmount);
    }

    public void payPlayer(Player player, Player dealer, int betSettlement) {
        dealer.getBank().withdraw(betSettlement);
        player.getBank().deposit(betSettlement);
    }
}
