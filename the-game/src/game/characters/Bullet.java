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
    private Point2D pos;


    public Bullet()
    {
        view = new ImageView(new Image("/Image/Bullet/bullet1.png", 60, 60, false, true));
        view.setTranslateX(0);
        view.setTranslateY(0);
    }

    public Bullet(Tower tower) {
        view = new ImageView(new Image("/Image/Bullet/bullet1.png", 60, 60, false, true));
        view.setTranslateX(tower.getView().getTranslateX());
        view.setTranslateY(tower.getView().getTranslateY());
        direction = tower.getVelocity();
        pos = new Point2D(tower.getView().getTranslateX(), tower.getView().getTranslateY());
    }

    public Point2D getPos() {
        return pos;
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

    public Point2D getDirection() {
        return direction;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }

    public void update(Enemy enemy) {
        double posEX = enemy.getView().getTranslateX();
        double posEY = enemy.getView().getTranslateY();


        if (posEX < getPos().getX()) {

            getView().setTranslateX(view.getTranslateX() - direction.getX());
            getView().setTranslateY(view.getTranslateY() - direction.getY());
        } else {
            getView().setTranslateX(view.getTranslateX() + direction.getX());
            getView().setTranslateY(view.getTranslateY() + direction.getY());
        }

        System.out.println(direction + "****");
    }
    //bullet trace method goes here, i think
}
