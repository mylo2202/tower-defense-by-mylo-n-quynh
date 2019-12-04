package game.characters.Enemies;

import game.characters.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class TankerEnemy extends Enemy
{
    public TankerEnemy() throws IOException {
        setImageUrl("/Image/Enemy/tankerEnemy.png");
        setEnemyImage(new Image(this.getImageUrl(), getEnemyRoad().getGRID_SIZE(), getEnemyRoad().getGRID_SIZE(), false, true));
        setView(new ImageView(this.getEnemyImage()));
        setHitPoints(1000);
        setMoveDuration(36);
        setReward(20);
        setLevel(10);
        setDamage(5);
    }
}
