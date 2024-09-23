package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ground {
    private BufferedImage grimage;
    private int x1,y1,x2,y2;
    public Ground(){
        try {
            grimage= ImageIO.read(new File("aset/ground.png"));
        } catch (IOException e) {

        }
        x1=0;
        y1=600;
        x2=x1+830;
        y2=600;
    }
    public void update(){
        x1-=2;
        x2-=2;

        if (x2<0)x1=x2+830;

        if (x1<0)x2=x1+830;

    }
    public void Paint(Graphics2D g2){
        g2.drawImage(grimage,x1,y1,null);
        g2.drawImage(grimage,x2,y2,null);

    }

    public int getYGround() {
        return y1;
    }
}
