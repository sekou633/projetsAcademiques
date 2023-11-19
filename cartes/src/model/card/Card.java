package model.card;

/**
 * <b>
 *     This class represents a card in classic playing cards.
 * </b>
 *
 * <p>
 *     A card is represented by a rank and a suit. The rank and the suit are immutable.
 *     The rank and the suit can be used to compare two cards.
 *     The rank and the suit can be used to display the card.
 *     The rank and the suit are represented by an enum.
 *     The rank and the suit are not null.
 *     The rank and the suit are not empty.
 * </p>
 *
 * @author 	22013393
 * @version	1.0
 */
public class Card {

    /**
     * The rank of the card.
     */
    private final Rank rank;

    /**
     * The suit of the card.
     */
    private final Suit suit;

    /**
     * Creates a new card with the given rank and suit.
     *
     * @param rank
     * 		The rank of the card.
     * @param suit
     * 		The suit of the card.
     *
     * @throws NullPointerException if the rank or the suit is null.
     */
    public Card(Rank rank, Suit suit) {
        if (rank == null) {
            throw new NullPointerException("The rank of the card cannot be null.");
        }
        if (suit == null) {
            throw new NullPointerException("The suit of the card cannot be null.");
        }
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Creates a new card
     *
     * @param card
     *          The card to copy.
     * @throws NullPointerException if the card is null.
     */
    public Card(Card card) {
        if (card == null) {
            throw new NullPointerException("The card to copy cannot be null.");
        }
        this.rank = card.rank;
        this.suit = card.suit;
    }

    /**
     * Returns the rank of the card.
     *
     * @return
     * 		The rank of the card.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns the suit of the card.
     *
     * @return
     * 		The suit of the card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the value of the card.
     *
     * @return
     * 		The value of the card.
     */
    public int getValue() {
        return rank.getValue();
    }

    /**
     * Returns the letter of the card.
     *
     * @return
     * 		The letter of the card.
     */
    public char getLetter() {
        return suit.getLetter();
    }

    /**
     * Returns the color of the card.
     *
     * @return
     * 		The color of the card.
     */
    public String getColor() {
        return suit.getColor();
    }

    /**
     * Returns the symbol of the card.
     *
     * @return
     * 		The symbol of the card.
     */
    public char getSymbol() {
        return suit.getSymbol();
    }

    /**
     * Returns the string representation of the card.
     *
     * @return
     * 		The string representation of the card.
     */
    @Override
    public String toString() {
        return "( " + rank.getSymbol() + " , " + suit.getSymbol() + " )";
    }

    /**
     * Returns true if the given object is equal to this card.
     *
     * @param obj
     * 		The object to compare.
     * @return
     * 		True if the given object is equal to this card.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.rank != other.rank) {
            return false;
        }
        return this.suit == other.suit;
    }

    /**
     * Returns the hash code of this card.
     *
     * @return The hash code of this card.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.rank != null ? this.rank.hashCode() : 0);
        hash = 97 * hash + (this.suit != null ? this.suit.hashCode() : 0);
        return hash;
    }



}