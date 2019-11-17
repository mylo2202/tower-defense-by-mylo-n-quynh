package game.characters;

import game.GameEntity;
import game.characters.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet extends GameEntity {
    private int bulletDamage;
    private int bulletRange;
    private final int bulletSpeed = 1000;
    private Enemy bulletTarget;
    private ImageView bullet;

    public Bullet(Tower tower)
    {
        super();
       /* this.bulletDamage = tower.getAttackDamage();
        this.bulletRange = tower.getAttackRange();*/
        bullet =new ImageView(new Image("/image/Bullet/normal.png"));
    }
    public Bullet (){
        super();
        super.setView(new ImageView(new Image("/image/Bullet/normal.png")));
    }



    public int getBulletDamage()
    {
        return bulletDamage;
    }

    public void setBulletDamage(int bulletDamage)
    {
        this.bulletDamage = bulletDamage;
    }

    public int getBulletRange()
    {
        return bulletRange;
    }

    public void setBulletRange(int bulletRange)
    {
        this.bulletRange = bulletRange;
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

    /*public void target(Tower tower)
    {
        setBulletTarget(tower.getAttackTarget());
    }*/

    //bullet trace method goes here, i
    public ImageView getBullet(){
        return bullet;
    }

}
