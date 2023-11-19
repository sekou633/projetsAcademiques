package model.card;

/**
 * <b>
 *     Classic card comparator permits to compare two cards according to the natural order of classic playing cards.
 * </b>
 *
 * <p>
 *     The natural order of classic playing cards is defined by the following rules:
 * </p>
 * <ol>
 *     <li>
 *         The cards are ordered by their suit. So the order is: clubs, diamonds, hearts, spades.
 *     </li>
 *     <li>
 *         The cards of the same suit are ordered by their value. So the order is: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A.
 *         The order of the values is the same as the order of the ranks.
 *     </li>
 * </ol>
 *
 * @author 	22013393
 * @version	1.0
 */
public class ClassicCardComparator implements CardComparator {

    /**
     * Compares two cards according to the natural order of classic playing cards.
     *
     * @param card1 The first card to compare.
     * @param card2 The second card to compare.
     * @return A negative integer, zero, or a positive integer as the first card is less than, equal to, or greater than the second card.
     */
    @Override
    public int compare(Card card1, Card card2) {
        // Compare the suits of the cards (the order is: clubs, diamonds, hearts, spades).
        int result = String.valueOf(card1.getSuit().getLetter()).compareTo(String.valueOf(card2.getSuit().getLetter()));

        // If the suits are equal, compare the values of the cards (the order is: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A).
        if (result == 0) {
            result = card1.getRank().getValue() - card2.getRank().getValue();
        }

        return result;
    }
}
