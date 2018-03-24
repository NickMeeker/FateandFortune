package com.fateandfortune.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fateandfortune.game.FateAndFortune;

/**
 * Created by nick on 3/24/18.
 */

public class FriendsListState extends State {

    protected FriendsListState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FateAndFortune.WIDTH/2, FateAndFortune.HEIGHT/2);

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
