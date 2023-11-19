package vue.composants;

import javax.swing.*;
import javax.swing.border.Border;

import vue.Taquin;
import vue.actions.FermetureFenetre;
import vue.actions.MelangerPlateau;
import vue.actions.OuvrirUnfichier;

import java.awt.*;
import java.awt.event.*;

public class Menu{
    
    /**La barre de menu */
    private JMenuBar menu;
    private JFrame frame;
    public Menu(JFrame frame) {
        this.menu = new JMenuBar();
        this.frame = frame;
    }
    
    public void creerMenuBar(Taquin taquin) {

        //Menu deroulant fichier 
        JMenu menuPartie = new JMenu("Partie");

        //Item ouverture d'un fichier
        JMenuItem ouvrirFichier = new JMenuItem("Ouvrir un fichier");
        ouvrirFichier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        menuPartie.add(ouvrirFichier);
        ouvrirFichier.addActionListener(new OuvrirUnfichier("jpg", taquin));

        menuPartie.add(ouvrirFichier);

        //Item melanger
        JMenuItem melanger = new JMenuItem("Melanger");
        melanger.addActionListener(new MelangerPlateau(taquin.getPlateau()));

        menuPartie.add(melanger);

        //Item sauvegarder
        JMenuItem sauvegarder = new JMenuItem("sauvegarder");

        menuPartie.add(sauvegarder);

        JMenuItem fin = new JMenuItem("fin");

        fin.addActionListener(new FermetureFenetre(frame));

        menuPartie.add(fin);
        
        menu.add(menuPartie);

        JMenu menuSon = new JMenu("Son");
        Border bordure = BorderFactory.createRaisedBevelBorder();

        menu.setBorder(bordure);

        frame.setJMenuBar(menu);
        frame.validate();

    }
    
    public JMenuBar getMenu() {
        return menu;
    }
}
