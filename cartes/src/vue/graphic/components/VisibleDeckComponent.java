package vue.graphic.components;

import model.cards.Deck;

import javax.swing.*;

/**
 * <b>
 *     VisibleDeckComponent is a component that represents a visible deck.
 * </b>
 *
 *
 * @author 	22013393
 * @version	1.0
 */
public class VisibleDeckComponent extends JScrollPane implements DeckComponent{

    /**
     * The deck to display.
     */
    private final Deck deck;

    /**
     * The number of cards in the deck.
     */
    private int numberOfCards;

    /**
     * Creates a new visible deck component.
     *
     * @param deck The deck to display.
     */
    public VisibleDeckComponent(Deck deck) {
        super();
        this.deck = deck;
        this.numberOfCards = deck.size();
        this.initComponents();
    }

    /**
     * Returns the number of cards in the deck.
     *
     * @return The number of cards in the deck.
     */
    @Override
    public int getNumberOfCards() {
        return numberOfCards;
    }

    /**
     * Initializes the components of the deck component.
     */
    @Override
    public void initComponents() {
        this.setVisible(true);
        // Add the cards
        JPanel cards = new JPanel();
        for (int i = 0; i < this.numberOfCards; i++) {
            cards.add(new CardComponent(this.deck.getCard(i), true));
        }
        this.setViewportView(cards);
    }

}
