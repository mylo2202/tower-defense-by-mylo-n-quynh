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
    private Point2D direction;


    public Bullet()
    {
        view = new ImageView(new Image("/Image/Bullet/bullet1.png", 60, 60, false, true));
        view.setTranslateX(0);
        view.setTranslateY(0);
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

    public Point2D getDirection(Enemy enemy) {
        return direction;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }

    public void update() {
        getView().setTranslateX(view.getTranslateX() + direction.getX());
        getView().setTranslateY(view.getTranslateY() + direction.getY());
    }
    //bullet trace method goes here, i think
}
