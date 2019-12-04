package game.characters.Enemies;

import game.characters.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class NormalEnemy extends Enemy
{
    public NormalEnemy() throws IOException {
        setImageUrl("/Image/Enemy/normalEnemy.png");
        setEnemyImage(new Image(this.getImageUrl(), getEnemyRoad().getGRID_SIZE(), getEnemyRoad().getGRID_SIZE(), false, true));
        setView(new ImageView(this.getEnemyImage()));
        setHitPoints(200);
        setMoveDuration(24);
        setReward(10);
        setLevel(2);
        setDamage(2);
    }
}