package model.card;

/**
 * <b>
 *     This enum represents the suit of a card in classic playing cards.
 * </b>
 *
 * <p>
 *     A suit is represented by a letter, a color and a symbol. The letter is used to compare two suits.
 * </p>
 *
 * @author 	22013393
 * @version	1.0
 */

public enum Suit {

    CLUBS('C', "BLACK", '♣'),
    DIAMONDS('D', "RED", '♦'),
    HEARTS('H', "RED", '♥'),
    SPADES('S', "BLACK", '♠');

    /**
     * The letter of the suit.
     */
    private final char letter;

    /**
     * The color of the suit.
     */
    private final String color;

    /**
     * The symbol of the suit.
     */
    private final char symbol;

    /**
     * Creates a new suit with the given letter, color and symbol.
     *
     * @param letter The letter of the suit.
     * @param color  The color of the suit.
     * @param symbol The symbol of the suit.
     */
    private Suit(char letter, String color, char symbol) {
        this.letter = letter;
        this.color = color;
        this.symbol = symbol;
    }

    /**
     * Returns the letter of the suit.
     *
     * @return The letter of the suit.
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Returns the color of the suit.
     *
     * @return The color of the suit.
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns the symbol of the suit.
     *
     * @return The symbol of the suit.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Returns the suit corresponding to the given letter.
     *
     * @param letter The letter of the suit.
     * @return The suit corresponding to the given letter.
     */
    public static Suit getSuit(char letter) {
        for (Suit suit : Suit.values()) {
            if (suit.getLetter() == letter) {
                return suit;
            }
        }
        return null;
    }

}
