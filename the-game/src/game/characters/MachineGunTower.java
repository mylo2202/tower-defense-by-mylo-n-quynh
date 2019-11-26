package game.characters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MachineGunTower extends Tower {
    public MachineGunTower() throws IOException
    {
        setImageUrl("/Image/Tower/machineGunTower.png");
        setTowerImage(new Image(this.getImageUrl(), getTowerHill().getGRID_SIZE(), getTowerHill().getGRID_SIZE(), false, true));
        setView(new ImageView(this.getTowerImage()));
        setAttackDamage(25);
        setAttackRange(120);
        setAttackCooldown(250);
        setBuildCost(70);
        setUpgradeCost(50);
        setSellPrice(25);
      /*  bullets.forEach(bullet -> {
            bullet.setImageUrl("/Image/Bullet/bullet1.png");
        });*/
    }
   /* public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }*/
}
