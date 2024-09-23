package view;

import model.Entity;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public abstract class GameScreen extends JFrame implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener {

    public int x_mouse ;
    public static int stt=0;
    public int y_mouse;
    public int CUSTOM_WIDTH  = 500;
    public int CUSTOM_HEIGHT = 500;

    private GameThread G_Thread;

    public Animation iconMap,iconDragon,animationTap,animationHold,animationFollow;
    public Entity maph,dragonh,howToPlay;
    public BufferedImage dragonImage,mapImage,tapImage,holdImage,followImage;


    public GameScreen(int w, int h){
        Toolkit toolkit =this.getToolkit();
        Dimension dimension=toolkit.getScreenSize();

        this.setBounds((dimension.width-w)/2,(dimension.height-h)/2,w,h);
        this.CUSTOM_WIDTH = w;
        this.CUSTOM_HEIGHT = h;
        InitThread();
        InitScreen();
    }
    public void menu(){
        try {
            dragonImage = ImageIO.read(new File("image/dragonchoose.png"));
            mapImage = ImageIO.read(new File("image/setting.png"));
            tapImage=ImageIO.read(new File("image/tap.png"));
            followImage=ImageIO.read(new File("image/follow.png"));
            holdImage=ImageIO.read(new File("image/hold.png"));

        } catch (IOException e) {}
        iconDragon=new Animation();
        iconMap=new Animation();
        animationTap=new Animation();
        animationHold=new Animation();
        animationFollow=new Animation();

        AFrameOnImage frameDragon,frameMap,frameTap,frameHold,frameFollow;

        frameMap = new AFrameOnImage(0, 0, 54, 54);
        iconMap.AddFrame(frameMap);
        maph=new Entity(250,500,54,54);

        frameDragon = new AFrameOnImage(0, 0, 50, 55);
        iconDragon.AddFrame(frameDragon);
        dragonh=new Entity(500,500,50,55);

        frameTap = new AFrameOnImage(0,0,40,40);
        animationTap.AddFrame(frameTap);
        howToPlay=new Entity(385,315,40,40);

        frameHold = new AFrameOnImage(0,0,40,40);
        animationHold.AddFrame(frameHold);

        frameFollow = new AFrameOnImage(0,0,40,40);
        animationFollow.AddFrame(frameFollow);

    }
    private void InitScreen(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setSize(CUSTOM_WIDTH, CUSTOM_HEIGHT);
        this.setTitle("Flappy Dragon");
        this.setResizable(false);
        setVisible(true);

    }

    public void BeginGame(){
        G_Thread.StartThread();
    }

    private void InitThread(){
        G_Thread = new GameThread(this);
        add(G_Thread);
    }



    public abstract void GAME_UPDATE();
    public abstract void GAME_PAINT(Graphics2D g2);


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        MOUSE_ACTION(e, MouseEvent.MOUSE_PRESSED);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MOUSE_ACTION(e, MouseEvent.MOUSE_RELEASED);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        x_mouse = e.getX();
        y_mouse = e.getY();

    }
    public abstract void MOUSE_ACTION(MouseEvent e, int Event);
}
