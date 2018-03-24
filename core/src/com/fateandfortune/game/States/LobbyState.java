package com.fateandfortune.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.fateandfortune.game.FateAndFortune;
import com.fateandfortune.game.GameButton;

/**
 * Created by nick on 3/24/18.
 */

public class LobbyState extends State{
    private Texture background;
    private TextureRegion mainBackground;
    private GameButton glass;
    private GameButton plus;
    private GameButton heart;
    private GameButton house;
    private GameButton bar;
    private Texture barRed;
    private Stage stage;
    private Table table;

    public LobbyState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FateAndFortune.WIDTH/2, FateAndFortune.HEIGHT/2);
        background = new Texture("lobbyBackground.png");
        Image bgImage = new Image();
        bgImage.setDrawable(new TextureRegionDrawable(new TextureRegion(background)));

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        bar = new GameButton(0, 0, 240, 29, "barBlue.png");
        glass = new GameButton(10, -4, 40, 40, "glass.png");
        plus = new GameButton(70, -6, 40, 40, "plus.png");
        heart = new GameButton(130, -15, 50, 50, "heart.png");
        house  = new GameButton(190, -8, 40, 40, "house.png");

        barRed = new Texture("barRed.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Table container = new Table();
        table = new Table();
        ScrollPane pane = new ScrollPane(table, skin);
        pane.layout();
        table.setBackground(bgImage.getDrawable());
        container.add(pane).width(FateAndFortune.WIDTH).height(700);
        container.row();
        container.setBounds(0, 15, FateAndFortune.WIDTH, 700);
        stage.addActor(container);

         //this is where the games will actually go
        for(int i = 0; i < 50; i++){
            TextButton tmp = new TextButton("", skin);
            Image matchBar = new Image(barRed);
            tmp.setText(i + "");
            table.add(matchBar).width(454).height(100).padTop(10).padBottom(5);
            table.row();
        }
        table.add();


    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touchPosition = super.getInputRegion();
            if(isTouched(touchPosition, house)){
                gsm.set(new LoginState(gsm));
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
        stage.act();
        stage.draw();
        sb.begin();
        //sb.draw(background, 0, 28, 240, 372);
        sb.draw(bar.getImage(), bar.getX(), bar.getY(), bar.getWidth(), bar.getHeight());
        sb.draw(glass.getImage(), glass.getX(), glass.getY(), glass.getWidth(), glass.getHeight());
        sb.draw(plus.getImage(), plus.getX(), plus.getY(), plus.getWidth(), plus.getHeight());
        sb.draw(heart.getImage(), heart.getX(), heart.getY(), heart.getWidth(), heart.getHeight());
        sb.draw(house.getImage(), house.getX(), house.getY(), house.getWidth(), house.getHeight());
        sb.end();

    }

    @Override
    public void dispose() {

    }


}
