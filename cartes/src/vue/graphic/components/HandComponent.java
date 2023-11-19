package vue.graphic.components;


import model.card.Card;
import model.card.Suit;
import model.cards.Hand;
import observer.Listenable;
import observer.ListenableObject;
import observer.Listener;

import javax.swing.*;
import java.awt.*;

/**
 * <b>
 *     A hand Component is a component that represents a hand in a graphic view.
 * </b>
 *
 * <p>
 *     A hand component is a JPanel.
 * </p>
 */
public class HandComponent extends JPanel implements Listener{

    /**
     * The hand to display.
     */
    private final Hand hand;

    /**
     * The width of a card.
     */
    private static final int CARD_WIDTH = 100;

    /**
     * The height of a card.
     */
    private static final int CARD_HEIGHT = 150;

    /**
     * The horizontal gap between two cards.
     */
    private static final int HORIZONTAL_GAP = 30;

    /**
     * The vertical gap between two cards.
     */
    private static final int VERTICAL_GAP = 5;

    /**
     * The next rotation of a card.
     */
    private static final int NEXT_ROTATION = 5;

    /**
     * Creates a new hand component.
     *
     * @param hand The hand to display.
     */
    public HandComponent(Hand hand) {
        this.hand = hand;
        this.initComponents();
    }

    /**
     * Initializes the components of the hand component.
     */
    private void initComponents() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP));
        this.hand.addListener(this);

    }

    @Override
    public void notify(Listenable listenable) {

        // Remove all the cards.
        this.removeAll();
        this.revalidate();
        this.repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Current rotation angle
        double angle = 5;

        // The initial position of the first card will be at the center of the panel
        int x = this.getWidth() / 2 - CARD_WIDTH / 2;
        int y = this.getHeight() / 2 - CARD_HEIGHT / 2;


        // We draw the cards.
        for (Card card : this.hand.getCards()) {
            // We draw the card.
            this.drawCard(g2d, card, x, y, CARD_WIDTH, CARD_HEIGHT);

            g2d.rotate(Math.toRadians(angle), x + CARD_WIDTH / 2, y + CARD_HEIGHT / 2);

            // We update the position of the next card.
            x += Math.cos(Math.toRadians(angle)) * (HORIZONTAL_GAP);
            y -= VERTICAL_GAP;

        }
        g2d.dispose();

//        // We will draw the cards in a stack with a gap between each card and a rotation angle.
//        for (int i = 0; i < hand.getNumberOfCards(); i++) {
//            // Draw the card
//            this.drawCard(g2d, hand.getCard(i), x, y, CARD_WIDTH, CARD_HEIGHT);
//
//            // Rotate the card
//            g2d.rotate(Math.toRadians(angle), x, y);
//
//            // The next x
//
//            // Change the rotation angle
//            angle += NEXT_ROTATION;
//        }
    }

        /**
         * Draw a card
         *
         * @param g2d The graphics
         * @param card The card to draw
         * @param x The x position
         * @param y The y position
         * @param width The width
         * @param height The height
         */
        private void drawCard(Graphics2D g2d, Card card, int x, int y, int width, int height){
            // Set the color
            g2d.setColor(Color.WHITE);
            // Draw the rectangle
            g2d.fillRect(x, y, width, height);

            // If the card is heart or diamond
            if(card.getSuit() == Suit.HEARTS || card.getSuit() == Suit.DIAMONDS){
                // Set the color to red
                g2d.setColor(Color.RED);
            } else {
                // Set the color to black
                g2d.setColor(Color.BLACK);
            }

            // Draw the rectangle
            g2d.drawRect(x, y, width, height);

            // Size of drawn text is 17 if the width is under 100 and 20 otherwise
            int fontSize = width < 100 ? 17 : 20;
            g2d.setFont(new Font("Arial", Font.BOLD, fontSize));

            // Draw the rank of the card at each corner
            g2d.drawString(card.getRank().toString(), x + 5, y + 20);
            g2d.drawString(card.getRank().toString(), x + width - 20, y + height - 5);
            g2d.drawString(card.getRank().toString(), x + 5, y + height - 5);
            g2d.drawString(card.getRank().toString(), x + width - 20, y + 20);

            // Draw a suit to each middle corner and to the center of the card
            g2d.drawString(card.getSuit().getSymbol() + "", x + width / 2 - fontSize / 2, y + height / 2 + fontSize / 2);
            g2d.drawString(card.getSuit().getSymbol() + "", x + width / 4 - fontSize / 2, y + height / 4 + fontSize / 2);
            g2d.drawString(card.getSuit().getSymbol() + "", x + width / 4 - fontSize / 2, y + height * 3 / 4 + fontSize / 2);
            g2d.drawString(card.getSuit().getSymbol() + "", x + width * 3 / 4 - fontSize / 2, y + height / 4 + fontSize / 2);
            g2d.drawString(card.getSuit().getSymbol() + "", x + width * 3 / 4 - fontSize / 2, y + height * 3 / 4 + fontSize / 2);

        }





}
