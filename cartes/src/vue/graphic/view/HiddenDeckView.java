package vue.graphic.view;

import model.cards.Deck;
import vue.View;
import vue.graphic.components.FanDeckComponent;
import vue.graphic.components.HiddenDeckComponent;
import vue.graphic.components.VisibleDeckComponent;

import javax.swing.*;
import java.awt.*;

/**
 * <b>
 *     HiddenDeckView is a view of a hidden deck of cards.
 * </b>
 *
 * <p>
 *     A hidden deck view is a graphical representation of a hidden deck of cards.
 *     It is used to display the hidden deck of cards. It will be updated when the hidden deck of cards is updated.
 *     It is a JFrame
 * </p>
 *
 * @author 22013393
 * @version 1.0
 */
public class HiddenDeckView extends View {

    /**
     * The frame of the hidden deck view.
     */
    private final JFrame frame;
    /**
     * Creates a new hidden deck view.
     *
     * @param deck The hidden deck of cards.
     * @param title The title of the hidden deck view.
     */
    public HiddenDeckView(Deck deck, String title) {
        super(deck);
        this.frame = new JFrame(title);
        this.initComponents();
    }

    /**
     * Initializes the components of the hidden deck view.
     */
    private void initComponents() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HiddenDeckComponent hiddenDeckComponent = new HiddenDeckComponent(((Deck) this.getListenable()).size(), false, 5, 5);
        VisibleDeckComponent visibleDeckComponent = new VisibleDeckComponent(((Deck) this.getListenable()));
//        this.frame.setContentPane(hiddenDeckComponent);
        this.frame.setContentPane(visibleDeckComponent);
        FanDeckComponent fanDeckComponent = new FanDeckComponent(((Deck) this.getListenable()));
//        this.frame.add(fanDeckComponent);
        this.frame.setPreferredSize(new Dimension(200, 300));
        this.frame.pack();
        this.frame.setVisible(true);
    }

    @Override
    public void update() {
        this.frame.pack();
    }
}
