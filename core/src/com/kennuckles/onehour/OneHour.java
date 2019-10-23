package com.kennuckles.onehour;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class OneHour extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera cam;
	Street street1;
	Car player;

	Array<Street> streets;

	int score = 1000;
	int speed = 7;
	float deltaTime = 0;
	int drunkenness = 0;
	boolean gameOver = false;
	boolean flip = false;
	float startTime;
	Sound sound, sound2, sound3;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 800, 480);
		startTime = startTime = System.currentTimeMillis();
		sound = Gdx.audio.newSound(Gdx.files.internal("jojo.wav"));
		sound2 = Gdx.audio.newSound(Gdx.files.internal("car.wav"));
		sound3 = Gdx.audio.newSound(Gdx.files.internal("lose.wav"));
		
		//create street
		streets = new Array<Street>();
		for (int i = 0; i < 50; i++)
		{
			if (i == 0)
				streets.add(new Street());
			else
			{
				if ( i % 10 == 0 || i % 5 == 0)
					streets.add(new Street(i*10, true));
				else
					streets.add(new Street(i*10, false));
			}	
		}

		//create player
		player = new Car();
		player.spr.rotate90(true);
		player.spr.setScale(0.3f, 1.0f);
		
	}

	@Override
	public void render () {
		
		if (!gameOver)
		{
		Gdx.gl.glClearColor(0, 0.5f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		Gdx.graphics.setTitle("Game Jam     Score: " + score + "     Alcohol Level: " + drunkenness);
		
		//Update Drunkenness if needed
		deltaTime  += (System.currentTimeMillis() - startTime) / 1000;

		System.out.println(deltaTime);
		if (((int)deltaTime) % 15 == 0)
		{
			drunkenness++;
		}

		//input
		if (Gdx.input.isKeyPressed((Keys.LEFT)) || Gdx.input.isKeyPressed((Keys.A)))
		{
			if (player.cornerTL.x < -177)
			{
				player.setX(5);
			}
			else
			{
				player.setX(-5);
			}

		}
		if (Gdx.input.isKeyPressed((Keys.RIGHT)) || Gdx.input.isKeyPressed((Keys.D)))
		{
			if (player.cornerTR.x > 773)
			{
				player.setX(-5);
			}
			else
			{
				player.setX(5);
			}
		}


		//bounds check & update streets
		//oof magic numbers >.<
		if (player.cornerTR.x > streets.get(0).boundR || player.cornerTL.x < streets.get(0).boundL)
			{
				for (Street str: streets)
				{
				str.rect.setY(str.rect.getY() - (speed/8));
				if (str.rect.getY() < 0)
					str.rect.setY(520); 
				}
				score -= 10;
			}
		else
			{
				for (Street str: streets)
				{
				str.rect.setY(str.rect.getY() - speed);
				if (!flip)
				{
					if (str.boundL > 0)
						//str.updateBoundX(drunkenness, flip);
					//else
					flip = true;
				}
				else
				{
					if (str.boundR < 800)
						//str.updateBoundX(drunkenness, flip);
					//else
					flip = false;
				}

				if (str.rect.getY() < 0)
					str.rect.setY(520); 
				}
			}


		
		//draw everything here
		batch.begin();
		//player.spr.setRotation(45/360f);
		for (Street str : streets)
		{
			batch.draw(str.texture, str.rect.x, str.rect.y);
		}
		player.spr.draw(batch);
		batch.end();
		

		if (score <= 0)
		{
			gameOver = true;
		}
		sound2.play();
		}
		else
		{
			Gdx.graphics.setTitle("Game Jam - GAME OVER. Press Space to retry...");
			sound3.play();
			if (Gdx.input.isKeyPressed(Keys.SPACE))
			{
				player.resetCar();
				score = 1000;
				Gdx.graphics.setTitle("Game Jam     Score: " + score);
				gameOver = false;
				sound.play();
			}
		}
		
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}


}