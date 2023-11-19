package vue.graphic.components;

import javax.swing.*;
import java.awt.*;

/**
 * <b>
 *     DeckComponent is a component that represents a deck.
 * </b>
 *
 * <p>
 *     It was an abstract class that was used to represent general properties of a deck component.
 *     It was used to display the deck of cards.
 * </p>
 *
 * @author 22013393
 * @version 1.0
 */
public interface DeckComponent{




    /**
     * Returns the number of cards in the deck.
     *
     * @return The number of cards in the deck.
     */
    int getNumberOfCards();

    /**
     * Initializes the components of the deck component.
     */
    void initComponents();



}
