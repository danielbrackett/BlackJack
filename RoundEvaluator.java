public class RoundEvaluator {

    /**
     * does the dealer have a 21 ?
     * did a player get 21?
     * is the round over? all players stayed and the dealer has over 17
     * did any player have a 2 cards of the same value? in the first deal. # add-on.
     * evaluate outcome of round.
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

    public  boolean didNoOneWin(Player player, Player dealer) {
        return dealer.getHand().valueOfCardsInHand() ==
                player.getHand().valueOfCardsInHand();
    }
}
