package vue.actions;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;
import controleur.ControleurAbstraitTaquin;

/**
 * Classe representant une action de clique sur une case du taquin
 */

public class CliqueSurImage implements MouseListener{

    /* Attributs de la classe */

    private ControleurAbstraitTaquin controleur;

    private Container conteneur;

    /**
     * Constructeur de la classe 
     * @param controleur le controleur du taquin
     * @param conteneur la conteneur du taquin
    */

    public CliqueSurImage(ControleurAbstraitTaquin controleur, Container conteneur) {
        this.controleur = controleur;
        this.conteneur = conteneur;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component label = conteneur.getComponentAt(e.getX(), e.getY());
        int index = Arrays.asList(conteneur.getComponents()).indexOf(label);
        controleur.setNumero(controleur.getModel().getCases().get(index));
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // On ne fait rien
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // On ne fait rien
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // On ne fait rien
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // On ne fait rien
        
    }
    
}
