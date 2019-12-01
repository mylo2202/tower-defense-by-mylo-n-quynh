package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class NormalTower extends Tower {
    public NormalTower() throws IOException
    {
        setImageUrl("/Image/Tower/normalTower.png");
        setTowerImage(new Image(this.getImageUrl(), getTowerHill().getGRID_SIZE(), getTowerHill().getGRID_SIZE(), false, true));
        setView(new ImageView(getTowerImage()));
        setAttackDamage(50);
        setAttackRange(200);
        setAttackCooldown(500);
        setBuildCost(200);
        setUpgradeCost(200);
        setSellPrice(100);
        /*bullets.forEach(bullet -> {
            bullet.setImageUrl("/Image/Bullet/bullet1.png");

        });*/
    }
   /* public void setBullet(){
        bullets.forEach(bullet -> {
            bullet.setImageUrl("/Image/Bullet/bullet1.png");

        });
    }
*/
    //upgrade tower method goes here
}
