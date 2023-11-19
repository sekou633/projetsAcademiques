package vue.console;

import model.cards.Deck;
import vue.View;

/**
 * <b>
 *     This class represents a console view.
 * </b>
 *
 * <p>
 *     The console view is used to display the game in the console.
 * </p>
 *
 * @author 	22013393
 * @version	1.0
 */
public class ConsoleView extends View {

    /**
     * Creates a new console view.
     *
     * @param deck
     *         The deck of cards.
     */
    public ConsoleView(Deck deck) {
        super(deck);
    }

    @Override
    public void update() {
        Deck deck = (Deck) this.getListenable();

        System.out.println("The deck of cards contains " + deck.size() + " cards.");
        System.out.println("The deck of cards is empty: " + deck.isEmpty() + ".");
        System.out.println("The current card at the top of the deck is " + deck.getCard(0) + ".");
        System.out.println("The List of cards is : \n\n");

        deck.getCards().forEach(System.out::println);
    }
}
