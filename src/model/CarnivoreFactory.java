package model;

import javafx.scene.image.Image;

public class CarnivoreFactory {
    private static final Image LION_COMBAT_IMAGE = new Image("model/Resources/lion_pixelized.png");
    private static final Image WOLF_COMBAT_IMAGE = new Image("model/Resources/wolf_pixelized.png");
    private static final Image CROC_COMBAT_IMAGE = new Image("model/Resources/croc_pixelized.png");
    private static final Image LION_GAME_IMAGE = new Image("model/Resources/lion.png");
    private static final Image WOLF_GAME_IMAGE = new Image("model/Resources/wolf.png");
    private static final Image CROC_GAME_IMAGE = new Image("model/Resources/croc.png");

    private Game game;

    public CarnivoreFactory(Game game){
        this.game = game;
    }

    public Carnivore produceCarnivore(Vector2 pos,Carnivore.CarnType type){
        switch(type){
            case WOLF:
                return new Carnivore(pos, game,2, 2, 3, 3, new boolean[]{true, true, false}, WOLF_COMBAT_IMAGE, WOLF_GAME_IMAGE, type);
            case LION:
                return new Carnivore(pos, game,2, 8, 5, 4, new boolean[]{true, true, false}, LION_COMBAT_IMAGE, LION_GAME_IMAGE, type);
            case CROC:
                return new Carnivore(pos, game,2, 5, 2, 3, new boolean[]{false, false, true}, CROC_COMBAT_IMAGE, CROC_GAME_IMAGE, type);
        }
        return null;
    }

}
