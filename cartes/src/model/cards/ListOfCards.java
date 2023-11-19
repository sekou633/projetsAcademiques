package model.cards;


import model.card.Card;
import model.card.Rank;
import model.card.Suit;
import observer.ListenableObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <b>
 *     This class represents a list of cards and provides methods to manipulate the list.
 * </b>
 *
 *
 * @author 	22013393
 * @version	1.0
 */
public abstract class ListOfCards extends ListenableObject {

    /**
     * The list of cards.
     */
    private final List<Card> cards;

    /**
     * Creates a new list of cards with the given cards.
     *
     * @param cards
     * 		The cards in the list.
     *
     * @throws NullPointerException if the cards is null.
     */
    protected ListOfCards(List<Card> cards) {
        super();
        if (cards == null) {
            throw new NullPointerException("The cards cannot be null.");
        }
        this.cards = cards;
    }

    /**
     * Creates a new list of cards.
     */
    protected ListOfCards() {
        this(new ArrayList<>());
    }

    /**
     * Adds a card to the list.
     *
     * @param card
     * 		The card to add.
     *
     * @throws NullPointerException if the card is null.
     */
    public void addCard(Card card) {
        if (card == null) {
            throw new NullPointerException("The card cannot be null.");
        }
        cards.add(card);
        super.notifyListeners();
    }

    /**
     * Removes a card from the list.
     *
     * @param card
     * 		The card to remove.
     *
     * @throws NullPointerException if the card is null.
     */
    public void removeCard(Card card) {
        if (card == null) {
            throw new NullPointerException("The card cannot be null.");
        }
        cards.remove(card);
        super.notifyListeners();
    }

    /**
     * Clears the list.
     *
     */
    public void clear() {
        cards.clear();
        super.notifyListeners();
    }

    /**
     * Returns the cards in the list.
     *
     * @return
     * 		The cards in the list.
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Returns the number of cards in the list.
     *
     * @return
     * 		The number of cards in the list.
     */
    public int size() {
        return cards.size();
    }

    /**
     * Returns the card at the given index.
     *
     * @param index
     * 		The index of the card to return.
     *
     * @return
     * 		The card at the given index.
     *
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Card getCard(int index) {
        if (index < 0 || index >= cards.size()) {
            throw new IndexOutOfBoundsException("The index of the card is out of bounds.");
        }
        return cards.get(index);
    }

    /**
     * Returns the index of the given card.
     *
     * @param card
     * 		The card to find.
     *
     * @return
     * 		The index of the given card.
     *
     * @throws NullPointerException if the card is null.
     */
    public int indexOf(Card card) {
        if (card == null) {
            throw new NullPointerException("The card cannot be null.");
        }
        return cards.indexOf(card);
    }

    /**
     * Returns true if the list contains the given card.
     *
     * @param card
     * 		The card to find.
     *
     * @return
     * 		True if the list contains the given card.
     *
     * @throws NullPointerException if the card is null.
     */
    public boolean contains(Card card) {
        if (card == null) {
            throw new NullPointerException("The card cannot be null.");
        }
        return cards.contains(card);
    }

    /**
     * Returns true if the list is empty.
     *
     * @return
     * 		True if the list is empty.
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Returns true if the list contains the given cards.
     *
     * @param cards
     * 		The cards to find.
     *
     * @return
     * 		True if the list contains the given cards.
     *
     * @throws NullPointerException if the cards is null.
     */
    public boolean containsAll(List<Card> cards) {
        if (cards == null) {
            throw new NullPointerException("The cards cannot be null.");
        }
        // We wrap the list in a set to remove duplicates.
        return new HashSet<>(this.cards).containsAll(cards);
    }

    /**
     * Get a list of cards that are not in the given list.
     *
     * @param cards
     *         The cards to find.
     *
     * @return A list of cards that are not in the given list.
     */
    public List<Card> getNotIn(List<Card> cards) {
        List<Card> notIn = new ArrayList<>();
        for (Card card : this.cards) {
            if (!cards.contains(card)) {
                notIn.add(card);
            }
        }
        return notIn;
    }

    /**
     * Get list of cards that are between the given indexes.
     *
     * @param from
     *         The index to start from.
     * @param to
     *        The index to end at.
     *
     * @return A list of cards that are between the given indexes.
     * @throws IndexOutOfBoundsException if the indexes are out of bounds.
     */
    public List<Card> getBetween(int from, int to) {
        if (from < 0 || from >= cards.size() || to < 0 || to >= cards.size()) {
            throw new IndexOutOfBoundsException("The indexes are out of bounds.");
        }

        return cards.subList(from, to);
    }

    /**
     * Get a list of cards that are a specific suit.
     *
     * @param suit
     *        The suit to find.
     *
     * @return A list of cards that are a specific suit.
     */
    public List<Card> getSuit(Suit suit) {

        return cards.stream().filter(card -> card.getSuit() == suit).collect(Collectors.toList());
    }

    /**
     * Get a list of cards that are a specific rank.
     *
     * @param rank
     *        The rank to find.
     *
     * @return A list of cards that are a specific rank.
     */
    public List<Card> getRank(Rank rank) {

        return cards.stream().filter(card -> card.getRank() == rank).collect(Collectors.toList());
    }

    /**
     * Sorts the list of cards.
     *
     * @param comparator
     *         The comparator to use.
     */
    public void sort(Comparator<Card> comparator) {
        cards.sort(comparator);
        super.notifyListeners();
    }

    /**
     * Sort the list of cards by rank.
     *
     * @param ascending
     *        True if the cards should be sorted in ascending order.
     * @param aceHigh
     *       True if the ace should be high.
     */
    public void sortByRank(boolean ascending, boolean aceHigh) {
        sort((card1, card2) -> {
            int rank1 = card1.getRank().getValue();
            int rank2 = card2.getRank().getValue();
            if (aceHigh) {
                if (rank1 == 1) {
                    rank1 = 14;
                }
                if (rank2 == 1) {
                    rank2 = 14;
                }
            }

            if (ascending) {
                return rank1 - rank2;
            } else {
                return rank2 - rank1;
            }
        });
    }

    /**
     * Sort the list of cards by suit.
     *
     * @param ascending
     *        True if the cards should be sorted in ascending order.
     */
    public void sortBySuit(boolean ascending) {
        // The order of the suits it is the same as the order of the enum.
        sort((card1, card2) -> {
            int suit1 = card1.getSuit().ordinal();
            int suit2 = card2.getSuit().ordinal();

            if (ascending) {
                return suit1 - suit2;
            } else {
                return suit2 - suit1;
            }
        });
    }

    /**
     * Iterator for the list of cards.
     *
     * @return Iterator for the list of cards.
     */
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    /**
     * Shuffles the list of cards.
     *
     * @param random
     *       The random to use.
     * @throws NullPointerException if the random is null.
     */
    public void shuffle(Random random) {
        if (random == null) {
            throw new NullPointerException("The random for shuffling cannot be null.");
        }
        Collections.shuffle(cards, random);
        super.notifyListeners();
    }

    /**
     * Shuffles the list of cards.
     */
    public void shuffle() {
        shuffle(new Random());
    }

    /**
     * Returns the string representation of the list of cards.
     *
     * @return The string representation of the list of cards.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [size=" + cards.size() + "\n" + cards + "]";
    }












}
