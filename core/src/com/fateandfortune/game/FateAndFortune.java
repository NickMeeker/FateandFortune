package com.fateandfortune.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fateandfortune.game.States.GameStateManager;
import com.fateandfortune.game.States.LoadingState;

public class FateAndFortune extends ApplicationAdapter {
	// KEEP RELEVANT CONSTANTS HERE
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "FATE AND FORTUNE";
	public static final String ENDPOINT = "http://fateandfortune.me:3000/api/";
	public static final String LOGIN_BG_PATH = "loginBg.png";
	public enum CardType {
		MAGICIAN, JUDGEMENT, HERMIT, FOOLS, STAR, MOON, SUN, WORLD
	}

	private SpriteBatch batch;
	private GameStateManager gsm;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new LoadingState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}

