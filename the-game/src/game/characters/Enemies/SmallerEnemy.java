package game.characters.Enemies;

import game.characters.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SmallerEnemy extends Enemy
{
    public SmallerEnemy() throws IOException {
        setImageUrl("/Image/Enemy/smallerEnemy.png");
        setEnemyImage(new Image(this.getImageUrl(), getEnemyRoad().getGRID_SIZE(), getEnemyRoad().getGRID_SIZE(), false, true));
        setView(new ImageView(this.getEnemyImage()));
        setHitPoints(100);
        setMoveDuration(8);
        setReward(10);
        setLevel(1);
    }
}
