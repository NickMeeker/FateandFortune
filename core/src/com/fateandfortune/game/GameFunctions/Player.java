package com.fateandfortune.game.GameFunctions;

import com.fateandfortune.game.Sprites.Card;

import java.util.ArrayList;

/**
 * Created by nick on 3/24/18.
 */

public class Player {
    private ArrayList<Card> hand = new ArrayList<Card>();
    private ArrayList<Card> discard = new ArrayList<Card>();
    private boolean turnPlayer;

    public Player(){
        this.turnPlayer = false;
    }

}
