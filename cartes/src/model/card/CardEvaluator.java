package model.card;

/**
 * <b>
 *     Card evaluator permits to evaluate a card.
 * </b>
 *
 * <p>
 *     According to the rules of the game, a card can be evaluated to determine the winner of the game.
 *     So we must specify the rules of the game to evaluate a card.
 * </p>
 *
 * @author 	22013393
 * @version	1.0
 */
public interface CardEvaluator {



    /**
     * Evaluate a card.
     *
     * @param card
     *         The card to evaluate.
     * @return the score of the card.
     */
    int evaluate(Card card);


}
