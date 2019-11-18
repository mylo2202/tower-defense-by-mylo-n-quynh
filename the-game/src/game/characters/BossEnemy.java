package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class BossEnemy extends Enemy
{
    public BossEnemy() throws IOException {
        setEnemySkin("/Image/Enemy/bossEnemy.png");
        setEnemyImage(new Image(this.getEnemySkin(), getEnemyRoad().getGRID_SIZE(), getEnemyRoad().getGRID_SIZE(), false, true));
        setEnemyView(new ImageView(this.getEnemyImage()));
        setHitPoints(5000);
        setMoveDuration(80);
        setReward(500);
        setLevel(50);
    }
}
