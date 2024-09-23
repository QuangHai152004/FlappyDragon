
package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Animation {
    
    private AFrameOnImage frames;
    public void AddFrame(AFrameOnImage sprite){
        frames = sprite;
    }
    
    public void PaintAnims(int x, int y, BufferedImage image, Graphics2D g2){
        frames.Paint(x, y, image, g2);
    }
}
