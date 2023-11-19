package modele;

import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

/**
 * Classe representante une case d'une grille.
 * 
 * 
 * @version 1.0
 */

@Getter
@Setter
@EqualsAndHashCode
public class Case implements Comparable<Case> {

    /**
     * La valeur contenue dans la case
     * -- Getter --
     * Retourne la valeur contenue dans la case
     * 
     * @return la valeur contenue dans la case
     * 
     * -- Setter --
     * Change la valeur contenue dans la case
     * 
     * @param valeur la nouvelle valeur de la case
     */
    private int valeur;

    /**
     * Construit une nouvelle instance d'une case avec une valeur
     * 
     * @param <Int> valeur la valeur que possedera la case
     */
    public Case(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Construit une nouvelle instance par defaut d'une case
     */
    public Case() {
        this(0);
    }

    /**
     * Permuter les valeurs de 2 cases
     */
    public static void permuter(Case case1, Case case2) {
        int valeurCase2 = case2.valeur;
        case2.valeur = case1.valeur;
        case1.valeur = valeurCase2;
    }

    /**
     * Initialise la liste des cases de l'etat
     * 
     * @param <Int> nbrElements le nombre d'element de la liste
     */
    public static List<Case> initCases(int nbrElements) {
        List<Case> liste = new ArrayList<>();
        for (int i = 0; i < nbrElements; i++) {
            liste.add(new Case(i));
        }
        liste.sort(null);
        return liste;
    }

    @Override
    public int compareTo(Case o) {
        if (o == null || valeur == 0) {
            return 1;
        }

        if (o.valeur == 0) {
            return -1;
        }

        return this.valeur - o.valeur;
    }

    @Override
    public String toString() {
        return String.format("[ %2d ]", valeur);
    }

}
