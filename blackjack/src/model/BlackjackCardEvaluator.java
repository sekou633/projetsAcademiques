package model;

import model.card.Card;
import model.card.CardEvaluator;
import model.card.Rank;

/**
 * <b>
 *     BlackjackCardEvaluator is a class that evaluates the value of a card in the game of Blackjack.
 * </b>
 *
 * <p>
 *     The value of a card in the game of Blackjack is determined by the rank of the card.
 *     If the rank of the card is a number, the value of the card is the number.
 *     If the rank of the card is a face card, the value of the card is 10.
 *     If the rank of the card is an ace, the value of the card is 11 or 1.
 * </p>
 */
public class BlackjackCardEvaluator implements CardEvaluator {


    /**
     * The value of the ace.
     */
    private final int aceValue;

    /**
     * Creates a new BlackjackCardEvaluator with the given value of the ace.
     *
     * @param aceValue
     *          The value of the ace.
     */
    public BlackjackCardEvaluator(int aceValue) {
        this.aceValue = aceValue;
    }

    /**
     * Creates a new BlackjackCardEvaluator with the default value of the ace.
     */
    public BlackjackCardEvaluator() {
        this(11);
    }

    @Override
    public int evaluate(Card card) {
        if (card.getRank() == Rank.ACE) {
            return this.aceValue;
        }
        return Math.min(card.getRank().getValue(), 10);
    }

    /**
     * Returns the value of the ace.
     *
     * @return the value of the ace.
     */
    public int getAceValue() {
        return this.aceValue;
    }




}
