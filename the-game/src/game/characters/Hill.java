package game.characters;

import game.drawers.TileMap;
import javafx.geometry.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class Hill extends TileMap {
    private ArrayList<Point2D> hill = new ArrayList<>();

    public Hill() throws IOException {
        super();
        mapHill();
    }

    public void mapHill()
    {
        for(int i = 0; i < getMAP_HEIGHT(); i++)
        {
            for(int j = 0; j < getMAP_WIDTH(); j++)
            {
                if(getGrid()[i][j] == 0)
                {
                    hill.add(new Point2D((j + 0.5)*GRID_SIZE, (i + 0.5)*GRID_SIZE));
                }
            }
        }
    }

    public ArrayList<Point2D> getHill() {
        return hill;
    }
}
