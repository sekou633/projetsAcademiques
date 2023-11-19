package vue.actions;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.*;

/**
 * Classe représentant l'action de fermeture de la fenetre 
 */

public class FermetureFenetre extends JFrame implements ActionListener{

    /* Attributs de la classe */
    private JFrame composant;

    /**
     * Constructeur de la classe 
     * @param composant le composant du taquin
     */
    public FermetureFenetre(JFrame composant) {
        this.composant = composant;
    }

    public void arreter() {
        
        int result =  JOptionPane.showConfirmDialog(this, "Confirmez la fermeture", "Attention Fermeture", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
           System.out.println("Fin de l'excecution, on ferme tout. Bonne journée.");
           composant.dispose();
           dispose();
        }
        
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        arreter();
    }
    
}
