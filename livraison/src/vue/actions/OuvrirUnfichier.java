package vue.actions;

import java.awt.event.*;
import java.io.File;

import javax.swing.JFileChooser;

import vue.Taquin;

/**
 * Classe représentant l'action d'ouverture d'un fichier
 */
public class OuvrirUnfichier implements KeyListener, ActionListener{

    /* Attributs de la classe */

    private final JFileChooser fc = new JFileChooser();
    private String filtre;
    private Taquin taquin;

    /**
     * Constructeurs de la classe
     * @param filtre le filtre du taquin
     * @param taquin le taquin lui même
     */
    public OuvrirUnfichier(String filtre, Taquin taquin) {
        this.filtre = filtre;
        this.taquin = taquin;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        int res = fc.showOpenDialog(fc.getParent());

        if (res == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            this.taquin.setCheminImage(file.getAbsolutePath());
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // On ne fait rien 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // On ne fait rien  
    }



    @Override
    public void keyPressed(KeyEvent e) {
        // On ne fait rien
    }
    
}
