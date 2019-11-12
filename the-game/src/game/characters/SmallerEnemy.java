package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SmallerEnemy extends Enemy
{
    private String smallerSkin= "/Image/Enemy/smallerEnemy.png";

    public SmallerEnemy(String smallerSkin) throws IOException {
        super(smallerSkin);

        setHitPoints(100);
        setMoveSpeed(200);
        setReward(50);
    }

    public ImageView loadSmallerSkin(){
        Image image = new Image("/Image/Enemy/smallerEnemy.png",64,64 ,false,true);
        return new ImageView(image);
    }
}
