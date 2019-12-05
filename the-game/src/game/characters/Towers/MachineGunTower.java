package game.characters.Towers;

import game.characters.Tower;
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
        setAttackCooldown(250);
        setBuildCost(100);
        setUpgradeCost(1000);
        setSellPrice(50);
        setAttackRange(120);
        setContextMenu();
    }
}
