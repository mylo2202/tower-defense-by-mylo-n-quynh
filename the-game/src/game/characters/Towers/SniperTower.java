package game.characters.Towers;

import game.characters.Tower;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SniperTower extends Tower {
    public SniperTower() throws IOException
    {
        setImageUrl("/Image/Tower/sniperTower.png");
        setTowerImage(new Image(this.getImageUrl(), getTowerHill().getGRID_SIZE(), getTowerHill().getGRID_SIZE(), false, true));
        setView(new ImageView(this.getTowerImage()));
        setAttackDamage(200);
        setAttackCooldown(2000);
        setBuildCost(500);
        setUpgradeCost(5000);
        setSellPrice(250);
        setAttackRange(360);
    }
}