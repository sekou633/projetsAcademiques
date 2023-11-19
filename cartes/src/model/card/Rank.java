package model.card;

/**
 * <b>
 *     This enum represents the rank of a card in classic playing cards.
 * </b>
 *
 * <p>
 *     A rank is represented by a value and a symbol. The value can be used to compare two ranks.
 *     The symbol is used to display the rank. The symbol is a single character. The value is an integer.
 * </p>
 *
 * @author 	22013393
 * @version	1.0
 */
public enum Rank {

    TWO(2, '2'),
    THREE(3, '3'),
    FOUR(4, '4'),
    FIVE(5, '5'),
    SIX(6, '6'),
    SEVEN(7, '7'),
    EIGHT(8, '8'),
    NINE(9, '9'),
    JACK(10, 'J'),
    QUEEN(11, 'Q'),
    KING(12, 'K'),
    ACE(13, 'A');

    /**
     * The value of the rank.
     */
    private final int value;

    /**
     * The symbol of the rank.
     */
    private final char symbol;

    /**
     * Creates a new rank with the given value and symbol.
     *
     * @param value
     * 		The value of the rank.
     * @param symbol
     * 		The symbol of the rank.
     */
    private Rank(int value, char symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    /**
     * Returns the value of the rank.
     *
     * @return
     * 		The value of the rank.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the symbol of the rank.
     *
     * @return
     * 		The symbol of the rank.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Returns the rank with the given value.
     *
     * @param value
     * 		The value of the rank.
     * @return
     * 		The rank with the given value.
     */
    public static Rank getRank(int value) {
        for (Rank rank : Rank.values()) {
            if (rank.getValue() == value) {
                return rank;
            }
        }
        return null;
    }

    /**
     * Returns the rank with the given symbol.
     *
     * @param symbol
     * 		The symbol of the rank.
     * @return
     * 		The rank with the given symbol.
     */
    public static Rank getRank(char symbol) {
        for (Rank rank : Rank.values()) {
            if (rank.getSymbol() == symbol) {
                return rank;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }




}