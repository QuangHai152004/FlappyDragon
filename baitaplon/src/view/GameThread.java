package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameThread extends JPanel implements Runnable{
    
    private GameScreen context;
    
    private Thread thread;

    public static int FPS = 72;
    
    private BufferedImage buffImage,backgroundImage;

    private int MasterWidth, MasterHeight;
    
    public GameThread(GameScreen context){

        try {
            // Đọc ảnh từ tệp
            backgroundImage = ImageIO.read(new File("image/2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.context = context;

        MasterWidth = context.CUSTOM_WIDTH;
        MasterHeight = context.CUSTOM_HEIGHT;

        this.thread = new Thread(this);
        
    }
    public void StartThread(){
        thread.start();
    }
    public void paint(Graphics g){
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 750, 600, this);
        }
//        g.setColor(Color.);
//        g.fillRect(0, 0, context.CUSTOM_WIDTH, context.CUSTOM_HEIGHT);
        Graphics2D g2 = (Graphics2D)g;
        if(buffImage!=null){
            g2.drawImage(buffImage, 0, 0, this);
        }
    }

    private void UpdateSize(){
        if(this.getWidth()<=0) return;
        
        context.CUSTOM_WIDTH = this.getWidth();
        context.CUSTOM_HEIGHT = this.getHeight();
    }

    
    @Override
    public void run() {
        
        long T = 1000/FPS;
        long TimeBuffer = T/2;
        
        long BeginTime = System.currentTimeMillis();
        long EndTime;
        long sleepTime;
        
        while(true){    
            
            UpdateSize();
            
            context.GAME_UPDATE();
            try{
                
                buffImage = new BufferedImage(MasterWidth, MasterHeight, BufferedImage.TYPE_INT_ARGB);
                if(buffImage == null) return;
                Graphics2D g2 = (Graphics2D) buffImage.getGraphics();
                
                if(g2!=null){
                    context.GAME_PAINT(g2);
                }
                    
            }catch(Exception myException){
                myException.printStackTrace();
            }
            
            repaint();
            
            EndTime = System.currentTimeMillis();
            sleepTime = T - (EndTime - BeginTime);
            if(sleepTime < 0) sleepTime = TimeBuffer;
            
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {}
            
            BeginTime = System.currentTimeMillis();
        }
    }
}
