package model.cards;

import model.card.Card;
import model.card.Rank;
import model.card.Suit;
import model.cards.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>
 *     A factory of decks of cards.
 * </b>
 *
 * <p>
 *     A factory of decks of cards permits to create a deck of cards with a predefined number of cards.
 *     The factory of decks of cards will use to create any deck of cards.
 * </p>
 * <p>
 *     This class is final and immutable. It is not possible to create a subclass of this class.
 *     This class is not possible to instantiate.
 * </p>
 *
 * @author 	22013393
 * @version	1.0
 */
public final class FactoryDeck {

        /**
        * <b>
        *     Constructor of the class
        * </b>
        *
        * <p>
        *     This constructor is private because this class is final and immutable
        * </p>
        */
        private FactoryDeck() {}

    /**
     * <b>
     *     Creates a deck of 52 cards.
     * </b>
     *
     * @return A deck of 52 cards.
     */
    public static Deck createDeck52() {
        List<Card> cards = new ArrayList<>();

        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }

        return new Deck(cards);
    }

    /**
     * <b>
     *     Creates a deck of 32 cards.
     * </b>
     *
     * <p>
     *     The deck of 32 cards contains 8 cards of each rank and suit.
     *     Each suit contains 8 cards : 7, 8, 9, 10, Jack, Queen, King, Ace.
     * </p>.
     *
     * @return A deck of 32 cards.
     */
    public static Deck createDeck32() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            // Add 7, 8, 9, 10, Jack, Queen, King, Ace
            for (int i = 7; i <= 13; i++) {
                cards.add(new Card(Rank.getRank(i), suit));
            }
        }
        return new Deck(cards);
    }


}
