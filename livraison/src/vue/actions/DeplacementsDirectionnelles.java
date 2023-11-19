package vue.actions;

import java.awt.event.*;

import controleur.ControleurAbstraitTaquin;

/**
 * Classe représentant les déplacements directionnelles du taquin
 */

public class DeplacementsDirectionnelles implements KeyListener{
    
    /* Attributs de la classe */
    private ControleurAbstraitTaquin controleur;

    /**
     * Constructeur de la classe
     * @param controleur le controleur du taquin
     */

    public DeplacementsDirectionnelles(ControleurAbstraitTaquin controleur) {
        this.controleur = controleur;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int positionVide = controleur.getModel().getPositionVide();
        int position = positionVide;

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                position -= 1;
                System.out.println("Déplacement vers la gauche");
                System.out.println();
                break;
                
            case KeyEvent.VK_RIGHT:
                position += 1;
                System.out.println("Déplacement vers la droite");
                System.out.println();
                break;

            case KeyEvent.VK_UP:
                position -= controleur.getModel().getLargeur();
                System.out.println("Déplacement vers le haut");
                System.out.println();
                break;

            case KeyEvent.VK_DOWN:
                position += controleur.getModel().getLargeur();
                System.out.println("Déplacement vers le bas");
                System.out.println();
                break;

            default:
                break;
        }
        
        position = controleur.getModel().indexesCasesAutour(positionVide).contains(position) ? position : positionVide;
        
        controleur.setNumero(controleur.getModel().getCases().get(position));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // On ne fait rien        
    }

    @Override
    public void keyTyped(KeyEvent e) {
      // On ne fait rien 
    }
    
}
