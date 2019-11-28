package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SniperTower extends Tower{
    public SniperTower() throws IOException
    {
        setImageUrl("/Image/Tower/sniperTower.png");
        setTowerImage(new Image(this.getImageUrl(), getTowerHill().getGRID_SIZE(), getTowerHill().getGRID_SIZE(), false, true));
        setView(new ImageView(this.getTowerImage()));
        setAttackDamage(200);
        setAttackRange(360);
        setAttackCooldown(15);
        setBuildCost(500);
        setUpgradeCost(500);
        setSellPrice(250);
        setRadius(160);
      /*  bullets.forEach(bullet -> {
            bullet.setImageUrl("/Image/Bullet/bullet1.png");
        });*/
    }

    //upgrade tower method goes here
}
