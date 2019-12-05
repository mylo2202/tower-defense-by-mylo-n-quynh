package game.characters.Towers;

import game.characters.Tower;
import game.drawers.TileMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MachineGunTower extends Tower {
    public MachineGunTower() throws IOException
    {
        setTowerImgUrl("/Image/Tower/machineGunTower.png");
        setTowerImage(new Image(this.getTowerImgUrl(), TileMap.getGRID_SIZE(), TileMap.getGRID_SIZE(), false, true));
        setTowerView(new ImageView(this.getTowerImage()));
        setAttackDamage(25);
        setAttackCooldown(250);
        setBuildCost(100);
        setUpgradeCost(getBuildCost() + (getBuildCost() / 4) * getTowerLevel());
        setSellPrice(50);
        setAttackRange(120);
        setContextMenu();
    }
}
