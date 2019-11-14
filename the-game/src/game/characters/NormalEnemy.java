package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class NormalEnemy extends Enemy
{
    public NormalEnemy() throws IOException {
        setEnemySkin("/Image/Enemy/normalEnemy.png");
        setEnemyImage(new Image(this.getEnemySkin(), 64, 64, false, true));
        setEnemyView(new ImageView(this.getEnemyImage()));
        setHitPoints(200);
        setMoveSpeed(80);
        setReward(50);
        setLevel(2);
    }
}