package model.cards;

import java.util.*;
import java.util.stream.Collectors;

import model.card.Card;
import model.card.Rank;
import model.card.Suit;
import model.cards.ListOfCards;
import observer.ListenableObject;

/**
 * <b>
 *     This class represents a deck of cards in classic playing cards.
 * </b>
 *
 * <p>
 *     A deck of cards is represented by a List of cards.
 *     The deck of cards can be used to shuffle the cards.
 *     The deck of cards can be used to display the cards.
 *     The deck of cards can be used to compare two decks of cards.
 *     The deck of cards can be used to sort the cards.
 * </p>
 */
public class Deck extends ListOfCards {

    /**
     * Creates a new deck of cards.
     *
     * @param cards
     *         The cards in the deck.
     *
     * @throws NullPointerException if the cards is null.
     */
    public Deck(List<Card> cards) {
        super(cards);
    }


    /**
     * Deal one card from the deck.
     *
     */
    public Card deal() {
        Card card = this.getCard(0);
        this.removeCard(card);
        return card;
    }

    /**
     * Deal a number of cards from the deck.
     *
     * @param number
     * 		The number of cards to deal.
     */
    public List<Card> deal(int number) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            cards.add(this.deal());
        }
        return cards;
    }

    /**
     * Deal all the cards from the deck.
     *
     * @return The cards of the deck.
     */
    public List<Card> dealAll() {
        List<Card> cards = new ArrayList<>(this.getCards());
        this.clear();
        return cards;
    }

}

