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
        setAttackDamage(10);

        setAttackCooldown(0.25);
        setBuildCost(100);
        setUpgradeCost(100);
        setSellPrice(50);
        setAttackRange(160);

    }
}
