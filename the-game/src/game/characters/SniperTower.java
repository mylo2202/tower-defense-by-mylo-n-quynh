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
        setAttackCooldown(2);
        setBuildCost(500);
        setUpgradeCost(500);
        setSellPrice(250);
        setAttackRange(400);
    }
}
