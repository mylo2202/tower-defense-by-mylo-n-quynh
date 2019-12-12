package game.characters.Towers;

import game.characters.Tower;
import game.drawers.TileMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public final class SniperTower extends Tower {
    public SniperTower() {
        setTowerImgUrl("/Image/Tower/sniperTower.png");
        setTowerImage(new Image(this.getTowerImgUrl(), TileMap.getGRID_SIZE(), TileMap.getGRID_SIZE(), false, true));
        setTowerView(new ImageView(this.getTowerImage()));
        setAttackDamage(200);
        setAttackCooldown(2000);
        setBuildCost(500);
        setUpgradeCost(getBuildCost() + (getBuildCost() / 4) * getTowerLevel());
        setSellPrice(250);
        setAttackRange(360);
        setContextMenu();
    }
}
