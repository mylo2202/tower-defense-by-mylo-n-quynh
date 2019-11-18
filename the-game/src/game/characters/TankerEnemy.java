package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class TankerEnemy extends Enemy
{
    public TankerEnemy() throws IOException {
        setEnemySkin("/Image/Enemy/tankerEnemy.png");
        setEnemyImage(new Image(this.getEnemySkin(), getEnemyRoad().getGRID_SIZE(), getEnemyRoad().getGRID_SIZE(), false, true));
        setEnemyView(new ImageView(this.getEnemyImage()));
        setHitPoints(1000);
        setMoveDuration(40);
        setReward(200);
        setLevel(10);
    }
}
