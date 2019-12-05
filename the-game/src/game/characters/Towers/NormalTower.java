package game.characters.Towers;

import game.characters.Tower;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class NormalTower extends Tower {
    public NormalTower() throws IOException
    {
        setImageUrl("/Image/Tower/normalTower.png");
        setTowerImage(new Image(this.getImageUrl(), getTowerHill().getGRID_SIZE(), getTowerHill().getGRID_SIZE(), false, true));
        setView(new ImageView(getTowerImage()));
        setAttackDamage(50);
        setAttackCooldown(500);
        setBuildCost(200);
        setUpgradeCost(2000);
        setSellPrice(100);
        setAttackRange(200);
    }
}
