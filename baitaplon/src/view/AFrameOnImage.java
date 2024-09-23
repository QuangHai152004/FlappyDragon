package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class AFrameOnImage {

    
    private int []DimsBounds = new int[4];
    
    public AFrameOnImage(int xOnImage, int yOnImage, int w, int h){
        DimsBounds[0] = xOnImage;
        DimsBounds[1] = yOnImage;
        DimsBounds[2] = w;
        DimsBounds[3] = h;
    }
    public void Paint(int x, int y, BufferedImage image, Graphics2D g2){
        BufferedImage imageDraw = image.getSubimage(DimsBounds[0], DimsBounds[1], DimsBounds[2], DimsBounds[3]);
        g2.drawImage(imageDraw, x, y, null);
    }
}
