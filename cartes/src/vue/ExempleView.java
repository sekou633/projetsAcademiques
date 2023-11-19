package vue;

import vue.graphic.components.HiddenDeckComponent;

import javax.swing.*;

/**
 * <b>
 *     A example view.
 * </b>
 *
 */
public class ExempleView extends JFrame {
    /**
     * Creates a new example view.
     */
    public ExempleView() {
        this.initComponents();
    }

    /**
     * Initializes the components of the example view.
     */
    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Exemple");
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        // Add HiddenDeckComponent
        this.add(new HiddenDeckComponent(32, false, 5, 5));

    }



}
