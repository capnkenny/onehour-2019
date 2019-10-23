package com.kennuckles.onehour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Street {
    Texture texture;
    Rectangle rect;
    int boundR, boundL;

	public Street()
	{
        texture = new Texture(Gdx.files.internal("straight-s.png"));
        rect = new Rectangle();
        rect.x = 247;
        rect.y = 0;
        rect.width = 314;
        rect.height = 58;
        boundR = (int)(rect.x+ rect.width);
        boundL = (int)(rect.x - 174);
    }

    public Street(int y, boolean blank)
	{
        if (!blank)
            texture = new Texture(Gdx.files.internal("straight-s.png"));
        else
            texture = new Texture(Gdx.files.internal("straight-s-b.png"));
        rect = new Rectangle();
        rect.x = 247;
        rect.y = 0 + y;
        rect.width = 314;
        rect.height = 58;
        boundR = (int)(rect.x+ rect.width);
        boundL = (int) (rect.x - 174);
    }

    public void updateBoundX(int x, boolean flip)
    {

        if (flip)
        {
        boundL += x;
        boundR += x;
        rect.setX(rect.getX() + x);
        }
        else
        {
            boundL -= x;
        boundR -= x;
        rect.setX(rect.getX() - x);
        }
        
    }


}
