package game.characters.Enemies;

import game.characters.Enemy;
import game.drawers.TileMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public final class TankerEnemy extends Enemy
{
    public TankerEnemy() throws IOException {
        setTowerImgUrl("/Image/Enemy/tankerEnemy.png");
        setEnemyImage(new Image(this.getTowerImgUrl(), TileMap.getGRID_SIZE(), TileMap.getGRID_SIZE(), false, true));
        setTowerView(new ImageView(this.getEnemyImage()));
        setHitPoints(1000);
        setMoveDuration(36);
        setReward(20);
        setLevel(10);
        setDamage(5);
    }
}
