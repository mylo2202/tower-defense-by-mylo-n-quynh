package game.characters;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;


public abstract class Tower implements GameEntity
{
    private int attackDamage;                       // Amount of health to reduce from enemies per attack
    private double attackCooldown;                     // Delayed time for each attack
    private int attackRange;                        // Maximum range the tower can attack
    private int towerLevel;                         // The higher level the tower is the more effective it is
    private int buildCost;                          // Cost for building
    private int upgradeCost;                        // Cost for upgrading
    private int sellPrice;                          // Gold gained for selling
    private Enemy attackTarget;
    private final int RADIUS = 320;
    //   protected List<Bullet> bullets= new ArrayList<>();

    protected String imageUrl;
    protected Image towerImage;
    protected ImageView View;
    private  ImageView platform;
    private Point2D pos;
    Bullet bullet;
    private Point2D velocity;

    private Hill towerHill = new Hill();
    private double slope;

    //platform image properties and methods maybe go here

    public Tower() throws IOException {
        this.towerLevel = 1;
        platform= new ImageView( new Image("/Image/Tower/platform.png",getTowerHill().getGRID_SIZE(), getTowerHill().getGRID_SIZE(), false, true));
        pos = new Point2D(getPlatform().getTranslateX(),getPlatform().getTranslateY());
        velocity = new Point2D(1, slope);
    }

    public Bullet createBullet() {
        bullet = new Bullet();
        return bullet;
    }
    public int getAttackDamage()
    {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage)
    {
        this.attackDamage = attackDamage;
    }

    public double getAttackCooldown()
    {
        return attackCooldown;
    }

    public void setAttackCooldown(double attackCooldown)
    {
        this.attackCooldown = attackCooldown;
    }

    public int getAttackRange()
    {
        return attackRange;
    }

    public void setAttackRange(int attackRange)
    {
        this.attackRange = attackRange;
    }

    public int getTowerLevel()
    {
        return towerLevel;
    }

    public void setTowerLevel(int towerLevel)
    {
        this.towerLevel = towerLevel;
    }

    public int getBuildCost()
    {
        return buildCost;
    }

    public void setBuildCost(int buildCost)
    {
        this.buildCost = buildCost;
    }

    public int getUpgradeCost()
    {
        return upgradeCost;
    }

    public void setUpgradeCost(int upgradeCost)
    {
        this.upgradeCost = upgradeCost;
    }

    public int getSellPrice()
    {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice)
    {
        this.sellPrice = sellPrice;
    }

    public void upgradeTower()
    {
        this.towerLevel++;
    }

    public Enemy getAttackTarget()
    {
        return attackTarget;
    }

    public void setAttackTarget(Enemy attackTarget)
    {
        this.attackTarget = attackTarget;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Image getTowerImage() {
        return towerImage;
    }

    public void setTowerImage(Image towerImage) {
        this.towerImage = towerImage;
    }

    public ImageView getView() {
        return View;
    }

    public void setView(ImageView view) {
        this.View = view;
    }

    public Hill getTowerHill() {
        return towerHill;
    }

    public ImageView getPlatform() {
        return platform;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
//attack method maybe goes here

    public Point2D getVelocity() {
        velocity = new Point2D(1 * 10, getSlope() * 10);
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(Enemy enemy) {
        double posEX = enemy.getView().getTranslateX();
        double posEY = enemy.getView().getTranslateY();
        slope = (posEY - getPos().getY()) / (posEX - getPos().getX());

    }

    public void update(Enemy enemy) {
        double posEX = enemy.getView().getTranslateX();
        double posEY = enemy.getView().getTranslateY();
        double distance = enemy.distance(this);
        if (posEY < getPos().getY() && distance <= RADIUS) {

            getView().setRotate(-Math.toDegrees(Math.atan((posEX - getPos().getX())
                    / (posEY - getPos().getY()))));
        }

        if (posEY > getPos().getY() && distance <= RADIUS)
            getView().setRotate(180 - Math.toDegrees(Math.atan((posEX - getPos().getX())
                    / (posEY - getPos().getY()))));
        if (distance > RADIUS) {
            getView().setRotate(0);
        }

    }



}


