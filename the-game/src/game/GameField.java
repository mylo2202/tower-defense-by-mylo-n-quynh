package game;

import game.characters.*;
import game.characters.Enemies.BossEnemy;
import game.characters.Enemies.NormalEnemy;
import game.characters.Enemies.SmallerEnemy;
import game.characters.Enemies.TankerEnemy;
import game.drawers.MyLabel;
import game.drawers.TileMap;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    Image hammer = new Image("/Image/Tower/Hammer.png");
    private int money;
    private MyLabel Money;
    private double eventPosX, eventPosY;
    TileMap map = new TileMap();
    private boolean build;
    private MyLabel life;
    private int lives;
    private int level;
    ImageView gameOver = new ImageView(new Image("/Image/Enemy/gameOver.png"));
    private Music music = new Music();

    public GameField() throws IOException {
        enemyList = new ArrayList<>();
        towerList = new ArrayList<>();
        bulletList = new ArrayList<>();
        money = 200;
        Money = new MyLabel("MONEY : " + money);
        build = false;
        lives = 100;
        life = new MyLabel("x " + lives, "/Image/UI/life.png", 45, 100);
        String setText = "X ";
        if (lives < 10 && lives > 0) setText = setText + "0";
        life.setText(setText + lives);
        gameOver.setLayoutX(500);
        gameOver.setLayoutY(400);
        this.level = 1;
    }

    public double getEventPosX() {
        return eventPosX;
    }

    public double getEventPosY() {
        return eventPosY;
    }

    public MyLabel getLife() {
        return life;
    }

    public void setLife(MyLabel life) {
        this.life = life;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isBuild() {
        return build;
    }

    public void setBuild(boolean build) {
        this.build = build;
    }

    public MyLabel getMoney() {
        return Money;
    }

    public void setMoney(MyLabel money) {
        this.Money = money;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void generateEnemy(ArrayList<Enemy> enemyList, int difficulty) throws IOException {
        Random rand = new Random();
        int randomNum = 0;
        if (difficulty == 1)
        {
            enemyList.add(new SmallerEnemy());
        }
        else if (difficulty >= 2 && difficulty < 10)
        {
            randomNum = rand.nextInt(7);
            if(randomNum >= 0 && randomNum <= 3) enemyList.add(new NormalEnemy());
            else if (randomNum >= 4 && randomNum <= 6) enemyList.add(new SmallerEnemy());
        }
        else if (difficulty >= 10 && difficulty < 50)
        {
            randomNum = rand.nextInt(9);
            if(randomNum >= 0 && randomNum <= 3) enemyList.add(new NormalEnemy());
            else if (randomNum >= 4 && randomNum <= 6) enemyList.add(new SmallerEnemy());
            else if (randomNum >= 7 && randomNum <= 8) enemyList.add(new TankerEnemy());
        }
        else if (difficulty >= 50)
        {
            randomNum = rand.nextInt(10);
            if(randomNum >= 0 && randomNum <= 3) enemyList.add(new NormalEnemy());
            else if (randomNum >= 4 && randomNum <= 6) enemyList.add(new SmallerEnemy());
            else if (randomNum >= 7 && randomNum <= 8) enemyList.add(new TankerEnemy());
            else if (randomNum == 9) enemyList.add(new BossEnemy());
        }
        System.out.println("random = " + randomNum);
    }

    public boolean checkRemoveEnemy(int i)
    {
        if(!getEnemyList().isEmpty())
        {
            //   System.out.println("dead = " + getEnemyList().get(i).isDead() + " goal = " + getEnemyList().get(i));
            // .hasReachedGoal());
            return getEnemyList().get(i).isDead() || getEnemyList().get(i).hasReachedGoal();
            //System.out.println(getEnemyList());
        }
        return false;
    }

    public void gameOver(AnimationTimer animationTimer, AnchorPane anchorPane) {

        if (lives <= 0) {
            music.getMediaBackground().stop();
            music.getMediaGameOver().play();
            animationTimer.stop();
            if (!getEnemyList().isEmpty()) {
                getEnemyList().forEach(enemy -> {
                    enemy.enemyMove().stop();
                });
            }
            anchorPane.getChildren().add(gameOver);

        }
    }

    public Music getMusic() {
        return music;
    }

    public void removeEnemy(int i)
    {
        if(!getEnemyList().isEmpty())
        {
            if (getEnemyList().get(i).hasReachedGoal()) {
                lives -= getEnemyList().get(i).getLevel();
                String setText = "X ";
                if (lives < 10) setText = setText + "0";
                life.setText(setText + lives);
                if (lives < 0) life.setText("X 00");
                //music.getMediaEnemyHasGoal().play();

            }
            if(checkRemoveEnemy(i))
            {
                getEnemyList().remove(getEnemyList().get(i));
            }

        }
    }

    public EventHandler buildTower(AnchorPane gamePane, Tower tower) {

        return (EventHandler<MouseEvent>) mouseEvent -> {
            if (build && money >= tower.getBuildCost() && lives > 0) {
                eventPosX = mouseEvent.getSceneX();
                eventPosY = mouseEvent.getSceneY();

                int i = (int) eventPosY / map.getGRID_SIZE();
                int j = (int) eventPosX / map.getGRID_SIZE();

                if (i < map.getMAP_HEIGHT() && j < map.getMAP_WIDTH() && map.getGrid()[i][j] == 0) {

                    towerList.add(tower);
                    tower.getView().setTranslateX(j * map.getGRID_SIZE());
                    tower.getView().setTranslateY(i * map.getGRID_SIZE());
                    tower.setPos(new Point2D(tower.getView().getTranslateX(), tower.getView().getTranslateY()));
                    gamePane.getChildren().add(tower.getView());

                    money = money - tower.getBuildCost();
                    String setTextMoney = "MONEY : ";
                    if (money < 10) setTextMoney = setTextMoney + "0";
                    Money.setText(setTextMoney + money);
                }
            }
            build = false;
        };
    }

    public void updateMoney(Enemy enemy) {
        money = money + enemy.getReward();
        String setTextMoney = "MONEY : ";
        if (money < 10) setTextMoney = setTextMoney + "0";
        Money.setText(setTextMoney + money);
    }
}