package game.characters;

import game.GameField;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class Tower implements GameEntity
{
    private int attackCooldown;              // Delayed time for each attack
    private int attackDamage;                       // Amount of health to reduce from enemies per attack
    // Maximum range the tower can attack
    private int towerLevel;                         // The higher level the tower is the more effective it is
    private int buildCost;                          // Cost for building
    private int upgradeCost;                        // Cost for upgrading
    private int sellPrice;                          // Gold gained for selling
    private Enemy attackTarget;
    private int attackRange;
    private long towerTimer = System.nanoTime();

    protected String imageUrl;
    protected Image towerImage;
    protected ImageView View;
    private Label labelLevel;
    private ContextMenu contextMenu;
    private MenuItem upgrade;
    private MenuItem sell;

    private Point2D pos;
    private List<Bullet> bullet = new ArrayList<>();

    private Hill towerHill = new Hill();

    //platform image properties and methods maybe go here

    public Tower() throws IOException {
        this.towerLevel = 1;
        contextMenu = new ContextMenu();
        labelLevel = new Label("level " + towerLevel);
        upgrade = new MenuItem("upgrade");
        sell = new MenuItem("sell");


    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public Bullet getBullet() {
        return bullet.get(bullet.size() - 1);
    }

    public Bullet getBulletIndex(int i) {
        return bullet.get(i);
    }

    public List<Bullet> getBulletList() {
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

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public void createBullet(Bullet bullets) {

        bullet.add(bullets);
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

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public void update(Enemy enemy) {
        if (enemy != null) {

            double posEX = enemy.getView().getTranslateX();
            double posEY = enemy.getView().getTranslateY();
            double distance = enemy.distance(this);
            if (distance <= attackRange) {
                getView().setRotate(180 - Math.toDegrees(Math.atan2((posEX - getPos().getX())
                        , (posEY - getPos().getY()))));

                infoLevel().setRotate(180 - Math.toDegrees(Math.atan2((posEX - getPos().getX()), (posEY - getPos().getY()))));
            }
            System.out.println(getView().getTranslateX() + "  " + getView().getTranslateY());
            /*if (distance > RADIUS) {
                getView().setRotate(0);
            }*/
        } else {

            getView().setRotate(0);
            infoLevel().setRotate(0);
        }

    }

    public Enemy targetEnemy(List<Enemy> enemies) {

        if (!enemies.isEmpty()) {

            Enemy closestEnemy = enemies.get(0);
            for (int i = 0; i < enemies.size(); i++) {
                double distance;
                distance = enemies.get(i).distance(this);
                if (distance < attackRange && distance < closestEnemy.distance(this)) {
                    closestEnemy = enemies.get(i);
                }
            }

            return closestEnemy;
        } else return null;
    }

    public String getInfo() {
        return "Level1:\n" +
                "AttackDamage: " + getAttackDamage() + "\n" +
                "Each level is doubled" + "\n" +
                "AttackCooldown: " + getAttackCooldown() / 1e3 + "s\n" +
                "BuildCost: " + getBuildCost() + "\n" +
                "AttackRange: " + getAttackRange() + "\n";



    }

    public void towerAttack(long now, GameField gameField, AnchorPane gamePane) {
        if (now - this.towerTimer >= this.attackCooldown * 1e6) {

            /*gameField.getTowerList().forEach(tower -> {
                //if (this instanceof Tower)

            });*/

            if (!gameField.getEnemyList().isEmpty()

                    && this.targetEnemy(gameField.getEnemyList()).distance(this) <= this.getAttackRange()) {

                this.createBullet(new Bullet(this));

                gamePane.getChildren().add(this.getBullet().getView());
            }

            this.towerTimer = now;
        }
    }

    public void setUpgrade() {
        setAttackDamage(getAttackDamage() * 2);
    }

    public void setContextMenu() {
        getView().setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {

                contextMenu.show(getView(), event.getScreenX(), event.getScreenY());
            }
        });

        contextMenu.getItems().addAll(upgrade, sell);
    }


    public Label getLabelLevel() {
        return labelLevel;
    }

    public void setLabelLevel(Label labelLevel) {
        this.labelLevel = labelLevel;
    }

    public MenuItem getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(MenuItem upgrade) {
        this.upgrade = upgrade;
    }

    public MenuItem getSell() {
        return sell;
    }

    public void setSell(MenuItem sell) {
        this.sell = sell;
    }

    public Label infoLevel() {
        return labelLevel;

    }
}


