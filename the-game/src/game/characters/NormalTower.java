package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class NormalTower extends Tower {
    public NormalTower() throws IOException
    {
        setTowerSkin("/Image/Tower/normalTower.png");
        setTowerImage(new Image(this.getTowerSkin(), getTowerHill().getGRID_SIZE(), getTowerHill().getGRID_SIZE(), false, true));
        setTowerView(new ImageView(getTowerImage()));
        setAttackDamage(50);
        setAttackRange(200);
        setAttackCooldown(500);
        setBuildCost(100);
        setUpgradeCost(100);
        setSellPrice(50);
    }

    //upgrade tower method goes here
}
