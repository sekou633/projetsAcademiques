package vue.composants;

import vue.actions.*;
import observeur.EcouteurTaquin;
import vue.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import controleur.TaquinControleur;

import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import lombok.Getter;
import modele.Case;

/**
 * Classe représentant le composants du plateau à jouer
 */

public class PlateauJouable extends JPanel{

    /* Attributs de la classe */

    @Getter
    private TaquinControleur controleur;
    @Getter
    private Map<Object, Icon> associationObjetEtImage;
    private int space = 0;
    private String cheminImage;
    private JLabel [] casesPlateau;

    /**
     * Constructeur de la classe
     * @param controleur le controleur du taquin
     * @param cheminImage le chemin de l'image
     */
    public PlateauJouable(TaquinControleur controleur, String cheminImage) {
        super();
        this.controleur = controleur;
        this.cheminImage = cheminImage;
        this.casesPlateau = new JLabel[controleur.getModelTaille()];
        setSize(1000, 1000);
    }

    /**
     * Modifie le chemin de l'image
     * @param cheminImage le chemin de l'image
     */
    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
        initialiseAssociationObjetEtImage();
    }

    /**
     * Construit et paramètre les composants de la fenetre
     */

    public void build() {
        setLayout(new GridLayout(controleur.getModel().getLongueur(),
                controleur.getModel().getLargeur(), space, space));

        setPreferredSize(new Dimension(500, 500));

        setFocusable(true);
        requestFocus();
        initialiseAssociationObjetEtImage();
        initialiserEvent();
        Border bordure = BorderFactory.createLoweredSoftBevelBorder();
        this.setBorder(bordure);
    }
    
    /**
     * Initialise l'évènement
     */
    private void initialiserEvent() {
        addKeyListener(new DeplacementsDirectionnelles(controleur));
        addMouseListener(new CliqueSurImage(controleur, this));
        addKeyListener(new MelangerPlateau(this));
        addComponentListener(new RedimensionnerPlateau(this));

    }
    
    

    /**
     * Initialise l'association de l'objet et de l'image
    */

    public void initialiseAssociationObjetEtImage() {
        int width = this.getWidth();
        int height = this.getHeight();
        
        this.removeAll();
        File file = new File(cheminImage);
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlateauJouable.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedImage image = null;
        try {
            
            image = ImageIO.read(fis);
            BufferedImage img = new BufferedImage(width, height, image.getType());
            Graphics2D gr = img.createGraphics();
            gr.drawImage(image, 0, 0, width, height, null);
            gr.dispose();
            image = img;
        } catch (IOException ex) {
            Logger.getLogger(PlateauJouable.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<BufferedImage> liste = Image.decouperUneImage(image, controleur.getModel().getLongueur(),
                controleur.getModel().getLargeur());

        List<Object> objects = new LinkedList<>();
        objects.addAll(Case.initCases(controleur.getModelTaille()));
        associationObjetEtImage = new HashMap<>();
        associationObjetEtImage = Image.associationToImage(objects, liste);
        Border bordure = BorderFactory.createRaisedBevelBorder();
        for (int i = 0; i < objects.size(); i++) {
            casesPlateau[i] = new JLabel();
            casesPlateau[i].setBorder(bordure);
            casesPlateau[i].setIcon(associationObjetEtImage.get(controleur.getModelCases().get(i)));
            add(casesPlateau[i]);
        }
        this.validate();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Border bordure = BorderFactory.createRaisedBevelBorder();
        Border lineBorder = BorderFactory.createLineBorder(Color.GREEN);
        Border composite = BorderFactory.createCompoundBorder(lineBorder, bordure);
        
        Border lineBorder2 = BorderFactory.createLineBorder(Color.MAGENTA);
        Border composite2 = BorderFactory.createCompoundBorder(lineBorder2, bordure);
        for (int i = 0; i < associationObjetEtImage.size(); i++) {
            if (controleur.getModelCases().get(i).getValeur() == (i + 1)) {
                casesPlateau[i].setBorder(composite);
            }
            else if (controleur.getModel().indexesCasesAutour(controleur.getModelPositionVide()).contains(i)) {
                casesPlateau[i].setBorder(composite2);

            }
            else {
                casesPlateau[i].setBorder(bordure);
            }

            casesPlateau[i].setIcon(associationObjetEtImage.get(controleur.getModelCases().get(i)));
        }

    }

}
