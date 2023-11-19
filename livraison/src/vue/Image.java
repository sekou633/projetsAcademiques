package vue;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.*;

/**
 * Classe finale représentant l'image du taquin
 */
public final class Image{

    private Image() {
        // Empeche de creer une instance de la classe
    }

    public static BufferedImage copy(BufferedImage img) {
        ColorModel colorModel = img.getColorModel();
        boolean isAlpha = colorModel.isAlphaPremultiplied();
        WritableRaster raster = img.copyData(img.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(colorModel, raster, isAlpha, null);
    }

    public static List<BufferedImage> decouperUneImage(BufferedImage img, int nbrLignes, int nbrColonnes) {
        List<BufferedImage> result = new LinkedList<>();
        int width = img.getWidth() / nbrColonnes;
        int height = img.getHeight() / nbrLignes;

        for (int x = 0; x < nbrLignes; x++) {
            for (int y = 0; y < nbrColonnes; y++) {
                BufferedImage sousImage = new BufferedImage(width, height, img.getType());

                Graphics2D gr = sousImage.createGraphics();
                if (x == nbrLignes - 1 && y == nbrColonnes - 1) {
                    gr.setColor(Color.GREEN);
                    gr.drawRect(0, 0, width, height);
                    gr.setColor(Color.GRAY);
                    gr.fillRect(0, 0, width, height);
                }
                else {
                    gr.drawImage(img, 0, 0, width, height, width * y, height * x, width * y + width,
                            height * x + height, null);
                }
                result.add(sousImage);

                gr.dispose();
            }
            
        }

        return result;
    }

    public static Map<Object, Icon> associationToImage(List<Object> liste,
                                                                List<BufferedImage> decoupageImage) {
        Map<Object, Icon> result = new LinkedHashMap<>();
        if (liste != null && liste.size() == decoupageImage.size()) {
            int taille = liste.size();
            int i;
            for (i = 0; i < taille; i++) {
                Icon icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(decoupageImage.get(i).getSource()));
                result.put(liste.get(i), icon);
            }
        }
        return result;
        
    }



    
    
}
