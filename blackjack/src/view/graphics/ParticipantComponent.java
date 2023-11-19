package view.graphics;

import model.participants.Participant;
import model.participants.Player;
import observer.Listenable;
import observer.Listener;
import vue.graphic.components.HandComponent;

import javax.swing.*;
import java.awt.*;

/**
 * <b>
 *     ParticipantComponent is a component that represents a participant in a gui.
 * </b>
 *
 * <p>
 *     When a player is displayed his name, his hand and the value of his hand are displayed.
 * </p>
 *
 * @author 	22013393
 * @version	1.0
 */
public class ParticipantComponent extends JPanel implements Listener {

    /**
     * The participant to display.
     */
    private final Participant participant;

    /**
     * The label that displays the value of the hand.
     */
    private final JLabel handValueLabel;


    /**
        * Creates a new player component.
         *
         * @param participant The participant to display.
         */
        public ParticipantComponent(Participant participant) {
            this.participant = participant;
            this.handValueLabel = new JLabel("Value: " + this.participant.evaluateHand());
            this.initComponents();
        }

        /**
        * Initializes the components of the player component.
        */
        private void initComponents() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(new JLabel(this.participant.getName()));
            this.add(new HandComponent(this.participant.getHand()));
            this.add(this.handValueLabel);

            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            this.participant.addListener(this);
        }

    @Override
    public void notify(Listenable listenable) {
        this.handValueLabel.setText("Value: " + this.participant.evaluateHand());
        this.handValueLabel.revalidate();

    }
}
