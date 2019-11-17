package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NormalTower extends Tower {
    public NormalTower()
    {
        setTowerSkin("/Image/Tower/normal.png");
        setTowerImage(new Image(this.getTowerSkin(), 80, 80, false, true));
        setTowerView(new ImageView(this.getTowerImage()));
        setAttackDamage(50);
        setAttackRange(200);
        setAttackSpeed(20);
        setBuildCost(100);
        setUpgradeCost(100);
        setSellPrice(50);
    }

    //upgrade tower method goes here
}