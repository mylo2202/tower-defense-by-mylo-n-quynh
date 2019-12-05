package game.characters;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.IOException;

public abstract class Enemy implements GameEntity {
    private int hitPoints;                      // How Strong enemy is
    private int moveDuration;                      // How fast enemy moves
    private int reward;                         // Gold reward for enemy's death
    private int damage;
    private boolean isDead;                     // Triggering flag for enemy's death and removal
    //private boolean reachedGoal;                // Check for enemy reaching the goal alive and removal if it does
    private int level;                          // How difficult to kill the enemy is

    //private GameStage gameStage;
    protected String imageUrl;
    protected Image enemyImage;
    protected ImageView View;

    private Road enemyRoad = new Road();

    public Image getEnemyImage() {
        return enemyImage;
    }

    public void setEnemyImage(Image enemyImage) {
        this.enemyImage = enemyImage;
    }

    public ImageView getView() {
        return View;
    }

    public void setView(ImageView view) {
        this.View = view;
        this.View.setTranslateX(enemyRoad.getSpawner().getX() - enemyRoad.getGRID_SIZE() / 2);
        this.View.setTranslateY(enemyRoad.getSpawner().getY() - enemyRoad.getGRID_SIZE() / 2);
    }

    public Enemy() throws IOException {
        isDead = false;
        //enemyMove().play();
    }

    public int getHitPoints()
    {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints)
    {
        this.hitPoints = hitPoints;
    }

    public void removeHitPoints(int hitPoints) {
        this.hitPoints = this.hitPoints - hitPoints;
    }

    public int getMoveDuration()
    {
        return moveDuration;
    }

    public void setMoveDuration(int moveDuration)
    {
        this.moveDuration = moveDuration;
    }

    public int getReward()
    {
        return reward;
    }

    public void setReward(int reward)
    {
        this.reward = reward;
    }

    public boolean isDead()
    {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Road getEnemyRoad() {
        return enemyRoad;
    }

    public boolean hasReachedGoal()
    {
        //setReachedGoal(true);
        return this.getView().getTranslateX() + this.getEnemyRoad().getGRID_SIZE() / 2 == this.getEnemyRoad().getGoal().getX() &&
                this.getView().getTranslateY() + this.getEnemyRoad().getGRID_SIZE() / 2 == this.getEnemyRoad().getGoal().getY();
    }

    public void takeDamage(int damage)      //reduces enemy's hit points and determines whether it is dead
    {
        hitPoints -= damage;
        if (hitPoints <= 0)
        {
            setDead(true);
            /*System.out.println("isDead = "  + isDead);
            System.out.println("reachedGoal = " + reachedGoal);*/
        }
    }

    public PathTransition enemyMove() {

        Path path= new Path();
        //Moving to the starting point
        MoveTo moveTo = new MoveTo(enemyRoad.getSpawner().getX(), enemyRoad.getSpawner().getY());
        path.getElements().add(moveTo);

        for(int i = 0; i < enemyRoad.getRoad().size(); i++) {
            LineTo line = new LineTo(enemyRoad.getRoad().get(i).getX(), enemyRoad.getRoad().get(i).getY());


            path.getElements().addAll(line);
        }

        LineTo endLine = new LineTo(enemyRoad.getGoal().getX(), enemyRoad.getGoal().getY());
        path.getElements().add(endLine);

        //Creating a path transition
        PathTransition pathTransition = new PathTransition();

        //Setting the duration of the path transition
        pathTransition.setDuration(Duration.seconds(this.moveDuration));

        //Setting the node for the transition
        pathTransition.setNode(View);

        //Setting the path
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);

        //Setting auto reverse value to false
        pathTransition.setAutoReverse(false);

        //Playing the animation
        return pathTransition;


    }

    public double distance(Tower tower) {
        double posEX = getView().getTranslateX();
        double posEY = getView().getTranslateY();

        return Math.sqrt(Math.pow(posEX - tower.getPos().getX(), 2) +
                Math.pow(posEY - tower.getPos().getY(), 2));
    }

    public double distance(Bullet bullet) {
        double posEX = getView().getTranslateX();
        double posEY = getView().getTranslateY();

        return Math.sqrt(Math.pow(posEX - bullet.getPos().getX(), 2) +
                Math.pow(posEY - bullet.getPos().getY(), 2));
    }
}