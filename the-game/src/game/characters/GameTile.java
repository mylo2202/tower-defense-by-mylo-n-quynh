package game.characters;

import game.GameEntity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


//this class manages everything that doesn't move
//including Towers, Hills, and Roads

abstract class GameTile extends GameEntity {
    private int[][] grid = new int[15][20];
    public final int GRID_SIZE = 48;
    public GameTile() {
      //  super(posX, posY);
    }



    //any other methods? idk
}
