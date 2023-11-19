package application;

import modele.*;
import vue.Taquin;
import java.util.*;
import controleur.*;

public class Main {

    public static void main(String[] args) {

        int longueur;

        int largeur;

        if (args.length < 2) {

            longueur = 3;

            largeur = 3;

        } else {

            longueur = Integer.parseInt(args[0]);

            largeur = Integer.parseInt(args[1]);

        }

        TaquinModel etat = new TaquinModel(longueur, largeur);

        TaquinControleur control = new TaquinControleur(etat);

        Taquin taquin = new Taquin(control, "Images/index.jpg");
        etat.ajoutEcouteur(taquin);
    }
}
