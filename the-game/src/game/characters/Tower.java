package game.characters;

import game.GameEntity;
import game.GameStage;
import game.characters.*;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public abstract class Tower extends GameEntity
{
    private int attackDamage;                       // Amount of health to reduce from enemies per attack
    private double attackSpeed;                     // Delayed time for each attack
    private int attackRange;                        // Maximum range the tower can attack
    private int towerLevel;                         // The higher level the tower is the more effective it is
    private int buildCost;                          // Cost for building
    private int upgradeCost;                        // Cost for upgrading
    private int sellPrice;                          // Gold gained for selling
    private Enemy attackTarget;

    protected String towerSkin;
    protected Image towerImage;
    protected ImageView towerView;

    public Tower()
    {
        //super();

//        this.attackDamage = attackDamage;
//        this.attackSpeed = attackSpeed;
//        this.attackRange = attackRange;
//        this.towerLevel = towerLevel;
//        this.buildCost = buildCost;
//        this.upgradeCost = upgradeCost;
//        this.sellPrice = sellPrice;

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

    public double getAttackSpeed()
    {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed)
    {
        this.attackSpeed = attackSpeed;
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


    public void setTowerView() {
        this.towerView = new ImageView(new Image("/Image/Tower/3.png"));
        this.towerView.setTranslateX(super.getView().getTranslateX());
        this.towerView.setTranslateY(super.getView().getTranslateY()+10);
    }

    public void setGun(ImageView imageView){
        super.setView(imageView) ;
    }
    public ImageView getGun(){
        return super.getView();
    }

    public void rotateLeft(){
        super.getView().setRotate(super.getView().getRotate()-30);
        super.setVelocity( new Point2D(Math.cos(Math.toRadians(getRotate())),Math.sin(Math.toRadians(getRotate()))));

    }
    public void rotateRight(){
        super.getView().setRotate(super.getView().getRotate()+30);
        super.setVelocity( new Point2D(Math.cos(Math.toRadians(getRotate())),Math.sin(Math.toRadians(getRotate()))));

    }
    public void setRotate(){
        towerView.setRotate(0);
    }
    public void update(Point2D p){
        towerView.setRotate(Math.toDegrees(Math.atan(p.getY()/p.getX())));

    }
}