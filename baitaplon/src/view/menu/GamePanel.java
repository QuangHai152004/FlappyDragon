package view.menu;

import Controll.flappy_follow;
import Controll.flappy_hold;
import Controll.flappy_tap;
import model.Entity;
import view.AFrameOnImage;
import view.Animation;
import view.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends GameScreen {
    private Boolean h=false;

    private BufferedImage dragonHoldImage,dragonTapImage,dragonFollowImage,arrowBackImage,tapImage,followImage,holdImage;
    private Entity entityHold, entityTap, entityFollow, arrowBack,tap,follow,hold;
    private Animation animationDragonHold,animationDragonTap, animationDragonFollow, animationArrowBack,animationTap,animationFollow,animationHold;
    private AFrameOnImage frameDragonFollow,frameDragonHold,frameArrowBack,frameDragonTap,frameTap,frameFollow,frameHold;

    public GamePanel(){

        super(750, 600);
        try {
            dragonTapImage=ImageIO.read((new File("image/dragon.png")));
            dragonFollowImage=ImageIO.read(new File("image/dragon3.png"));
            dragonHoldImage=ImageIO.read(new File("image/dragon2.png"));
            arrowBackImage=ImageIO.read(new File("image/arrow.png"));
            tapImage=ImageIO.read(new File("image/tap.png"));
            followImage=ImageIO.read(new File("image/follow.png"));
            holdImage=ImageIO.read(new File("image/hold.png"));
        } catch (IOException e) {}

        animationDragonFollow=new Animation();
        animationDragonHold=new Animation();
        animationArrowBack=new Animation();
        animationDragonTap=new Animation();
        animationTap=new Animation();
        animationFollow=new Animation();
        animationHold=new Animation();

        frameDragonTap = new AFrameOnImage(0,0,111,99);
        animationDragonTap.AddFrame(frameDragonTap);
        entityTap =new Entity(320,247,108,60);

        frameDragonFollow = new AFrameOnImage(0, 0, 111, 99);
        animationDragonFollow.AddFrame(frameDragonFollow);
        entityFollow =new Entity(100,240,111,99);

        frameDragonHold = new AFrameOnImage(0, 0, 111, 52);
        animationDragonHold.AddFrame(frameDragonHold);
        entityHold =new Entity(539,255,111,52);

        frameArrowBack = new AFrameOnImage(0, 0, 100, 83);
        animationArrowBack.AddFrame(frameArrowBack);
        arrowBack=new Entity(5,5,100,83);

        frameTap=new AFrameOnImage(0,0,40,40);
        animationTap.AddFrame(frameTap);
        tap=new Entity(354,310,40,40);

        frameFollow=new AFrameOnImage(0,0,40,40);
        animationFollow.AddFrame(frameFollow);
        follow=new Entity(135,310,40,40);

        frameHold=new AFrameOnImage(0,0,40,40);
        animationHold.AddFrame(frameHold);
        hold=new Entity(575,310,40,40);

        BeginGame();

    }


    @Override
    public void GAME_UPDATE() {

    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {

        animationDragonTap.PaintAnims((int) entityTap.getPosX(), (int) entityTap.getPosY(), dragonTapImage, g2);
        animationDragonHold.PaintAnims((int) entityHold.getPosX(),(int) entityHold.getPosY(),dragonHoldImage,g2);
        animationDragonFollow.PaintAnims((int) entityFollow.getPosX(),(int) entityFollow.getPosY(),dragonFollowImage,g2);
        animationArrowBack.PaintAnims((int) arrowBack.getPosX(),(int) arrowBack.getPosY(),arrowBackImage,g2);

        animationTap.PaintAnims((int) tap.getPosX(), (int) tap.getPosY(), tapImage, g2);
        animationFollow.PaintAnims((int) follow.getPosX(), (int) follow.getPosY(), followImage, g2);
        animationHold.PaintAnims((int) hold.getPosX(), (int) hold.getPosY(), holdImage, g2);

    }

    @Override
    public void MOUSE_ACTION(MouseEvent e, int Event) {
        if (h) {
            int x = e.getX(), y = e.getY();
            if (x <= (CUSTOM_WIDTH / 7) && y < (CUSTOM_WIDTH / 6)) {
                dispose();
                if (stt == 0) {
                    new flappy_tap();
                } else if (stt == 1) {
                    new flappy_hold();
                } else {
                    new flappy_follow();
                }
            } else if (x <= (CUSTOM_WIDTH / 3) && y>(CUSTOM_HEIGHT*2/4) &&y<(CUSTOM_HEIGHT*3/4)) {

                dispose();
                new flappy_follow();

            } else if (x > (CUSTOM_WIDTH / 3) && x < (CUSTOM_WIDTH * 2) / 3 && y>(CUSTOM_HEIGHT*2/4) &&y<(CUSTOM_HEIGHT*3/4)) {

                dispose();
                new flappy_tap();

            } else if (x > (CUSTOM_WIDTH * 2) / 3 && y>(CUSTOM_HEIGHT*2/4) &&y<(CUSTOM_HEIGHT*3/4)) {

                dispose();
                new flappy_hold();

            }
        }
        h=true;
    }

}



