package game;

import game.characters.*;
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
    private int lifes;
    ImageView gameOver = new ImageView(new Image("/Image/Enemy/gameOver.png"));
    private Music music = new Music();

    public GameField() throws IOException {
        enemyList = new ArrayList<>();
        towerList = new ArrayList<>();
        bulletList = new ArrayList<>();
        money = 2500;
        Money = new MyLabel("MONEY : " + money);
        build = false;
        lifes = 100;
        life = new MyLabel("x " + lifes, "/Image/UI/life.png", 45, 100);
        String setText = "X ";
        if (lifes < 10 && lifes > 0) setText = setText + "0";
        life.setText(setText + lifes);
        gameOver.setLayoutX(500);
        gameOver.setLayoutY(400);


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
            //   System.out.println("dead = " + getEnemyList().get(i).isDead() + " goal = " + getEnemyList().get(i));
            // .hasReachedGoal());
            return getEnemyList().get(i).isDead() || getEnemyList().get(i).hasReachedGoal();
            //System.out.println(getEnemyList());
        }
        return false;
    }

    public void gameOver(AnimationTimer animationTimer, AnchorPane anchorPane) {

        if (lifes <= 0) {
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
                lifes--;
                String setText = "X ";
                if (lifes < 10) setText = setText + "0";
                life.setText(setText + lifes);
                if (lifes < 0) life.setText("X 00");
                //music.getMediaEnemyHasGoal().play();

            }
            if(checkRemoveEnemy(i))
            {
                getEnemyList().remove(getEnemyList().get(i));
            }

        }
    }

    /* public EventHandler getMouse(){

         EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent mouseEvent) {
                     eventPosX = mouseEvent.getSceneX();
                     eventPosY = mouseEvent.getSceneY();
                 }
         };
        return eventHandler;
     }*/
    public EventHandler buildTower(AnchorPane gamePane, Tower tower) throws IOException {

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (build == true && money >= tower.getBuildCost() && lifes > 0) {
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
            }
        };

        return eventHandler;
    }

    public void updateMoney(Enemy enemy) {
        money = money + enemy.getReward();
        String setTextMoney = "MONEY : ";
        if (money < 10) setTextMoney = setTextMoney + "0";
        Money.setText(setTextMoney + money);
    }


}