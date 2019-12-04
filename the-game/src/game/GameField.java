package game;

import game.characters.Bullet;
import game.characters.Enemies.BossEnemy;
import game.characters.Enemies.NormalEnemy;
import game.characters.Enemies.SmallerEnemy;
import game.characters.Enemies.TankerEnemy;
import game.characters.Enemy;
import game.characters.Tower;
import game.drawers.MyLabel;
import game.drawers.TileMap;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    private int money;
    private MyLabel MoneyLabel;
    private MyLabel LevelLabel;
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
        MoneyLabel = new MyLabel("MONEY : " + money);

        LevelLabel = new MyLabel("Level 0");
        build = false;
        lives = 100;
        life = new MyLabel("x " + lives, "/Image/UI/life.png", 45, 100);
        String setText = "X ";
        if (lives < 10 && lives > 0) setText = setText + "0";
        life.setText(setText + lives);
        gameOver.setLayoutX(300);
        gameOver.setLayoutY(200);
        this.level = 0;
    }

    public double getEventPosX() {
        return eventPosX;
    }

    public double getEventPosY() {
        return eventPosY;
    }



    public MyLabel getLabelLevel() {
        return LevelLabel;
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

    public void setLabelLevel(MyLabel level) {
        LevelLabel = level;
    }

    public MyLabel getMoneyLabel() {
        return MoneyLabel;
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

    public void setMoneyLabel(MyLabel moneyLabel) {
        this.MoneyLabel = moneyLabel;
    }

    public int getLevelLabel() {
        return level;
    }

    public void setLevelLabel(int levelLabel) {
        this.level = levelLabel;
    }

    public void updateLabelLevel() {
        level++;
        LevelLabel.setText("Level " + level);
    }

    public void updateLevel(int level) {
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

    public boolean gameOver() {
        return lives <= 0;
    }

    public void gameOver(AnimationTimer animationTimer, AnimationTimer timer, Stage game, Stage menu) throws IOException {

        if (lives <= 0) {
            animationTimer.stop();
            timer.stop();
            game.close();

            menu.show();

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
                lives--;
                String setText = "X ";
                if (lives < 10) setText = setText + "0";
                life.setText(setText + lives);
                if (lives < 0) life.setText("X 00");
                //   music.getMediaEnemyHasGoal().play();
                // music.getMediaEnemyHasGoal().setStopTime(music.getMediaEnemyHasGoal().getStopTime()/2);


            }
            if(checkRemoveEnemy(i))
            {
                getEnemyList().remove(getEnemyList().get(i));
                music.getMediaEnemyHasGoal().stop();
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
                    MoneyLabel.setText(setTextMoney + money);
                }
            }
            build = false;
        };
    }

    public void updateMoney(Enemy enemy) {
        money = money + enemy.getReward();
        String setTextMoney = "MONEY : ";
        if (money < 10) setTextMoney = setTextMoney + "0";
        MoneyLabel.setText(setTextMoney + money);
    }
}