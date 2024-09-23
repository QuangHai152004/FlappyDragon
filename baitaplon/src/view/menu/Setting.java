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

import static view.Objects.*;

public class Setting extends GameScreen {
    private Boolean h=false;

    private BufferedImage arrowBackImage,soundImage,musicImage;
    private Entity arrowBack,sound,music;
    private Animation animationArrowBack,animationSound,animationMusic;
    private AFrameOnImage frameArrowBack,frameSound,frameMusic;
    private static long k1=0,k2=0;
    public static Boolean k3=true;
    public Setting(){

        super(750, 600);
        try {

            arrowBackImage=ImageIO.read(new File("image/arrow.png"));
            soundImage = ImageIO.read(new File("image/sound.png"));
            musicImage = ImageIO.read(new File("image/music.png"));

        } catch (IOException e) {}


        animationArrowBack=new Animation();
        frameArrowBack = new AFrameOnImage(0, 0, 100, 83);
        animationArrowBack.AddFrame(frameArrowBack);
        arrowBack=new Entity(5,5,100,83);


        animationSound=new Animation();
        frameSound = new AFrameOnImage(0, 0, 60, 60);
        animationSound.AddFrame(frameSound);
        sound=new Entity(350,300,120,60);

        animationMusic=new Animation();
        frameMusic = new AFrameOnImage(0, 0, 60, 60);
        animationMusic.AddFrame(frameMusic);
        music=new Entity(340,200,120,60);

        BeginGame();

    }


    @Override
    public void GAME_UPDATE() {
        if (allowSound){

            AFrameOnImage  f;
            f=new AFrameOnImage(0,0,60,60);
            animationMusic.AddFrame(f);

        }else {

            AFrameOnImage  f;
            f=new AFrameOnImage(60,0,60,60);
            animationMusic.AddFrame(f);
        }
        if (allow){
            AFrameOnImage  f;
            f=new AFrameOnImage(0,0,60,60);
            animationSound.AddFrame(f);
        }else {

            AFrameOnImage  f;
            f=new AFrameOnImage(60,0,60,60);
            animationSound.AddFrame(f);
        }
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        animationArrowBack.PaintAnims((int) arrowBack.getPosX(),(int) arrowBack.getPosY(),arrowBackImage,g2);
        animationSound.PaintAnims((int) sound.getPosX(),(int) sound.getPosY(),soundImage,g2);
        animationMusic.PaintAnims((int) music.getPosX(),(int) music.getPosY(),musicImage,g2);
    }

    @Override
    public void MOUSE_ACTION(MouseEvent e, int Event) {
        if (h) {
            int x = e.getX(), y = e.getY();
            if (x <= (CUSTOM_WIDTH / 7) && y < (CUSTOM_WIDTH / 6)) {
                if (stt == 0) {
                    new flappy_tap();
                } else if (stt == 1) {
                    new flappy_hold();
                } else {
                    new flappy_follow();
                }
                dispose();
            }
            if (Event == MouseEvent.MOUSE_RELEASED) {
                if (y >= (CUSTOM_HEIGHT * 4 / 10) && y < (CUSTOM_HEIGHT * 6 / 10) && x >= (CUSTOM_WIDTH * 7 / 15) && x < (CUSTOM_WIDTH * 9 / 15)) {
                    if (k1 % 2 == 0) {
                        AFrameOnImage f;
                        f = new AFrameOnImage(60, 0, 60, 60);
                        animationMusic.AddFrame(f);
                        allowSound = false;
                    } else {
                        AFrameOnImage f;
                        f = new AFrameOnImage(0, 0, 60, 60);
                        animationMusic.AddFrame(f);
                        allowSound = true;
                    }
                    k1++;
                }
                if (y >= (CUSTOM_HEIGHT * 6 / 10) && y < (CUSTOM_HEIGHT * 8 / 10) && x >= (CUSTOM_WIDTH * 7 / 15) && x < (CUSTOM_WIDTH * 9 / 15)) {
                    if (k2 % 2 == 0) {
                        AFrameOnImage f;
                        f = new AFrameOnImage(60, 0, 60, 60);
                        animationSound.AddFrame(f);
                        flappy_tap.h.game_sound.stop();
                        allow=false;

                    } else {
                        AFrameOnImage f;
                        f = new AFrameOnImage(0, 0, 60, 60);
                        animationSound.AddFrame(f);
                        flappy_tap.h.game_sound.playLoop();
                        allow=true;

                    }
                    k2++;
                }

            }
        }
        h = true;

    }

}



