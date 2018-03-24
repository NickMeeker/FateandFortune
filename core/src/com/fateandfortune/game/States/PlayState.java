package com.fateandfortune.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fateandfortune.game.FateAndFortune;

/**
 * Created by nick on 3/24/18.
 */

public class PlayState extends State {
    private Texture background;
    private TextureRegion mainBackground;
    protected PlayState(GameStateManager gsm) {
        super(gsm);
        gameInit();
        cam.setToOrtho(false, FateAndFortune.WIDTH/2, FateAndFortune.HEIGHT/2);
        background = new Texture("blueBackground.jpg");
        mainBackground = new TextureRegion(background, 0, 0, 500, 500);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(mainBackground, 0, 0, FateAndFortune.WIDTH, FateAndFortune.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {

    }

    public void gameInit(){
        // get all the shit from klayton
        // for i from 0 to player count init players
        // set turn player player.turnPlayer = true

    }
}
