package observeur;

/**
 * <b> Interface implementée par tous les objets Taquin ecoutables. </b>
 * 
 * <p>
 * Cette interface spécifie l'ensemble des methodes que doivent definir les
 * objets Taquin ecoutables.
 * </p>
 * 
 * @version 1.0
 */

public interface TaquinEcoutable {

    /**
     * Ajoute un ecouteur à la liste des ecouteurs
     * 
     * @param ecouteur l'ecouteur à rajouter
     */
    void ajoutEcouteur(EcouteurTaquin ecouteur);

    /**
     * Retire un ecouteur à la liste des ecouteurs
     * 
     * @param ecouteur l'ecouteur à retirer
     */
    void retraitEcouteur(EcouteurTaquin ecouteur);

    /**
     * Retire tous les ecouteurs à la liste des ecouteurs;
     * 
     */
    void retraitEcouteurs();
}
