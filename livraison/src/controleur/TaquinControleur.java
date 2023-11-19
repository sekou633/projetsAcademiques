package controleur;

import java.util.List;

import modele.*;

/**
 * Classe représentant le controleur du taquin
 */
public class TaquinControleur extends ControleurAbstraitTaquin {

    /** 
     * Constructeur de la classe
     * @param modeleTaquin le modele du taquin
    */

    public TaquinControleur(TaquinModel modeleTaquin) {
        super(modeleTaquin);
    }

    /** 
     * @return la position de la case vide du modele
    */

    public int getModelPositionVide() {
        return modeleTaquin.getPositionVide();
    }

    /**
     * Modifie la position de la case vide du modele
     * @param positionVide la position vide du modele
    */

    public void setModelPositionVide(int positionVide) {
        modeleTaquin.setPositionVide(positionVide);
    }

    /**
     * @return la largeur du modele
     */

    public int getModelLargeur() {
        return modeleTaquin.getLargeur();
    }

    /**
     * @return la longueur du modele
    */

    public int getModelLongueur() {
        return modeleTaquin.getLongueur();
    }
    
    /**
     * @return la taille du modele
     */

    public int getModelTaille() {
        return modeleTaquin.getTaille();
    }

    /**
     * @return la liste des cases du modele
     */

    public List<Case> getModelCases() {
        return modeleTaquin.getCases();
    }

    @Override
    public void control() {

        if (num != -1) {
            Case c = new Case(num);
            boolean ok = modeleTaquin.deplace(c);
            score = ok ? score + 1 : score;
            
        }
        if (nbrPermutations != -1) {
            modeleTaquin.melanger(nbrPermutations);
            score = 0;
        }

        if (!termine) {
            termine = this.modeleTaquin.estTermine();
        }
        num = -1;
        nbrPermutations = -1;
    }

}
