package modele;

import observeur.*;
import java.util.*;

/**
 * <b>Classe abstraite representante un modele de taquin ecoutable</b>
 * 
 * <p>
 * Un taquin ecoutable ecoutable permet de:
 * </p>
 * <ul>
 * <li>Gerer les ecouteurs du taquin</li>
 * <li>notifier un changement dans le modele</li>
 * </ul>
 * 
 * @version 1.0
 * 
 */
public abstract class ModeleAbstraitTaquin implements TaquinEcoutable {

    /** La liste des ecouteurs du modele */
    private List<EcouteurTaquin> ecouteurs;

    protected ModeleAbstraitTaquin() {
        ecouteurs = new LinkedList<>();
    }

    @Override
    public void ajoutEcouteur(EcouteurTaquin ecouteur) {
        ecouteurs.add(ecouteur);
    }

    @Override
    public void retraitEcouteur(EcouteurTaquin ecouteur) {
        ecouteurs.remove(ecouteur);
    }

    @Override
    public void retraitEcouteurs() {
        ecouteurs.clear();
    }

    public abstract boolean deplace(Case caseAdeplacer);

    public abstract void melanger(int nbrPermutations);

    /**
     * Notifie un changement du modele à tous les ecouteurs
     * 
     */
    protected void fireChange() {
        for (EcouteurTaquin ecouteur : ecouteurs) {
            ecouteur.miseAjour(this);
        }
    }

}
