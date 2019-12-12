package game.characters.Enemies;

import game.characters.Enemy;
import game.drawers.TileMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public final class NormalEnemy extends Enemy
{
    public NormalEnemy() throws IOException {
        setTowerImgUrl("/Image/Enemy/normalEnemy.png");
        setEnemyImage(new Image(this.getTowerImgUrl(), TileMap.getGRID_SIZE(), TileMap.getGRID_SIZE(), false, true));
        setTowerView(new ImageView(this.getEnemyImage()));
        setHitPoints(200);
        setMoveDuration(24);
        setReward(10);
        setLevel(2);
        setDamage(2);
    }
}