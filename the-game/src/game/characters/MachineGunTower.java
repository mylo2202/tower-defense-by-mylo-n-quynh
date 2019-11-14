package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MachineGunTower extends Tower {
    public MachineGunTower()
    {
        setTowerSkin("src/Image/Tower/machineGunTower");
        setTowerImage(new Image(this.getTowerSkin(), 80, 80, false, true));
        setTowerView(new ImageView(this.getTowerImage()));
        setAttackDamage(25);
        setAttackRange(120);
        setAttackSpeed(40);
        setBuildCost(50);
        setUpgradeCost(50);
        setSellPrice(25);
    }

    //upgrade tower method goes here
}
