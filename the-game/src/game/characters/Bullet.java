package game.characters;

import game.characters.Towers.MachineGunTower;
import game.characters.Towers.NormalTower;
import game.characters.Towers.SniperTower;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet implements GameEntity {
    private int bulletDamage;
    private int bulletSpeed = 25;
    private Enemy bulletTarget;
    private ImageView view;
    private String url;
    //  private Music music = new Music();

    private Point2D pos;

    private boolean alive;

    public Bullet()
    {
        view = new ImageView(new Image("/Image/Bullet/normalBullet.png", 80, 80, false, true));
    }

    public Bullet(Tower tower, boolean sound) {
        view = new ImageView(new Image("/Image/Bullet/normalBullet.png", 80, 80, false, true));
        view.setTranslateX(tower.getTowerView().getTranslateX());
        view.setTranslateY(tower.getTowerView().getTranslateY());
        //   if (sound == true) music.getMediaBullet().play();
        pos = new Point2D(tower.getPos().getX(), tower.getPos().getY());
    }

    public Bullet(Tower tower) {
        if (tower instanceof NormalTower)
            view = new ImageView(new Image("/Image/Bullet/normalBullet.png", 80, 80, false, true));
        else if (tower instanceof MachineGunTower)
            view = new ImageView(new Image("/Image/Bullet/machineBullet.png", 80, 80, false, true));
        else if (tower instanceof SniperTower)
            view = new ImageView(new Image("/Image/Bullet/sniperBullet.png", 80, 80, false, true));
        else
            view = new ImageView(new Image("/Image/Bullet/normalBullet.png", 80, 80, false, true));
        view.setTranslateX(tower.getTowerView().getTranslateX());
        view.setTranslateY(tower.getTowerView().getTranslateY());
        pos = new Point2D(tower.getPos().getX(), tower.getPos().getY());
    }

    // public Music getMusic() {
    //    return music;
    // }
    public Point2D getPos() {
        return pos;
    }

    public boolean isDead() {
        return !isAlive();
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public int getBulletDamage()
    {
        return bulletDamage;
    }

    public void setBulletDamage(int bulletDamage)
    {
        this.bulletDamage = bulletDamage;
    }

    public int getBulletSpeed()
    {
        return bulletSpeed;
    }

    public void setBulletSpeed(int speed) {
        this.bulletSpeed = speed;
    }
    public Enemy getBulletTarget() {
        return bulletTarget;
    }

    public void setBulletTarget(Enemy bulletTarget)
    {
        this.bulletTarget = bulletTarget;
    }

    public void target(Tower tower)
    {
        setBulletTarget(tower.getAttackTarget());
    }

    @Override
    public ImageView getTowerView() {
        return view;
    }

    @Override
    public void setTowerView(ImageView towerView) {
        this.view = towerView;
    }

    @Override
    public String getTowerImgUrl() {
        return url;
    }

    @Override
    public void setTowerImgUrl(String towerImgUrl) {
        this.url = towerImgUrl;
    }

    public void update(Enemy enemy) {
        if (enemy != null) {
            double posEX = enemy.getTowerView().getTranslateX();
            double posEY = enemy.getTowerView().getTranslateY();

            Point2D centerE = new Point2D(posEX + 5, posEY + 5);

            double angle = (Math.atan2(centerE.getX() - getPos().getX(), centerE.getY() - getPos().getY()));
            getTowerView().setTranslateX(getTowerView().getTranslateX() + Math.sin(angle) * bulletSpeed);
            getTowerView().setTranslateY(getTowerView().getTranslateY() + Math.cos(angle) * bulletSpeed);
            //  System.out.println(getView().getTranslateX() + Math.sin(angle) * bulletSpeed);
        }
    }

    public boolean isColliding(Enemy enemy) {
        if (enemy != null) {
            double posEX = enemy.getTowerView().getTranslateX();
            double posEY = enemy.getTowerView().getTranslateY();
            double posX = getTowerView().getTranslateX();
            double posY = getTowerView().getTranslateY();
            return posEX - 20 <= posX && posX <= posEX + 20 && posEY - 20 <= posY && posY <= posEY + 20;
        }
        return false;

    }

}
