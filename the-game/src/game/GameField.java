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
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
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
    Image hammer = new Image("/Image/Tower/Hammer.png");
    private int money;
    private MyLabel moneyLabel;
    private double eventPosX, eventPosY;
    final TileMap map = new TileMap();
    private boolean build;
    private boolean generatedEnemy = false;
    private MyLabel life;
    private final MyLabel labelLevel;
    private int lives;
    private int level;
    //  ImageView gameOver = new ImageView(new Image("/Image/Enemy/gameOver.png"));
    private final Music music = new Music();

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
        int randomNum;
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

    public void gameOver(AnimationTimer animationTimer, AnimationTimer timer, Stage game, Stage menu) {

        if (lives <= 0) {
            music.getMediaBackground().stop();
            //music.getMediaGameOver().play();
            animationTimer.stop();
            timer.stop();
            menu.show();
            game.close();
            if (!getEnemyList().isEmpty()) {
                getEnemyList().forEach(enemy -> enemy.enemyMove().stop());
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
                getEnemyList().set(i, null);
                getEnemyList().remove(getEnemyList().get(i));
            }

        }
    }

    public EventHandler buildTower(AnchorPane gamePane, Tower tower) {

        tower.getUpgradeMenuItem().setText("upgrade to level " +
                (tower.getTowerLevel() + 1) + " (" + tower.getUpgradeCost() + ")");
        tower.getSellMenuItem().setText("sell (" + tower.getSellPrice() + ")");
        return (EventHandler<MouseEvent>) mouseEvent -> {
            if (build && money >= tower.getBuildCost() && lives > 0) {
                eventPosX = mouseEvent.getSceneX();
                eventPosY = mouseEvent.getSceneY();

                int i = (int) eventPosY / TileMap.getGRID_SIZE();
                int j = (int) eventPosX / TileMap.getGRID_SIZE();

                if (i < map.getMAP_HEIGHT() && j < map.getMAP_WIDTH() && map.getGrid()[i][j] == 0) {
                    map.getGrid()[i][j] = 1;
                    towerList.add(tower);
                    tower.getTowerView().setTranslateX(j * TileMap.getGRID_SIZE());
                    tower.getTowerView().setTranslateY(i * TileMap.getGRID_SIZE());
                    tower.setPos(new Point2D(tower.getTowerView().getTranslateX(), tower.getTowerView().getTranslateY()));
                    tower.infoLevel().setTranslateX(tower.getTowerView().getTranslateX() + 25);
                    tower.infoLevel().setTranslateY(tower.getTowerView().getTranslateY() + TileMap.getGRID_SIZE() - 8);
                    tower.getPlatformView().setTranslateX(j * TileMap.getGRID_SIZE());
                    tower.getPlatformView().setTranslateY(i * TileMap.getGRID_SIZE());

                    gamePane.getChildren().addAll(tower.getPlatformView() ,tower.getTowerView(), tower.infoLevel());

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
            getTowerList().get(i).getSellMenuItem().setOnAction(event -> {
                money = money + getTowerList().get(finalI).getSellPrice();
                updateMoney();
                int x = (int) getTowerList().get(finalI).getTowerView().getTranslateX() / TileMap.getGRID_SIZE();
                int y = (int) getTowerList().get(finalI).getTowerView().getTranslateY() / TileMap.getGRID_SIZE();
                map.getGrid()[y][x] = 0;
                anchorPane.getChildren().removeAll(getTowerList().get(finalI).getPlatformView() ,
                        getTowerList().get(finalI).getTowerView(),
                        getTowerList().get(finalI).infoLevel());
                getTowerList().set(finalI, null);
                getTowerList().remove(finalI);
            });
        }
    }

    public void upgradeTower() {
        getTowerList().forEach(tower -> tower.getUpgradeMenuItem().setOnAction(event -> {
            if (getMoney() >= tower.getUpgradeCost()) {
                tower.upgradeTower();
                money = money - tower.getUpgradeCost();
                updateMoney();
                tower.setUpgrade();
                tower.getUpgradeMenuItem().setText("upgrade to level " +
                        (tower.getTowerLevel() + 1) + " (" + tower.getUpgradeCost() + ")");
            }
        }));

    }

    public void updateMoney() {
        String setTextMoney = "MONEY : ";
        if (money < 10) setTextMoney = setTextMoney + "0";
        if (money < 0) setTextMoney = setTextMoney + "00";
        moneyLabel.setText(setTextMoney + money);
    }

    public void updateMoney(Enemy enemy) {
        money = money + enemy.getReward();
        updateMoney();
    }
}