package vue;

import model.cards.Deck;
import observer.Listenable;
import observer.Listener;

/**
 * <b>
 *     A view of a deck of cards.
 * </b>
 *
 * <p>
 *     A view of a deck of cards is a graphical representation of a deck of cards.
 *     It is used to display the deck of cards. It will be updated when the deck of cards is updated.
 * </p>
 *
 * @author 22013393
 * @version 1.0
 * @see Deck
 */
public abstract class View implements Listener {

    /**
     * The listenable object.
     */
    private final Listenable listenable;

    /**
     * Creates a new view of a deck of cards.
     *
     * @param listenable
     * 		The listenable object.
     */
    protected View(Listenable listenable) {
        this.listenable = listenable;
        this.listenable.addListener(this);
    }

    /**
     *
     */

    /**
     * Returns the listenable object.
     *
     * @return The listenable object.
     */
    protected Listenable getListenable() {
        return this.listenable;
    }

    /**
     * Updates the view of the deck of cards.
     */
    public abstract void update();

    @Override
    public void notify(Listenable source) {
        this.update();
    }


}
