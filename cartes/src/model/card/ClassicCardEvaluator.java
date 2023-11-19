package model.card;

/**
 * <b>
 *     A classic card evaluator is based on the natural value of the cards.
 * </b>
 *
 * <p>
 *     The natural value of the cards is the value that is associated to the card in the game.
 * </p>
 *
 * @author 	22013393
 * @version	1.0
 */
public class ClassicCardEvaluator implements CardEvaluator {

    @Override
    public int evaluate(Card card) {
        return card.getRank().getValue();
    }
}
