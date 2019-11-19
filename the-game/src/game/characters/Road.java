package game.characters;

import game.drawers.TileMap;
import javafx.geometry.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class Road extends TileMap {
    private ArrayList<Point2D> road = new ArrayList<>();
    private Point2D spawner = new Point2D(0, GRID_SIZE*1.5);
    private Point2D goal = new Point2D(GRID_SIZE*MAP_WIDTH, SCREEN_HEIGHT - GRID_SIZE*1.5);

    public Road() throws IOException {
        super();
        mapRoad();
    }

    public void mapRoad()
    {
        //starting point and ending point a.k.a. spawner (wtf?) and goal
        int pointerX = 0;
        int pointerY = 1;

        while(!(pointerX == 11 && pointerY == 7))
        {
            if(getGrid()[pointerY][pointerX] == 50) pointerX++;
            if(getGrid()[pointerY][pointerX] == 51) pointerX--;
            if(getGrid()[pointerY][pointerX] == 60) pointerY++;
            if(getGrid()[pointerY][pointerX] == 61) pointerY--;
            if(getGrid()[pointerY][pointerX] == 10 || getGrid()[pointerY][pointerX] == 31)
            {
                road.add(new Point2D((pointerX + 0.5)*GRID_SIZE, (pointerY + 0.5)*GRID_SIZE));
                pointerX++;
            }
            if(getGrid()[pointerY][pointerX] == 21 || getGrid()[pointerY][pointerX] == 40)
            {
                road.add(new Point2D((pointerX + 0.5)*GRID_SIZE, (pointerY + 0.5)*GRID_SIZE));
                pointerX--;
            }
            if(getGrid()[pointerY][pointerX] == 11 || getGrid()[pointerY][pointerX] == 20)
            {
                road.add(new Point2D((pointerX + 0.5)*GRID_SIZE, (pointerY + 0.5)*GRID_SIZE));
                pointerY++;
            }
            if(getGrid()[pointerY][pointerX] == 30 || getGrid()[pointerY][pointerX] == 41)
            {
                road.add(new Point2D((pointerX + 0.5)*GRID_SIZE, (pointerY + 0.5)*GRID_SIZE));
                pointerY--;
            }

            //System.out.println(pointerX + " " + pointerY + " " + getGrid()[pointerY][pointerX]);
        }

        /*for (Point2D point2D : road) {
            System.out.println(point2D);
        }*/
    }

    public Point2D getSpawner() {
        return spawner;
    }

    public Point2D getGoal() {
        return goal;
    }

    public ArrayList<Point2D> getRoad() {
        return road;
    }
}