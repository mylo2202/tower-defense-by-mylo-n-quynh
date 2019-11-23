package game;

import game.characters.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//should this class be abstract?

public class GameField
{
    //methods for getting coordinates and sizes go here, maybe? or not?

    protected ArrayList<Enemy> enemyList;
    protected ArrayList<Tower> towerList;
    protected ArrayList<Bullet> bulletList;

    public GameField() {
        enemyList = new ArrayList<>();
        towerList = new ArrayList<>();
        bulletList = new ArrayList<>();
    }

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

    public void generateEnemy(Enemy enemy, int difficulty) throws IOException {
        Random random = new Random();
        int difficultyScore = difficulty;
        while(difficultyScore > 0)
        {
            int x = random.ints(1, 101).limit(1).findFirst().getAsInt();
            System.out.println(x);
            if(x >= 1 && x <= 30)
            {
                getEnemyList().add(enemy = new SmallerEnemy());
                difficultyScore -= getEnemyList().get(getEnemyList().size() - 1).getLevel();
            }
            else if(x >= 31 && x <= 70)
            {
                if(difficultyScore >= 2)
                {
                    getEnemyList().add(enemy = new NormalEnemy());
                    difficultyScore -= getEnemyList().get(getEnemyList().size() - 1).getLevel();
                }
            }
            else if(x >= 71 && x <= 90)
            {
                if(difficultyScore >= 10)
                {
                    getEnemyList().add(enemy = new TankerEnemy());
                    difficultyScore -= getEnemyList().get(getEnemyList().size() - 1).getLevel();
                }
            }
            else if(x >= 91 && x <= 100)
            {
                if(difficultyScore >= 50)
                {
                    getEnemyList().add(enemy = new BossEnemy());
                    difficultyScore -= getEnemyList().get(getEnemyList().size() - 1).getLevel();
                }
            }
        }
    }

    public boolean checkRemoveEnemy(int i)
    {
        if(!getEnemyList().isEmpty())
        {
            System.out.println("dead = " + getEnemyList().get(i).isDead() + " goal = " + getEnemyList().get(i));
            // .hasReachedGoal());
            return getEnemyList().get(i).isDead() || getEnemyList().get(i).hasReachedGoal();
            //System.out.println(getEnemyList());
        }
        return false;
    }

    public void removeEnemy(int i)
    {
        if(!getEnemyList().isEmpty())
        {
            if(checkRemoveEnemy(i))
            {
                getEnemyList().remove(getEnemyList().get(i));
            }
        }
    }

}