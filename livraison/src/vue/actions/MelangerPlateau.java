package vue.actions;

import java.awt.event.*;

import javax.swing.JOptionPane;

import vue.composants.PlateauJouable;

/**
 * Classe représentant une action qui permet de mélanger le tableau
 */
public class MelangerPlateau implements KeyListener, ActionListener{

    /* Attributs de la classe */
    private PlateauJouable plateau;

    /**
     * Constructeurs de la classe
     * @param plateau le plateau du taquin
    */

    public MelangerPlateau(PlateauJouable plateau) {
        this.plateau = plateau;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane jop = new JOptionPane();
        String nbrPermutations = JOptionPane.showInputDialog(null, "Veuillez rentrer le nombre de permutations à effectuer",
                "Effectuer des permutations", JOptionPane.QUESTION_MESSAGE);
        
            plateau.getControleur().setNbrPermutations(Integer.parseInt(nbrPermutations));
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_M && e.isControlDown()) {
            plateau.getControleur().setNbrPermutations(2402);
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
