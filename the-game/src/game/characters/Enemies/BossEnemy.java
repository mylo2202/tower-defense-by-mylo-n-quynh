package game.characters.Enemies;

import game.characters.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class BossEnemy extends Enemy
{
    public BossEnemy() throws IOException {
        setImageUrl("/Image/Enemy/bossEnemy.png");
        setEnemyImage(new Image(this.getImageUrl(), getEnemyRoad().getGRID_SIZE(), getEnemyRoad().getGRID_SIZE(), false, true));
        setView(new ImageView(this.getEnemyImage()));
        setHitPoints(5000);
        setMoveDuration(54);
        setReward(50);
        setLevel(50);
        setDamage(20);
    }
}
