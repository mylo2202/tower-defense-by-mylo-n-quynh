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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
    private MyLabel moneyLabel;
    private double eventPosX, eventPosY;
    TileMap map = new TileMap();
    private boolean build;
    private boolean generatedEnemy = false;
    private MyLabel life;
    private MyLabel labelLevel;
    private int lives;
    private int level;
    //  ImageView gameOver = new ImageView(new Image("/Image/Enemy/gameOver.png"));
    private Music music = new Music();

    public GameField() throws IOException {
        enemyList = new ArrayList<>();
        towerList = new ArrayList<>();
        bulletList = new ArrayList<>();
        money = 200;
        moneyLabel = new MyLabel("MONEY : " + money);
        build = false;
        lives = 100;
        life = new MyLabel("x " + lives, "/Image/UI/life.png", 45, 100);
        String setText = "X ";
        life.setText(setText + lives);
        this.level = 0;
        labelLevel = new MyLabel("Level " + level);

    }

    public MyLabel getLabelLevel() {
        return labelLevel;
    }

    public void updateLabelLevel() {
        labelLevel.setText("Level " + getLevel());
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

    public boolean hasGeneratedEnemy() {
        return generatedEnemy;
    }

    public void setGeneratedEnemy(boolean generatedEnemy) {
        this.generatedEnemy = generatedEnemy;
    }

    public MyLabel getMoneyLabel() {
        return moneyLabel;
    }

    public void setMoneyLabel(MyLabel moneyLabel) {
        this.moneyLabel = moneyLabel;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void generateEnemy(ArrayList<Enemy> enemyList, int difficulty) throws IOException {
        Random rand = new Random();
        int randomNum = 0;
        if (difficulty == 1)
        {
            enemyList.add(new SmallerEnemy());
        } else if (difficulty > 1 && difficulty <= 10)
        {
            randomNum = rand.nextInt(7);
            if(randomNum >= 0 && randomNum <= 3) enemyList.add(new NormalEnemy());
            else if (randomNum >= 4 && randomNum <= 6) enemyList.add(new SmallerEnemy());
        } else if (difficulty > 10 && difficulty <= 50)
        {
            randomNum = rand.nextInt(9);
            if(randomNum >= 0 && randomNum <= 3) enemyList.add(new NormalEnemy());
            else if (randomNum >= 4 && randomNum <= 6) enemyList.add(new SmallerEnemy());
            else if (randomNum >= 7 && randomNum <= 8) enemyList.add(new TankerEnemy());
        } else if (difficulty > 50)
        {
            randomNum = rand.nextInt(10);
            if(randomNum >= 0 && randomNum <= 3) enemyList.add(new NormalEnemy());
            else if (randomNum >= 4 && randomNum <= 6) enemyList.add(new SmallerEnemy());
            else if (randomNum >= 7 && randomNum <= 8) enemyList.add(new TankerEnemy());
            else if (randomNum == 9) enemyList.add(new BossEnemy());
        }
        //System.out.println("random = " + randomNum);
        this.generatedEnemy = true;
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

    public void gameOver(AnimationTimer animationTimer, AnimationTimer timer, Stage game, Stage Menu) {

        if (lives <= 0) {
            music.getMediaBackground().stop();
            //music.getMediaGameOver().play();
            animationTimer.stop();
            timer.stop();
            Menu.show();
            game.close();
            if (!getEnemyList().isEmpty()) {
                getEnemyList().forEach(enemy -> {
                    enemy.enemyMove().stop();
                });
            }
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
                lives -= getEnemyList().get(i).getDamage();
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
                    map.getGrid()[i][j] = 1;
                    towerList.add(tower);
                    tower.getView().setTranslateX(j * map.getGRID_SIZE());
                    tower.getView().setTranslateY(i * map.getGRID_SIZE());

                    tower.setPos(new Point2D(tower.getView().getTranslateX(), tower.getView().getTranslateY()));
                    tower.infoLevel().setTranslateX(tower.getView().getTranslateX() + 25);
                    tower.infoLevel().setTranslateY(tower.getView().getTranslateY() + 30);
                    //  tower.infoLevel().setTranslateY(i * map.getGRID_SIZE()+40);

                    gamePane.getChildren().addAll(tower.getView(), tower.infoLevel());

                    money = money - tower.getBuildCost();
                    updateMoney();
                }
            }
            build = false;
        };
    }

    public void sellTower(AnchorPane anchorPane) {
        for (int i = 0; i < getTowerList().size(); i++) {
            int finalI = i;
            getTowerList().get(i).getSell().setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    money = money + getTowerList().get(finalI).getSellPrice();
                    updateMoney();
                    anchorPane.getChildren().removeAll(getTowerList().get(finalI).getView(), getTowerList().get(finalI).infoLevel());
                    getTowerList().remove(finalI);
                }
            });
        }
    }

    public void upgradeTower() {
        getTowerList().forEach(tower -> {
            tower.getUpgradeItem().setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (getMoney() >= tower.getUpgradeCost()) {
                        tower.upgradeTower();
                        tower.setUpgrade();
                        tower.getLabelLevel().setText("level " + tower.getTowerLevel());

                        money = money - tower.getUpgradeCost();
                        updateMoney();
                    }
                }
            });
        });

    }

    public void updateMoney() {
        String setTextMoney = "MONEY : ";
        if (money < 10) setTextMoney = setTextMoney + "0";
        if (money < 0) setTextMoney = setTextMoney + "00";
        moneyLabel.setText(setTextMoney + money);
    }

    public void updateMoneyReward(Enemy enemy) {
        money = money + enemy.getReward();
        updateMoney();
    }
}