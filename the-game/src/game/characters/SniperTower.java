package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SniperTower extends Tower{
    public SniperTower() throws IOException
    {
        setTowerSkin("/Image/Tower/sniperTower.png");
        setTowerImage(new Image(this.getTowerSkin(), getTowerHill().getGRID_SIZE(), getTowerHill().getGRID_SIZE(), false, true));
        setTowerView(new ImageView(this.getTowerImage()));
        setAttackDamage(200);
        setAttackRange(360);
        setAttackCooldown(2000);
        setBuildCost(250);
        setUpgradeCost(250);
        setSellPrice(125);
    }

    //upgrade tower method goes here
}
