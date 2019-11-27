package game.characters;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet implements GameEntity {
    private int bulletDamage;
    private final int bulletSpeed = 2048;
    private Enemy bulletTarget;
    private ImageView view;
    private String url;

    private Point2D pos;

    private boolean alive;

    public Bullet()
    {
        view = new ImageView(new Image("/Image/Bullet/bullet1.png", 60, 60, false, true));

    }
    public Bullet(Tower tower) {
        view = new ImageView(new Image("/Image/Bullet/bullet1.png", 60, 60, false, true));
        view.setTranslateX(tower.getView().getTranslateX());
        view.setTranslateY(tower.getView().getTranslateY());

        pos = new Point2D(tower.getView().getTranslateX(), tower.getView().getTranslateY());
    }
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

    public final int getBulletSpeed()
    {
        return bulletSpeed;
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
    public ImageView getView() {
        return view;
    }

    @Override
    public void setView(ImageView view) {
        this.view = view;
    }

    @Override
    public String getImageUrl() {
        return url;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.url = imageUrl;
    }

    public void update(Enemy enemy) {
        if (enemy != null) {
            double posEX = enemy.getView().getTranslateX();
            double posEY = enemy.getView().getTranslateY();

            Point2D centerE = new Point2D(posEX, posEY);

            double angle = (Math.atan2(centerE.getX() - getPos().getX(), centerE.getY() - getPos().getY()));
            getView().setTranslateX(getView().getTranslateX() + Math.sin(angle) * 10);
            getView().setTranslateY(getView().getTranslateY() + Math.cos(angle) * 10);
        }
    }

    public boolean isColliding(Enemy enemy) {
        if (enemy != null) {
            double posEX = enemy.getView().getTranslateX();
            double posEY = enemy.getView().getTranslateY();
            double posX = getView().getTranslateX();
            double posY = getView().getTranslateY();
            if (posEX <= posX && posX <= posEX + 40 && posEY <= posY && posY <= posEY + 40) return true;
            if (posEX >= posX && posX <= posEX - 40 && posEY <= posY && posY >= posEY + 40) return true;
            if (posEX <= posX && posX <= posEX + 40 && posEY >= posY && posY <= posEY - 40) return true;
            return posEX <= posX && posX <= posEX + 40 && posEY <= posY && posY >= posEY + 40;
            //  return getView().getBoundsInParent().intersects(enemy.getView().getBoundsInParent());
        }
        return false;

    }

}
