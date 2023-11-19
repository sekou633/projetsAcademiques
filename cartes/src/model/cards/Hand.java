package model.cards;

import model.card.CardComparator;
import model.card.CardEvaluator;
import model.card.Card;

import java.util.List;

/**
 * <b>
 *     Hand is a set of cards.
 * </b>
 *
 * <p>
 *     In a playing card game, a hand is a set of cards. The user can add a card to the hand.
 *     The user can remove a card from the hand. The user can get specific card from the hand.
 *     The user can get the number of cards in the hand. The user can use the hand to evaluate the hand.
 * </p>
 *
 * @author 	22013393
 * @version	1.0
 */
public class Hand  extends ListOfCards {

    /**
     * Creates a new hand.
     */
    public Hand() {
        super();
    }

    /**
     * Creates a new hand with the given cards.
     *
     * @param cards
     * 		The cards in the hand.
     *
     * @throws NullPointerException if the cards is null.
     */
    public Hand(List<Card> cards) {
        super(cards);
    }

    /**
     * Returns the score of the hand.
     *
     * @param cardEvaluator
     * 		The card evaluator to use.
     * @return the score of the hand.
     *
     * @throws NullPointerException if the card evaluator is null.
     */
    public int evaluate(CardEvaluator cardEvaluator) {
        if (cardEvaluator == null) {
            throw new NullPointerException("The card evaluator cannot be null.");
        }
        return getCards().stream().mapToInt(cardEvaluator::evaluate).sum();
    }

    /**
     * Returns the best card of the hand.
     *
     * @param cardComparator
     * 		The card comparator to use.
     * @return the best card of the hand.
     *
     * @throws NullPointerException if the card comparator is null.
     */
    public Card getBestCard(CardComparator cardComparator) {
        if (cardComparator == null) {
            throw new NullPointerException("The card comparator cannot be null.");
        }
        return getCards().stream().max(cardComparator).orElse(null);
    }

    /**
     * Returns the worst card of the hand.
     *
     * @param cardComparator
     *         The card comparator to use.
     * @return the worst card of the hand.
     * @throws NullPointerException if the card comparator is null.
     */
    public Card getWorstCard(CardComparator cardComparator) {
        if (cardComparator == null) {
            throw new NullPointerException("The card comparator cannot be null.");
        }
        return getCards().stream().min(cardComparator).orElse(null);
    }

    /**
     * Returns a boolean indicating if the hand contains a pair.
     *
     * @return a boolean indicating if the hand contains a pair.
     */
    public boolean containsPair() {
        return getCards().stream().anyMatch(card -> getCards().stream().filter(card::equals).count() == 2);
    }


}
