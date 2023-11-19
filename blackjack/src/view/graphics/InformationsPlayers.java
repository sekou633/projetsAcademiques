package view.graphics;

import model.participants.Players;

import javax.swing.*;

/**
 * <b>
 *     Frame that show the informations of the players during the game.
 * </b>
 *
 * @author 22013393
 * @version 1.0
 */
public class InformationsPlayers extends JFrame {


    /**
     * Creates a new frame with the given players.
     *
     * @param players
     * 		The players of the game.
     */
    public InformationsPlayers(Players players) {
        super("Informations Players");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new PlayersTable(players));
        this.pack();
        this.setVisible(true);
    }


}
