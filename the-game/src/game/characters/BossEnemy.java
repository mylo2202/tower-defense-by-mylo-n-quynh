package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class BossEnemy extends Enemy
{
    private String bossSkin= "/Image/Enemy/bossEnemy.png";

    public BossEnemy(String bossSkin) throws IOException {
        super(bossSkin);

        setHitPoints(1600);
        setMoveSpeed(25);
        setReward(350);
    }

    public ImageView loadBossSkin(){
        Image image = new Image("/Image/Enemy/bossEnemy.png",64,64 ,false,true);
        return new ImageView(image);
    }
}
