package game.characters;

import game.GameStage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TileMap {
    private int[][] grid;
    protected final int GRID_SIZE = 80;
    protected final int SCREEN_HEIGHT = 720;
    protected final int SCREEN_WIDTH = 1280;
    protected final int MAP_HEIGHT = 9;
    protected final int MAP_WIDTH = 12;


    public TileMap() throws IOException {
        grid = new int[MAP_HEIGHT][MAP_WIDTH];
        mapGrid();
    }

    public void mapGrid() throws IOException {
        Scanner scanner = new Scanner(new File("src/game/mapGrid.txt"));

        for(int i = 0; i < MAP_HEIGHT; ++i)
        {
            for(int j = 0; j < MAP_WIDTH; ++j)
                this.grid[i][j] = scanner.nextInt();
        }
    }
    public void drawMap(AnchorPane gamePane)
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                Image mapGrid = new Image("/Image/Map/map" + grid[i][j] + ".png", GRID_SIZE, GRID_SIZE,false,true);
                ImageView mapGridView =new ImageView(mapGrid);
                mapGridView.setLayoutX(j*GRID_SIZE);
                mapGridView.setLayoutY(i*GRID_SIZE);
                gamePane.getChildren().addAll(mapGridView);

            }
        }
    }

    public int getGRID_SIZE() {
        return GRID_SIZE;
    }

    public int getSCREEN_HEIGHT() {
        return SCREEN_HEIGHT;
    }

    public int getSCREEN_WIDTH() {
        return SCREEN_WIDTH;
    }

    public int getMAP_HEIGHT() {
        return MAP_HEIGHT;
    }

    public int getMAP_WIDTH() {
        return MAP_WIDTH;
    }

    public int [][] getGrid(){
        return grid;
    }
    public int getSize(){
        return GRID_SIZE;
    }
}
