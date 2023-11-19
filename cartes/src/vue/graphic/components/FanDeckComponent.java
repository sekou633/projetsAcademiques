package vue.graphic.components;

import model.cards.Deck;

import javax.swing.*;
import java.awt.*;

/**
 * <b>
 *     FanDeckComponent is a component that permits to display a fan of cards.
 * </b>
 *
 * @author 22013393
 * @version 1.0
 */
public class FanDeckComponent extends JPanel implements DeckComponent {

    /**
     * The deck of cards to display.
     */
    private final Deck deck;

    /**
     * Creates a new fan deck component with the given deck of cards.
     *
     * @param deck
     * 		The deck of cards to display.
     */
    public FanDeckComponent(Deck deck) {
        this.deck = deck;
        this.initComponents();
    }

    /**
     * Returns the deck of cards to display.
     *
     * @return
     * 		The deck of cards to display.
     */
    public Deck getDeck() {
        return this.deck;
    }

    @Override
    public int getNumberOfCards() {
        return this.deck.size();
    }

    @Override
    public void initComponents() {
        // The position of the first card.
        int x = 0;
        int y = 0;

        // Arc of the fan.
        int arc = 360 / this.getNumberOfCards();
        // The angle of the first card.
        int angle = 0;
        // The angle of the last card.
        int lastAngle = 0;

        // The width of the fan.
        int width = 0;
        // The height of the fan.
        int height = 0;

        // Adds the cards to the fan.
        for (int i = 0; i < this.getNumberOfCards(); i++) {
            // The card to add.
            CardComponent cardComponent = new CardComponent(this.deck.getCard(i), true);
            // The size of the card.
            Dimension cardSize = cardComponent.getPreferredSize();
            // The width of the card.
            int cardWidth = (int) cardSize.getWidth();
            // The height of the card.
            int cardHeight = (int) cardSize.getHeight();

            // The width of the fan.
            width = Math.max(width, cardWidth);
            // The height of the fan.
            height = Math.max(height, cardHeight);

            // The angle of the card.
            angle = lastAngle + arc;
            // The position of the card.
            x = (int) (Math.cos(Math.toRadians(angle)) * cardWidth);
            y = (int) (Math.sin(Math.toRadians(angle)) * cardHeight);

            // Adds the card to the fan.
            this.add(cardComponent);
            // Sets the position of the card.
            cardComponent.setLocation(x, y);
            // Sets the size of the card.
            cardComponent.setSize(cardWidth, cardHeight);
            // Sets the angle of the card.
//            cardComponent.setAngle(angle);

            // The angle of the last card.
            lastAngle = angle;
        }
    }
}
