package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SniperTower extends Tower{
    public SniperTower()
    {
        setTowerSkin("/Image/Tower/sniper.png");
        setTowerImage(new Image(this.getTowerSkin(), 80, 80, false, true));
        setTowerView(new ImageView(this.getTowerImage()));
        setAttackDamage(200);
        setAttackRange(360);
        setAttackSpeed(5);
        setBuildCost(250);
        setUpgradeCost(250);
        setSellPrice(125);
    }

    //upgrade tower method goes here
}