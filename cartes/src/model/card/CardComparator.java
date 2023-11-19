package model.card;

import java.util.Comparator;

/**
 * <b>
 *     Card comparator permits to compare two cards.
 * </b>
 *
 * <p>
 *     The user of this class will define the way to compare two cards.
 * </p>
 */
public interface CardComparator extends Comparator<Card> {

    /**
     * Compares two cards.
     *
     * @param card1
     * 		The first card to compare.
     * @param card2
     * 		The second card to compare.
     * @return
     * 		-1 if the first card is less than the second card.
     * 		0 if the first card is equal to the second card.
     * 		1 if the first card is greater than the second card.
     */
    @Override
    int compare(Card card1, Card card2);

    /**
     * Returns true if the first card is less than the second card.
     *
     * @param card1
     *         The first card to compare.
     * @param card2
     *        The second card to compare.
     * @return True if the first card is less than the second card.
     */
    default boolean isLessThan(Card card1, Card card2) {
        return compare(card1, card2) < 0;
    }

    /**
     * Returns true if the first card is equal to the second card.
     *
     * @param card1
     *         The first card to compare.
     * @param card2
     *        The second card to compare.
     * @return True if the first card is equal to the second card.
     */
    default boolean isEqualTo(Card card1, Card card2) {
        return compare(card1, card2) == 0;
    }

    /**
     * Returns true if the first card is greater than the second card.
     *
     * @param card1
     *         The first card to compare.
     * @param card2
     *        The second card to compare.
     * @return True if the first card is greater than the second card.
     */
    default boolean isGreaterThan(Card card1, Card card2) {
        return compare(card1, card2) > 0;
    }

    /**
     * Returns true if the first card is less than or equal to the second card.
     *
     * @param card1
     *         The first card to compare.
     * @param card2
     *        The second card to compare.
     * @return True if the first card is less than or equal to the second card.
     */
    default boolean isLessThanOrEqualTo(Card card1, Card card2) {
        return compare(card1, card2) <= 0;
    }

    /**
     * Returns true if the first card is greater than or equal to the second card.
     *
     * @param card1
     *         The first card to compare.
     * @param card2
     *        The second card to compare.
     * @return True if the first card is greater than or equal to the second card.
     */
    default boolean isGreaterThanOrEqualTo(Card card1, Card card2) {
        return compare(card1, card2) >= 0;
    }



}
