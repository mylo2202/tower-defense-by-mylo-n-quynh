package game.characters.Enemies;

import game.characters.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SmallerEnemy extends Enemy
{
    public SmallerEnemy() throws IOException {
        setTowerImgUrl("/Image/Enemy/smallerEnemy.png");
        setEnemyImage(new Image(this.getTowerImgUrl(), getEnemyRoad().getGRID_SIZE(), getEnemyRoad().getGRID_SIZE(), false, true));
        setTowerView(new ImageView(this.getEnemyImage()));
        setHitPoints(100);
        setMoveDuration(16);
        setReward(5);
        setLevel(1);
        setDamage(1);
    }
}
