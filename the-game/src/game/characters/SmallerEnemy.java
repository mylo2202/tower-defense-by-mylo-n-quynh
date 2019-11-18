package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SmallerEnemy extends Enemy
{
    public SmallerEnemy() throws IOException {
        setEnemySkin("/Image/Enemy/smallerEnemy.png");
        setEnemyImage(new Image(this.getEnemySkin(), getEnemyRoad().getGRID_SIZE(), getEnemyRoad().getGRID_SIZE(), false, true));
        setEnemyView(new ImageView(this.getEnemyImage()));
        setHitPoints(100);
        setMoveDuration(10);
        setReward(25);
        setLevel(1);
    }
}
