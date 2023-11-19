package view.graphics;

import model.participants.Players;
import observer.Listenable;
import observer.Listener;

import javax.swing.*;

/**
 * <b>
 *     This class represents a players table.
 * </b>
 *
 * @author 22013393
 * @version 1.0
 */
public class PlayersTable extends JTable implements Listener {

    /**
     * The players of the game.
     */
    private final Players players;

    /**
     * Creates a new players table with the given players.
     *
     * @param players
     * 		The players of the game.
     */
    public PlayersTable(Players players) {
        super(new PlayersAdaptater(players));
        this.players = players;
        this.players.addListener(this);
    }

    /**
     * Returns the players of the game.
     *
     * @return The players of the game.
     */
    public Players getPlayers() {
        return this.players;
    }

    @Override
    public void notify(Listenable listenable) {
        this.repaint();
    }
}
