package game.characters;

import game.characters.*;

public class Bullet extends GameEntity{
    private int bulletDamage;
    private int bulletRange;
    private final int bulletSpeed = 1000;
    private Enemy bulletTarget;

    public Bullet(Tower tower)
    {
        this.bulletDamage = tower.getAttackDamage();
        this.bulletRange = tower.getAttackRange();
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

    public void target(Tower tower)
    {
        setBulletTarget(tower.getAttackTarget());
    }

    //bullet trace method goes here, i think
}
