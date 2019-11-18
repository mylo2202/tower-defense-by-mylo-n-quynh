package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MachineGunTower extends Tower {
    public MachineGunTower() throws IOException
    {
        setTowerSkin("/Image/Tower/machineGunTower.png");
        setTowerImage(new Image(this.getTowerSkin(), getTowerHill().getGRID_SIZE(), getTowerHill().getGRID_SIZE(), false, true));
        setTowerView(new ImageView(this.getTowerImage()));
        setAttackDamage(25);
        setAttackRange(120);
        setAttackCooldown(250);
        setBuildCost(50);
        setUpgradeCost(50);
        setSellPrice(25);
    }

    //upgrade tower method goes here
}
