package game.characters;

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

    protected String towerSkin;
    protected Image towerImage;
    protected ImageView towerView;

    private Hill towerHill = new Hill();

    //platform image properties and methods maybe go here

    public Tower() throws IOException {
        this.towerLevel = 1;
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

    public String getTowerSkin() {
        return towerSkin;
    }

    public void setTowerSkin(String towerSkin) {
        this.towerSkin = towerSkin;
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

    public void drawTower(int mapX, int mapY) throws IOException {
        Tower tower = null;
        tower = new NormalTower();
    }

    //attack method maybe goes here
}