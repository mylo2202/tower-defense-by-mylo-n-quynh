package game.characters;

//this class is still an experiment
//idk what to do with it actually
//but it is required so...
//this is dumb

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameStage
{
    private int difficulty;
    
    public GameStage() {}

    public GameStage(int difficulty)
    {
        //other methods and properties maybe go here

        this.difficulty = difficulty;
    }

    //or here
    public int getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }
}