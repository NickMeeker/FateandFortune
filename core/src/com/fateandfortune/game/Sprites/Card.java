package com.fateandfortune.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.fateandfortune.game.FateAndFortune;

/**
 * Created by nick on 3/24/18.
 */

public class Card {
    private Texture artwork;
    private String name;
    private String effect;
    private int value;

    public Card(FateAndFortune.CardType type){
        // TODO: add artwork
        switch(type){
            case MAGICIAN:
                name = "Magician";
                effect = "Name a non-Magician card and choose another player. If that player has that card, he or she is out of the round.";
                value = 1;
                break;
            case JUDGEMENT:
                name = "Judement";
                effect = "Look at another player's hand.";
                value = 2;
                break;
            case HERMIT:
                name = "Hermit";
                effect = "You and another player secretly compare hands. The player with the lower value is out of the round.";
                value = 3;
                break;
            case FOOLS:
                name = "The Fools";
                effect = "Until your next turn, ignore all effects from other players' cards.";
                value = 4;
                break;
            case STAR:
                name = "Star";
                effect = "Choose any player (including yourself) to discard his or her hand and draw a new card.";
                value = 5;
                break;
            case MOON:
                name = "The Moon";
                effect = "Trade hands with another player of your choice.";
                value = 6;
                break;
            case SUN:
                name = "The Sun";
                effect = "If you have this card and the King or the Prince in your hand, you must discard this card.";
                value = 7;
                break;
            case WORLD:
                name = "The World";
                effect = "If you discard this card, you are out of the round.";
                value = 8;
                break;
            default:
                System.out.println("Error bad card");
        }

    }
}
