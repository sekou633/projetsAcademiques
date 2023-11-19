package vue.graphic.components;

import model.card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <b>
 *     CardComponent is a component that represents a card.
 * </b>
 *
 * <p>
 *     A card can be visible or not.
 *     A card view is a JPanel.
 * </p>
 *
 * @author 	22013393
 * @version	1.0
 */
public class CardComponent extends JPanel {

    /**
     * The card to display.
     */
    private final Card card;

    /**
     *
     */
    private boolean visibility;

    /**
     * Creates a new card view.
     *
     * @param card The card to display.
     * @param visibility The visibility of the card.
     */
    public CardComponent(Card card, boolean visibility) {
        this.card = card;
        this.visibility = visibility;
        this.initComponents();
    }

    /**
     * Returns the card to display.
     *
     * @return The card to display.
     */
    public Card getCard() {
        return card;
    }

    /**
     * Returns the visibility of the card.
     *
     * @return The visibility of the card.
     */
    public boolean isVisibility() {
        return visibility;
    }

    /**
     * Sets the visibility of the card.
     *
     * @param visibility The visibility of the card.
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code.
     */
    private void initComponents() {

        this.setBackground(new Color(255, 255, 255));
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.setMaximumSize(new Dimension(100, 150));
        this.setMinimumSize(new Dimension(100, 150));
        this.setPreferredSize(new Dimension(100, 150));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
    }



    /**
     * Draws the card.
     *
     * @param g The graphics.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the representation of the card if visible.
        g.setFont(new Font("Arial", Font.BOLD, 20));
        if (this.visibility) {
            if (this.card.getColor().equals("RED")) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
            // Draw the symbol of the card to the center of the card.
            g.drawString(Character.toString(this.card.getSymbol()), this.getWidth() / 2 - 10, this.getHeight() / 2 + 10);
            // Draw the number of the card to the top left and bottom right of the card.
            g.drawString(Character.toString(this.card.getRank().getSymbol()), 5, 20);
            g.drawString(Character.toString(this.card.getRank().getSymbol()), this.getWidth() - 15, this.getHeight() - 5);
        }
        else{
            g.setColor(Color.BLACK);
            // Draw a cross
            g.drawLine(0, 0, this.getWidth(), this.getHeight());
            g.drawLine(0, this.getHeight(), this.getWidth(), 0);
        }
    }


    public Image getImage() {
        BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        this.paint(g2d);
        g2d.dispose();
        return bi;
    }
}


