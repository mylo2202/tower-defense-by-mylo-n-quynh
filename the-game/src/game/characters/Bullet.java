package game.characters;

import game.GameEntity;
import game.characters.*;

public class Bullet extends GameEntity {
    private int bulletDamage;
    private final int bulletSpeed = 1280;
    private Enemy bulletTarget;

    public Bullet(Tower tower)
    {
        this.bulletDamage = tower.getAttackDamage();
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

    //bullet trace method goes here, i think
}
