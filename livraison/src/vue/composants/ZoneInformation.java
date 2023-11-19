package vue.composants;

import java.awt.LayoutManager;
import java.awt.event.*;
import java.nio.Buffer;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import controleur.TaquinControleur;

/**
 * Classe représentant la zone d'inforamtion
 */
public class ZoneInformation extends JPanel {
    
    /* Attributs de la classe */

    private JLabel zoneNbCoups;
    private JLabel zoneCoups;
    private int nbCoups;
    private TaquinControleur controleur;

    /**
     * Constructeur de la classe
     * @param controleur le controleur du taquin
     */

    public ZoneInformation(TaquinControleur controleur) {
        this.zoneNbCoups = new JLabel();
        this.zoneCoups = new JLabel();
        this.nbCoups = 0;
        this.controleur = controleur;
    }

    /**
     * Initialise le composant de la zone d'inforamtion
     */
    private void initialiserComposant() {
        this.zoneCoups.setHorizontalAlignment(SwingConstants.CENTER);
        this.zoneCoups.setText("Nombre de coup");
        this.zoneNbCoups.setHorizontalAlignment(SwingConstants.CENTER);
        Border bordure = BorderFactory.createLoweredSoftBevelBorder();
        this.zoneNbCoups.setBorder(bordure);
        this.zoneNbCoups.setText(""+nbCoups);
    }
    
    /**
     * Construit et parametre le composant de la zone d'inforamtion'
     */
    public void build() {
        initialiserComposant();
        Border bordure = BorderFactory.createRaisedBevelBorder();

        this.setBorder(bordure);
        this.setLayout(new GridLayout(2, 1));
        this.add(zoneCoups);
        this.add(zoneNbCoups);

    }
    

    /**
     * Construit le contexte graphique passer en paramètre
     * @param g le contexte graphique 
    */

    public void paint(Graphics g) {
        super.paint(g);
        this.zoneNbCoups.setText("" + controleur.getScore());
    }
}
