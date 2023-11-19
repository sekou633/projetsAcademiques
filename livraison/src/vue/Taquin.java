package vue;

import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.*;
import observeur.EcouteurTaquin;
import vue.actions.FermetureFenetre;
import controleur.*;
import vue.composants.*;
import vue.composants.Menu;
import java.awt.*;
import java.awt.event.*;

import lombok.Getter;

/**

 * Classe représentant la vue du taquin

 */
public class Taquin extends JFrame implements EcouteurTaquin,WindowListener{

    /* Attributs de la classe*/

    /**Le controleur sur lequel on a la vue*/

    @Getter

    TaquinControleur control;

    @Getter

    private PlateauJouable plateau;

    /**Le chemin vers l'image */

    private String cheminImage;
    private FermetureFenetre close;
    private ZoneInformation zoneInformation;

    private Menu menu;

    /**

     * Constructeur de la classe

     * @param control le controleur du taquin

     * @param le chemin de l'image

     */

    public Taquin(TaquinControleur control, String cheminImage){

        super("Taquin puzzle");

        this.control = control;

        this.cheminImage = cheminImage;

        this.plateau = new PlateauJouable(control, this.cheminImage);

        this.zoneInformation = new ZoneInformation(control);

        zoneInformation.build();

        plateau.build();

        add(plateau);

        add(zoneInformation, BorderLayout.NORTH);

        this.menu = new Menu(this);

        initialize();

        

    }

    /**

     * Initialise la fenetre

     */

    private void initialize() {

        setSize(950, 950);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        menu.creerMenuBar(this);

        Border bordure = BorderFactory.createLoweredSoftBevelBorder();

        this.getRootPane().setBorder(bordure);

    }

    

    /**

     * Modifie le chemin de l'image

     * @param cheminImage le chemin de l'image'

     */

    public void setCheminImage(String cheminImage) {

        this.cheminImage = cheminImage;

        this.plateau.setCheminImage(cheminImage);

    }

    /**

     * Initialise l'évènement

     */

    

    private void initialiseEvenement() {

    }

        

    @Override

    public void miseAjour(Object source) {

        System.out.println(control.getModel());

        repaint();
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
        this.close.arreter();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }


}
