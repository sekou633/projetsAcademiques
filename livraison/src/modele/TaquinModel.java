package modele;

import java.util.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe representante un etat de jeu
 * 
 * @author Manne Emile KITSOUKOU
 * @version 1.0
 */

@EqualsAndHashCode(of = { "cases", "positionVide", "longueur", "largeur" }, callSuper = false)
public class TaquinModel extends ModeleAbstraitTaquin {

    /**
     * La liste des cases de l'etat
     * -- Getter --
     * Retourne la liste des cases du taquin
     * 
     * @return une liste de cases
     * 
     *         -- Setter --
     *         Change la valeur de la liste des cases du taquin
     * 
     * @param cases la nouvelle liste des cases
     */
    @Getter
    private List<Case> cases;

    /**
     * Indice de la position de la case vide
     * -- Getter --
     * Retourne l'indice de case vide
     * 
     * @return un entier indiquant l'indice de la case vide
     * 
     * -- Setter --
     * Change la valeur de l'indice de la case vide
     * 
     * @param positionVide le nouvel indice de la case vide
     */
    @Getter
    @Setter
    private int positionVide;

    /**
     * Longueur du plateau
     * -- Getter --
     * Retourne la longueur du plateau
     * 
     * @return un entier indiquant la longueur du plateau
     */
    @Getter
    private int longueur;

    /**
     * Largeur du plateau
     * -- Getter --
     * Retourne la largeur du plateau
     * 
     * @return un entier indiquant la largeur du plateau
     */
    @Getter
    private int largeur;

    /**
     * Construit une nouvelle instance d'un etat
     * 
     * @param <List<Case>> liste des cases de l'etat
     * @param <Int>        position la position de la case vide
     * @param <Int>        longueur la longueur du plateau
     * @param <Int>        largeur la largeur du plateau
     * @param <Int>        score la score de la partie
     */
    public TaquinModel(List<Case> cases, int position, int longueur, int largeur) {
        super();
        this.cases = cases;
        this.positionVide = position;
        this.longueur = longueur;
        this.largeur = largeur;
    }

    /**
     * Construit une nouvelle instance d'un etat
     * 
     * @param <Int> longueur la longueur du plateau
     * @param <Int> largeur la largeur du plateau
     */
    public TaquinModel(int longueur, int largeur) {
        this(Case.initCases(longueur * largeur),
                longueur * largeur - 1,
                longueur,
                largeur);
    }

    /**
     * Construit une instance par defaut de l'TaquinModel
     */
    public TaquinModel() {
        this(3, 3);
    }

    /**
     * Retourne la taille du taquin
     * 
     * @return
     */
    public int getTaille() {
        return longueur * largeur;
    }

    /**
     * Retourne la liste des indexes de case autour d'une certaine position
     * 
     * @param <Int> position la position autour de laquelle on cherche les cases
     */
    public List<Integer> indexesCasesAutour(int position) {

        List<Integer> indexes = new ArrayList<>();
        int x = position / largeur;
        int y = position % largeur;
        if (x > 0) {
            indexes.add(position - largeur);
        }
        if (x >= 0 && x < (longueur - 1)) {
            indexes.add(position + largeur);
        }
        if (y > 0) {
            indexes.add(position - 1);
        }
        if (y >= 0 && y < (largeur - 1)) {
            indexes.add(position + 1);
        }
        return indexes;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < cases.size(); i++) {
            if (i % largeur == 0) {
                res += "\n";
            }
            res += cases.get(i).toString();
        }
        return res;
    }

    /**
     * Retourne un boolean qui indique si une case a bien été déplacé
     * 
     * @param <Int> position de la case à déplacer
     */
    public boolean deplace(int position) {

        if (position >= 0 && indexesCasesAutour(positionVide).contains(position)) {
            Collections.swap(cases, positionVide, position);
            setPositionVide(position);
            fireChange();
            return true;
        }
        return false;
    }

    /**
     * Retourne un boolean qui indique si une case a bien été déplace
     * 
     * @param <Int> case
     */
    public boolean deplace(Case caseAdeplacer) {

        return deplace(cases.indexOf(caseAdeplacer));
    }

    /**
     * Retourne un boolean indiquant si le jeu est fini
     * 
     * @return un boolean
     */
    public boolean estTermine() {
        boolean res = true;
        for (int i = 1; i < cases.size() && res; i++) {
            if (cases.get(i - 1).compareTo(cases.get(i)) > 0) {
                // 25072001
                res = false;
            }
        }
        return res;
    }

    /**
     * Melanger le jeu
     */
    public void melanger(int nbr) {
        List<Integer> indices = null;
        for (int i = 0; i < nbr; i++) {
            indices = indexesCasesAutour(positionVide);
            Collections.shuffle(indices);
            // Case.permuter(cases.get(indices.get(0)), cases.get(positionVide));
            Collections.swap(cases, indices.get(0), positionVide);
            setPositionVide(indices.get(0));
            fireChange();
        }
    }

}