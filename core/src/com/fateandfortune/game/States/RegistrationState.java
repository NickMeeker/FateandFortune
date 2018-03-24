package com.fateandfortune.game.States;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.fateandfortune.game.FateAndFortune;
import com.fateandfortune.game.GameButton;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created by nick on 3/24/18.
 */

public class RegistrationState extends State {
    private Texture background;
    private TextureRegion mainBackground;
    private Stage stage;
    private TextField usernameField;
    private TextField passwordField;
    private TextField confirmPasswordField;
    private String username;
    private String password;
    private GameButton registerButton;
    private GameButton backButton;

    protected RegistrationState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FateAndFortune.WIDTH/2, FateAndFortune.HEIGHT/2);
        background = new Texture(FateAndFortune.LOGIN_BG_PATH);
        registerButton = new GameButton(cam.position.x - 100 / 2, 300, 200, 100, "registerButton.png");

        backButton = new GameButton(10, 50, 100, 100, "leftArrow.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        usernameField = new TextField("Username", skin);
        usernameField.setPosition(cam.position.x/2, 400);
        usernameField.setSize(300, 40);
        passwordField = new TextField("Password", skin);
        passwordField.setPosition(cam.position.x/2, 350);
        passwordField.setSize(300, 40);
        confirmPasswordField = new TextField("Confirm Password", skin);
        confirmPasswordField.setPosition(cam.position.x/2, 300);
        confirmPasswordField.setSize(300, 40);
        stage.addActor(usernameField);
        stage.addActor(passwordField);
        stage.addActor(confirmPasswordField);


    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touchPosition = super.getInputRegion();
            if(isTouched(touchPosition, registerButton)){
                username = usernameField.getText();
                if(passwordField.getText().equals(confirmPasswordField.getText())){
                    password = passwordField.getText();
                    HttpResponse<JsonNode> resp;
                    try {
                        resp = Unirest.post("http://fateandfortune.me:3000/api/users")
                                .field("username", username)
                                .field("password", password)
                                .asJson();
                    } catch(UnirestException e){
                        e.printStackTrace();
                        System.out.println(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR));
                        return;
                    }
                    if(resp.getStatus() == 201){
                        System.out.println("Success");
                    }else{
                        System.out.println("Bad");
                        System.out.println(resp.getStatus() + " " + resp.getBody());
                    }
                }
            }else if(isTouched(touchPosition, backButton)){
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
        sb.begin();
        sb.draw(background, 0, 0, FateAndFortune.WIDTH/2, FateAndFortune.HEIGHT/2);
        sb.draw(registerButton.getImage(), registerButton.getX(), registerButton.getY(), registerButton.getWidth(), registerButton.getHeight());
        sb.draw(backButton.getImage(), backButton.getX(), backButton.getY(), backButton.getWidth(), backButton.getY());
        sb.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
