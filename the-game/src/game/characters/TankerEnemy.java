package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class TankerEnemy extends Enemy
{
    private String tankerSkin= "/Image/Enemy/tankerEnemy.png";

    public TankerEnemy(String tankerSkin) throws IOException {
        super(tankerSkin);

        setHitPoints(600);
        setMoveSpeed(50);
        setReward(125);
    }

    public ImageView loadSkin(){
        Image image = new Image("/Image/Enemy/tankerEnemy.png",64,64 ,false,true);
        return new ImageView(image);
    }
}
