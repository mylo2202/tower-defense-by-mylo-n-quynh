package game;

import game.characters.*;

import java.util.ArrayList;

//should this class be abstract?

public class GameEntity
{
    //methods for getting coordinates and sizes go here, maybe? or not?

    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private ArrayList<Tower> towerList = new ArrayList<>();
    private ArrayList<Bullet> bulletList = new ArrayList<>();

    public GameEntity() {}

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(ArrayList<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

    public ArrayList<Tower> getTowerList() {
        return towerList;
    }

    public void setTowerList(ArrayList<Tower> towerList) {
        this.towerList = towerList;
    }

    public ArrayList<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(ArrayList<Bullet> bulletList) {
        this.bulletList = bulletList;
    }
}