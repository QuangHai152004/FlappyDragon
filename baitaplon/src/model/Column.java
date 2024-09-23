package model;

import view.Objects;

import java.awt.*;

public class Column extends Objects {
    private Rectangle rec;
    private Boolean isBehinddragon=false;
    public Column(float x, float y, float w, float h) {
        super(x, y, w, h);
        rec=new Rectangle((int) x, (int) y, (int) w/2, (int) h-6);
    }
    public void update(){
        setPosX(getPosX()-2.4f);
        this.rec.setLocation((int) (this.getPosX()+getW()/4),(int) this.getPosY()+3);
    }
    public Rectangle getRec(){
        return rec;
    }

    public Boolean getBehinddragon() {
        return isBehinddragon;
    }

    public void setBehinddragon(Boolean behinddragon) {
        isBehinddragon = behinddragon;
    }
}
