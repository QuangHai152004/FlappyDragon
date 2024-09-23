package model;

import view.Objects;
import view.QueueList;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Columns extends Objects {
    private QueueList<Column> columns;
    private BufferedImage col_image,col_image2;
    public static int size=6;

    private int top=-390;
    private int bottom=240;
    public Columns() {

        try {
            col_image = ImageIO.read(new File("image/chimney.png"));
            col_image2 = ImageIO.read(new File("image/chimney2.png"));
        } catch (IOException e) {}

        columns=new QueueList<Column>();
        Column cl;

        for (int i = 0; i < size/2; i++) {
            int deltaY=getRandom();
            cl=new Column(830+i*300,bottom+deltaY,74,400);
            columns.push(cl);
            cl=new Column(830+i*300,top+deltaY,74,400);
            columns.push(cl);
        }
    }

    public int getRandom(){
        Random random=new Random();
        int a=random.nextInt(10);
        return a*40;
    }
    public Column getColumn(int i) {
        return columns.get(i);
    }

    public void update(){
        for (int i = 0; i < size; i++) {
            columns.get(i).update();

        }
        if (columns.get(0).getPosX()<-74){
            int deltaY=getRandom();
            Column column;
            column=columns.pop();
            column.setPosX(columns.get(4).getPosX()+300);
            column.setPosY(bottom+deltaY);
            column.setBehinddragon(false);
            columns.push(column);

            column=columns.pop();
            column.setPosX(columns.get(4).getPosX());
            column.setPosY(top+deltaY);
            column.setBehinddragon(false);
            columns.push(column);
        }

    }
    public void Resetcolumns(){
        columns=new QueueList<Column>();
        Column cl;

        for (int i = 0; i < size/2; i++) {
            int deltaY=getRandom();
            cl=new Column(830+i*300,bottom+deltaY,74,400);
            columns.push(cl);
            cl=new Column(830+i*300,top+deltaY,74,400);
            columns.push(cl);
        }
    }
    public void paint(Graphics2D g2){
        for (int i = 0; i < 6; i++) {
            if (i%2==0)
            g2.drawImage(col_image ,(int) columns.get(i).getPosX(),(int) columns.get(i).getPosY(),null);
            else g2.drawImage(col_image2 ,(int) columns.get(i).getPosX(),(int) columns.get(i).getPosY(),null);
        }
    }
}
