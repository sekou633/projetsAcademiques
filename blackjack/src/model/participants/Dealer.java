package model.participants;

import model.BlackjackCardEvaluator;
import model.cards.Deck;
import model.cards.Hand;

/**
 * <b>
 *     Dealer is a participant of the game that can't bet and that his cards is used to compare with the players.
 * </b>
 *
 * @author 22013393
 * @version 1.0
 */
public class Dealer extends Participant{

    /**
     * Creates a new dealer
     *
     * @param name
     *         The name of the dealer.
     * @param hand
     *        The hand of the dealer.
     * @param evaluator
     *      The evaluator of the dealer.
     * @param money
     *      The money of the dealer.
     * @param canPlay
     *    Boolean that indicates if the dealer can play.
     */
    public Dealer(String name, Hand hand, BlackjackCardEvaluator evaluator, int money, boolean canPlay) {
        super(name, hand, evaluator, money, canPlay);
    }

    /**
     * Creates a new dealer with the given name and money.
     *
     * @param name
     *         The name of the dealer.
     * @param evaluator
     *     The evaluator of the dealer.
     * @param money
     *        The money of the dealer.
     */
    public Dealer(String name, BlackjackCardEvaluator evaluator, int money) {
        super(name, new Hand(), evaluator, money, true);
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    /**
     * Take cards from the deck until the hand is greater than a specific value.
     *
     * @param deck
     *       The deck of cards.
     * @param value
     *      The minimum value of the hand.
     */
    public void takeCardsUntil(Deck deck, int value) {
        while (this.evaluateHand() < value) {
            this.takeCard(deck);
        }
    }


}
