package view.graphics;

import controller.BlackjackController;

import javax.swing.*;
import java.awt.*;

/**
 * <b>
 *     ActionsComponent is a component that represents all the actions of a player.
 * </b>
 *
 *
 */
public class ActionsComponent extends JPanel {

    /**
     * The controller of the game.
     */
    private final BlackjackController controller;
    /**
     * Creates a new actions component.
     *
     * @param controller The controller of the game.
     */
    public ActionsComponent(BlackjackController controller) {
        this.controller = controller;
        this.initComponents();
    }

    /**
     * Initializes the components of the actions component.
     */
    private void initComponents() {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JButton hitButton = new JButton("Hit");
        hitButton.addActionListener(e -> {
            this.controller.hit();
            if (this.controller.isDealerTurn()){
                this.controller.dealerPlay();
            }
        });

        this.add(hitButton);
        JButton standButton = new JButton("Stand");
        standButton.addActionListener(e -> {
            this.controller.stand();
            if (this.controller.isDealerTurn()){
                this.controller.dealerPlay();
            }
        });
        this.add(standButton);
        JButton doubleButton = new JButton("Double");
        doubleButton.addActionListener(e -> {
            this.controller.dealerPlay();
            if (this.controller.isDealerTurn()){
                this.controller.dealerPlay();
            }
        });
        this.add(doubleButton);
    }

    /**
     * Remove all action on the buttons.
     */
    public void removeAction() {
        // The panel don't capture the event.
        this.setEnabled(false);
    }

    /**
     * Add all action on the buttons.
     */
    public void addAction() {
        // The panel don't capture the event.
        this.setEnabled(true);
    }



}
