package com.fateandfortune.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by nick on 3/24/18.
 */

public class LoginState extends State {
    private Stage stage;
    private TextField usernameField;
    private TextField passwordField;
    private Texture background;
    private TextureRegion mainBackground;
    private GameButton loginButton;
    private File ifp;
    private String username;
    private String password;
    private String authToken;
    private boolean toLobby = false;


    protected LoginState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FateAndFortune.WIDTH/2, FateAndFortune.HEIGHT/2);
        background = new Texture(FateAndFortune.LOGIN_BG_PATH);
        loginButton = new GameButton(cam.position.x - 100 / 2, 300, 100, 40, "loginButton.png");
        ifp = new File("username.txt");


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        usernameField = new TextField("", skin);
        usernameField.setPosition(cam.position.x/2, 400);
        usernameField.setSize(300, 40);
        passwordField = new TextField("Password", skin);
        passwordField.setPosition(cam.position.x/2, 350);
        passwordField.setSize(300, 40);



        if(ifp.exists() && !ifp.isDirectory() && ifp.length() != 0){
            try {
                Scanner scan = new Scanner(ifp);
                String id = scan.nextLine();
                String token = scan.nextLine();

                HttpResponse<JsonNode> loginResp;
                try{
                    loginResp = Unirest.get(FateAndFortune.ENDPOINT + "/users/{id}")
                            .header("Authorization", token)
                            .routeParam("id", id)
                            .asJson();

                    username = loginResp.getBody().getObject().getString("username");
                    if(loginResp.getStatus() == 200){
                        toLobby = true;
                    }else{
                        System.out.println("Bad key");
                        ifp.delete();
                    }
                } catch(UnirestException e){
                    e.printStackTrace();
                    return;
                }
                scan.close();
            } catch(FileNotFoundException e){
                System.out.println("file not found");
            }
        }

        stage.addActor(usernameField);
        stage.addActor(passwordField);

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touchPosition = super.getInputRegion();
            if(isTouched(touchPosition, loginButton)){
                username = usernameField.getText();
                password = passwordField.getText();
                loginAttempt(username, password);
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        if(toLobby)
            gsm.set(new LobbyState(gsm));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, FateAndFortune.WIDTH/2, FateAndFortune.HEIGHT/2);
        sb.draw(loginButton.getImage(), loginButton.getX(), loginButton.getY(), loginButton.getWidth(), loginButton.getHeight());
        sb.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {

    }

    private void loginAttempt(String user, String pass){
        HttpResponse<JsonNode> resp;
        try{
            resp = Unirest.post(FateAndFortune.ENDPOINT + "authenticate")
                    .field("username", user)
                    .field("password", pass)
                    .asJson();
        } catch(UnirestException e){
            e.printStackTrace();
            System.out.println(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR));
            return;
        }

        if(resp.getStatus() == 201){
            System.out.println("Login Successful");
            successfulLogin(resp.getBody().getObject().getString("id"), resp.getBody().getObject().getString("token"));

        }else{
            System.out.println("Bad");
        }
    }
    private void successfulLogin(String id, String authorization){
        try {
            if(ifp.exists())
                ifp.delete();
            ifp.createNewFile();

            FileWriter writer = new FileWriter(ifp);
            writer.write(id);
            writer.write("\n");
            writer.write(authorization);
            writer.flush();
            writer.close();
            gsm.set(new LobbyState(gsm));
        }catch(IOException e){
            return;
        }
    }

}
