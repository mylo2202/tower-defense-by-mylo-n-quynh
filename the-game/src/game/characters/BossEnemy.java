package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class BossEnemy extends Enemy
{
    public BossEnemy() throws IOException {
        setEnemySkin("/Image/Enemy/bossEnemy.png");
        setEnemyImage(new Image(this.getEnemySkin(), 64, 64, false, true));
        setEnemyView(new ImageView(this.getEnemyImage()));
        setHitPoints(5000);
        setMoveSpeed(25);
        setReward(1000);
        setLevel(50);
    }
}
