package vue.actions;

import java.awt.event.*;

import vue.composants.PlateauJouable;


/**
 * Classe représentant une action permettant de redimensionner le plateau du taquin
 */
public class RedimensionnerPlateau implements ComponentListener{

    /* Attributs de la classe */
    private PlateauJouable plateau;
    
    /**
     * Constructeur de la classe
     * @param plateau le plateau du taquin
     */
    public RedimensionnerPlateau(PlateauJouable plateau) {
        this.plateau = plateau;
    }
    @Override
    public void componentHidden(ComponentEvent e) {
       // On ne fait rien
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // On ne fait rien
    }

    @Override
    public void componentResized(ComponentEvent e) {
        plateau.initialiseAssociationObjetEtImage();
        
    }

    @Override
    public void componentShown(ComponentEvent e) {
       // On ne fait rien
    }
    
}
