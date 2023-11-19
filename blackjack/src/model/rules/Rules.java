package model.rules;


import model.BlackjackCardEvaluator;
import model.participants.Dealer;
import model.participants.Participant;
import model.participants.Player;

import java.util.Collection;

/**
 * <b>
 *     This interface represents the rules of the blackjack game.
 * </b>
 *
 * <p>
 *     All versions of the blackjack game have their own rules and common rules.
 *     This interface represents the common rules of the blackjack game.
 *     The common rules are the rules that are the same for all versions of the blackjack game.
 *     The common rules are the following:
 * </p>
 * <ul>
 *     <li> The player can play if he has not busted and if he has not won. </li>
 *     <li> T
 * </ul>
 *
 * @author 	22013393
 * @version	1.0
 */
public interface Rules {

    /**
     * Returns the evaluator of the cards.
     *
     * @return The evaluator of the cards.
     */
    BlackjackCardEvaluator getEvaluator();


    /**
     * Returns the minimum bet of the game.
     *
     * @return the minimum bet of the game.
     */
    int getMinimumBet();

    /**
     * Returns the maximum bet of the game.
     *
     * @return the maximum bet of the game.
     */
    int getMaximumBet();

    /**
     * Checks if a player have set a valid bet.
     *
     * @param bet
     *         The bet of the player.
     * @return true if the player have set a valid bet, false otherwise.
     */
    default boolean isValidBet(int bet){
        return bet >= this.getMinimumBet() && bet <= this.getMaximumBet();
    }



    /**
     * Returns the number of cards to deal to a player at the beginning of the game.
     *
     * @return the number of cards to deal to a player at the beginning of the game.
     */
    int getInitialCardsPerPlayer();


    /**
     * Returns the number of cards to deal to a dealer at the beginning of the game.
     *
     * @return the number of cards to deal to a dealer at the beginning of the game.
     */
    int getNumberOfCardsToDealToDealer();

    /**
     * Returns a boolean that indicates if the player can take another card even if he has busted.
     *
     * @return a boolean that indicates if the player can take another card even if he has busted.
     */
    boolean canTakeAnotherCardAfterBusted();

    /**
     * Returns the winner between a player and a dealer.
     *
     * @param player
     *       The player.
     * @param dealer
     *      The dealer.
     */
    Participant getWinner(Player player, Dealer dealer);

    /**
     * Returns the value of the bust.
     *
     * @return the value of the bust.
     */
    int getBustValue();

    /**
     * Returns a boolean that indicates if a participant is busted.
     *
     * @param participant
     *       The participant to check.
     * @return a boolean that indicates if a participant is busted.
     */
    default boolean isBusted(Participant participant) {
        return participant.evaluateHand() > this.getBustValue();
    }

    /**
     * Collect the loosing bet or pay the winning bet.
     *
     * @param player
     *       The player to collect the bet.
     * @param dealer
     *     The dealer to collect the bet.
     */
    void collectBet(Player player, Dealer dealer);

    /**
     * Returns the value of the hand that the dealer must have before he can stop taking cards.
     * @return the value of the hand that the dealer must have before he can stop taking cards.
     */
    int getDealerStandLimit();
}
