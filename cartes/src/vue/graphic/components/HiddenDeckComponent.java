package vue.graphic.components;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * <b>
 *     HiddenDeckComponent is a component that represents a hidden deck.
 * </b>
 *
 * <p>
 *     A hidden deck is a deck of cards that is not visible. It is used to display the deck of cards.
 *     We can paint a hidden deck in different ways.
 * </p>
 *
 * <u>
 *     <li> A hidden deck can be painted  horizontally or vertically. </li>
 *     <li>
 *         We can paint a hidden deck by shifiting the cards.
 *     </li>
 * </u>
 *
 * @author 	22013393
 * @version	1.0
 */
public class HiddenDeckComponent extends JPanel implements DeckComponent, MouseListener{

    /**
     * The width of the card.
     */
    private final static int CARD_WIDTH = 100;

    /**
     * The height of the card.
     */
    private final static int CARD_HEIGHT = 150;

    /**
     * The number of cards in the deck.
     */
    private int numberOfCards;

    /**
     * boolean that indicates if the deck is painted horizontally or vertically.
     */
    private final boolean horizontal;

    /**
     * The vertical shift of the cards.
     */
    private final int verticalShift;

    /**
     * The horizontal shift of the cards.
     */
    private final int horizontalShift;

    /**
     * The current x position of the card.
     */
    private int currentX;

    /**
     * The current y position of the card.
     */
    private int currentY;

    /**
     * Creates a new hidden deck component.
     *
     * @param numberOfCards The number of cards in the deck.
     * @param horizontal The orientation of the deck.
     * @param verticalShift The vertical shift of the cards.
     * @param horizontalShift The horizontal shift of the cards.
     */
    public HiddenDeckComponent(int numberOfCards, boolean horizontal, int verticalShift, int horizontalShift) {
        this.numberOfCards = numberOfCards;
        this.horizontal = horizontal;
        this.verticalShift = verticalShift;
        this.horizontalShift = horizontalShift;
        this.currentX = 0;
        this.currentY = 0;
        this.addMouseListener(this);

    }

    /**
     * Returns the orientation of the deck.
     *
     * @return The orientation of the deck.
     */
    public boolean isHorizontal() {
        return this.horizontal;
    }

    /**
     * Returns the vertical shift of the cards.
     *
     * @return The vertical shift of the cards.
     */
    public int getVerticalShift() {
        return this.verticalShift;
    }

    /**
     * Returns the horizontal shift of the cards.
     *
     * @return The horizontal shift of the cards.
     */
    public int getHorizontalShift() {
        return this.horizontalShift;
    }

    @Override
    public int getNumberOfCards() {
        return this.numberOfCards;
    }

    @Override
    public void initComponents() {

    }


    /**
     * Paints the hidden deck component.
     *
     * @param g The graphics context.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Initialize the x and y coordinates.
        int x = 0;
        int y = 0;

        if (this.isHorizontal()) {
            for (int i = 0; i < this.getNumberOfCards(); i++) {
                // Set the color of the deck.
                g.setColor(Color.BLACK);
                // Draw the card.
                g.drawRect(this.currentX, this.currentY, CARD_WIDTH, CARD_HEIGHT);
                // Draw a cross on the card.
                g.drawLine(this.currentX, this.currentY, this.currentX + CARD_WIDTH, this.currentY + CARD_HEIGHT);
                g.drawLine(this.currentX + CARD_WIDTH, this.currentY, this.currentX, this.currentY + CARD_HEIGHT);
                //Set the color for filling the card.
                g.setColor(Color.WHITE);
                // Fill the card.
                g.fillRect(this.currentX, this.currentY, CARD_WIDTH, CARD_HEIGHT);
                // Shift the x coordinate.
                this.currentX += this.getHorizontalShift();

            }
        } else {
            for (int i = 0; i < this.getNumberOfCards(); i++) {
                //Set the color for filling the card.
                g.setColor(Color.WHITE);
                // Fill the card.
                g.fillRect(x, y, CARD_WIDTH, CARD_HEIGHT);
                // Set the color of the deck.
                g.setColor(Color.BLACK);
                // Draw the card.
                g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
                // Draw a cross on the card.
                g.drawLine(x, y, y + CARD_WIDTH, y + CARD_HEIGHT);
                g.drawLine(x + CARD_WIDTH, y, x, y + CARD_HEIGHT);

                // Shift the y coordinate.
                y += this.getVerticalShift();
                // Shift the x coordinate.
                x += this.getHorizontalShift();
            }
        }
    }



    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        // Remove the top card of the deck.
        this.numberOfCards--;
        // Clear the rectangle.
        this.repaint();


    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
