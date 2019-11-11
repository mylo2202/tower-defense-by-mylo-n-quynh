package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TileMap extends GameTile {
    private int[][] grid;
    public  int GRID_SIZE = 48;


    public TileMap() throws IOException {
        super();
        grid = new int[15][20];
        mapGrid();
    }

    public void mapGrid() throws IOException {
        Scanner scanner = new Scanner(new File("src/game/mapGrid.txt"));

        for(int i = 0; i < 15; ++i)
        {
            for(int j = 0; j < 20; ++j)
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
    public int [][] getGrid(){
        return grid;
    }
    public int getSize(){
        return GRID_SIZE;
    }
}
