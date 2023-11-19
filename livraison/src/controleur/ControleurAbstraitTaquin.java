package controleur;

import lombok.Getter;
import lombok.Setter;
import modele.*;

/** 
 * Classe abstraite du controleur du taquin
*/

public abstract class ControleurAbstraitTaquin {

    /*Les attributs de la classe*/

    /** Le modele dont le controleur est en charge */
    protected TaquinModel modeleTaquin;

    /** Le numero contenu dans la case */
    protected int num = -1;

    /** Le nombre de permutation a faire */
    protected int nbrPermutations = -1;

    /** Le score de la partie */
    @Getter
    protected int score;

    protected boolean termine;

    /** 
     * Constructeur de la classe 
     * @param modeleTaquin le modele dont le controleur est en charge
    */
    protected ControleurAbstraitTaquin(TaquinModel modeleTaquin) {
        this.modeleTaquin = modeleTaquin;
        this.score = 0;
    }

    /** 
     * Modifie la valeur de numero
     * @param num le nouveau numero
    */

    public void setNumero(int num) {
        this.num = num;
        control();
    }

    /** 
     * Modifie la valeur de numero
     * @param currentCase la case actuel
    */

    public void setNumero(Case currentCase) {
        setNumero(currentCase.getValeur());
    }

    /** 
     * Modifie le noombre de permutation 
     * @param nbrPermutations le nouveau nombre de permutation
    */

    public void setNbrPermutations(int nbrPermutations) {
        this.nbrPermutations = nbrPermutations;
        control();
    }
    

    /** 
     * Applique une modification sur le modele
    */
    public abstract void control();

    /** 
     * Vérifie si le jeu est terminé ou pas
    */
    public boolean isTermine(){
        return termine;
    }
    
    /**
     * @return le modele du taquin
     */
    public TaquinModel getModel() {
        return modeleTaquin;
    }

}
