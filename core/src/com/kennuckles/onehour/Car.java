package com.kennuckles.onehour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Car {

    Texture texture;
    Rectangle rect;
    TextureRegion region;
    Sprite spr;

    Vector2 cornerTL, cornerTR, cornerBL, cornerBR;

    public Car() {
        texture = new Texture(Gdx.files.internal("car-s.png"));     
        spr = new Sprite(texture);
        rect = new Rectangle();
        rect.x = 200;
        rect.y = 240;
        rect.height = spr.getHeight();
        rect.width = spr.getWidth();
        spr.setOriginCenter();
        spr.setRotation(90/360f);
        spr.setX((800/2-64/2));
        spr.setY((64/2));
        cornerTL = new Vector2(spr.getX()-(spr.getWidth()/2), spr.getY()+(spr.getHeight()/2));
        cornerTR = new Vector2(spr.getX()+(spr.getWidth()/2), spr.getY()+(spr.getHeight()/2));
        cornerBL = new Vector2(spr.getX()-(spr.getWidth()/2), spr.getY()-(spr.getHeight()/2));
        cornerBR = new Vector2(spr.getX()+(spr.getWidth()/2), spr.getY()-(spr.getHeight()/2));
    }

    public void setX(int x){
        spr.setX(spr.getX() + x);
        cornerTL.add(x, 0);
        cornerBL.add(x, 0);
        cornerTR.add(x, 0);
        cornerBR.add(x, 0);
        //System.out.println(cornerTL + "\t" + cornerTR+ "\n");
    }

    public void resetCar()
    {
        spr.setX((800/2-64/2));
        spr.setY((64/2));
        cornerTL = new Vector2(spr.getX()-(spr.getWidth()/2), spr.getY()+(spr.getHeight()/2));
        cornerTR = new Vector2(spr.getX()+(spr.getWidth()/2), spr.getY()+(spr.getHeight()/2));
        cornerBL = new Vector2(spr.getX()-(spr.getWidth()/2), spr.getY()-(spr.getHeight()/2));
        cornerBR = new Vector2(spr.getX()+(spr.getWidth()/2), spr.getY()-(spr.getHeight()/2));
    }

}