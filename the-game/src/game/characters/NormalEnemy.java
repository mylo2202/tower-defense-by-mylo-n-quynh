package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class NormalEnemy extends Enemy
{
    public NormalEnemy() throws IOException {
        setEnemySkin("/Image/Enemy/normalEnemy.png");
        setEnemyImage(new Image(this.getEnemySkin(), getEnemyRoad().getGRID_SIZE(), getEnemyRoad().getGRID_SIZE(), false, true));
        setEnemyView(new ImageView(this.getEnemyImage()));
        setHitPoints(200);
        setMoveDuration(20);
        setReward(50);
        setLevel(2);
    }
}