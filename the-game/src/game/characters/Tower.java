package game.characters;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;


public abstract class Tower
{
    private int attackDamage;                       // Amount of health to reduce from enemies per attack
    private double attackCooldown;                     // Delayed time for each attack
    private int attackRange;                        // Maximum range the tower can attack
    private int towerLevel;                         // The higher level the tower is the more effective it is
    private int buildCost;                          // Cost for building
    private int upgradeCost;                        // Cost for upgrading
    private int sellPrice;                          // Gold gained for selling
    private Enemy attackTarget;

    protected String towerUrl;
    protected Image towerImage;
    protected ImageView towerView;
    private  ImageView platform;
    private Point2D pos;

    private Hill towerHill = new Hill();

    //platform image properties and methods maybe go here

    public Tower() throws IOException {
        this.towerLevel = 1;
        platform= new ImageView( new Image("/Image/Tower/platform.png",getTowerHill().getGRID_SIZE(), getTowerHill().getGRID_SIZE(), false, true));
        pos = new Point2D(getPlatform().getTranslateX(),getPlatform().getTranslateY());
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

    public String getTowerUrl() {
        return towerUrl;
    }

    public void setTowerUrl(String towerUrl) {
        this.towerUrl = towerUrl;
    }

    public Image getTowerImage() {
        return towerImage;
    }

    public void setTowerImage(Image towerImage) {
        this.towerImage = towerImage;
    }

    public ImageView getTowerView() {
        return towerView;
    }

    public void setTowerView(ImageView towerView) {
        this.towerView = towerView;
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
}