package game.characters.Enemies;

import game.characters.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public final class NormalEnemy extends Enemy
{
    public NormalEnemy() throws IOException {
        setTowerImgUrl("/Image/Enemy/normalEnemy.png");
        setEnemyImage(new Image(this.getTowerImgUrl(), getEnemyRoad().getGRID_SIZE(), getEnemyRoad().getGRID_SIZE(), false, true));
        setTowerView(new ImageView(this.getEnemyImage()));
        setHitPoints(200);
        setMoveDuration(24);
        setReward(10);
        setLevel(2);
        setDamage(2);
    }
}