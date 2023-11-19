package model.rules;

import model.BlackjackCardEvaluator;
import model.participants.Dealer;
import model.participants.Participant;
import model.participants.Player;

/**
 * <b>
 *     Classic rules is the default rules of the blackjack game.
 * </b>
 *
 */
public class ClassicRules implements Rules {

    /**
     * The value of the ace.
     */
    private final int aceValue;

    /**
     * The evaluator of the cards.
     */
    private final BlackjackCardEvaluator evaluator;

    /**
     * Creates a new ClassicRules with the given value of the ace and the given evaluator of the cards.
     *
     * @param aceValue
     *          The value of the ace.
     */
    public ClassicRules(int aceValue) {
        this.aceValue = aceValue;
        this.evaluator = new BlackjackCardEvaluator(aceValue);
    }

    /**
     * Creates a new ClassicRules with the default value of the ace and the default evaluator of the cards.
     */
    public ClassicRules() {
        this(11);
    }

    /**
     * Returns the value of the ace.
     *
     * @return the value of the ace.
     */
    public int getAceValue() {
        return this.aceValue;
    }


    /**
     * Returns the evaluator of the cards.
     *
     * @return The evaluator of the cards.
     */
    @Override
    public BlackjackCardEvaluator getEvaluator() {
        return this.evaluator;
    }

    /**
     * Returns the minimum bet of the game.
     *
     * @return the minimum bet of the game.
     */
    @Override
    public int getMinimumBet() {
        return 2;
    }

    /**
     * Returns the maximum bet of the game.
     *
     * @return the maximum bet of the game.
     */
    @Override
    public int getMaximumBet() {
        return 100;
    }

    /**
     * Returns the number of cards to deal to a player at the beginning of the game.
     *
     * @return the number of cards to deal to a player at the beginning of the game.
     */
    @Override
    public int getInitialCardsPerPlayer() {
        return 2;
    }

    /**
     * Returns the number of cards to deal to a dealer at the beginning of the game.
     *
     * @return the number of cards to deal to a dealer at the beginning of the game.
     */
    @Override
    public int getNumberOfCardsToDealToDealer() {
        return 1;
    }

    /**
     * Returns a boolean that indicates if the player can take another card even if he has busted.
     *
     * @return a boolean that indicates if the player can take another card even if he has busted.
     */
    @Override
    public boolean canTakeAnotherCardAfterBusted() {
        return false;
    }

    /**
     * Returns the winner between a player and a dealer.
     *
     * @param player The player.
     * @param dealer The dealer.
     */
    @Override
    public Participant getWinner(Player player, Dealer dealer) {
        if (this.isBusted(player)) {
            return dealer;
        } else if (this.isBusted(dealer)) {
            return player;
        } else if (player.evaluateHand() > dealer.evaluateHand()) {
            return player;
        } else if (player.evaluateHand() < dealer.evaluateHand()) {
            return dealer;
        } else {
            return null;
        }
    }

    /**
     * Returns the value of the bust.
     *
     * @return the value of the bust.
     */
    @Override
    public int getBustValue() {
        return 21;
    }

    /**
     * Collect the loosing bet or pay the winning bet.
     *
     * @param player The player to collect the bet.
     * @param dealer The dealer to collect the bet.
     */
    @Override
    public void collectBet(Player player, Dealer dealer) {
        Participant winner = this.getWinner(player, dealer);
        if (winner == null){
            // Give back the bet
            player.increaseMoney(player.getBet());
        }
        else if (winner == player){
            // The player win once their bet
            player.increaseMoney(player.getBet() * 2);
            // The money of the dealer decrease by the bet of the player
            dealer.decreaseMoney(player.getBet());
        }
        else if (winner == dealer){
            // The money of the dealer increase by the bet of the player
            dealer.increaseMoney(player.getBet());
            // The money of the player decrease by the bet of the player
            player.decreaseMoney(player.getBet());
        }

    }
    
    @Override
    public int getDealerStandLimit() {
        return 17;
    }
}
