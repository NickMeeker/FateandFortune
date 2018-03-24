package com.fateandfortune.game.States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.net.HttpStatus;
import com.fateandfortune.game.FateAndFortune;
import com.fateandfortune.game.GameButton;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import static com.fateandfortune.game.FateAndFortune.ENDPOINT;

/**
 * Created by nick on 3/24/18.
 */

public class LoadingState extends State {
    private Texture background;
    private TextureRegion mainBackground;
    private GameButton loaded;

    public LoadingState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FateAndFortune.WIDTH/2, FateAndFortune.HEIGHT/2);
        background = new Texture(FateAndFortune.LOGIN_BG_PATH);
        loaded = new GameButton(cam.position.x - 189 / 2, cam.position.y, 189/2, 267/2, "enter.jpg");

        // w/h is dimensions of background asset
        mainBackground = new TextureRegion(background, 0, 0, 500, 500);


    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touchPosition = super.getInputRegion();
            if(isTouched(touchPosition, loaded)){
                gsm.set(new RegistrationState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(mainBackground, 0, 0, FateAndFortune.WIDTH/2, FateAndFortune.HEIGHT/2);
        sb.draw(loaded.getImage(), loaded.getX(), loaded.getY(), loaded.getWidth(), loaded.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
