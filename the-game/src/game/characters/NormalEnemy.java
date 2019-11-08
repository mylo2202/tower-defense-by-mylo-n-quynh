package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NormalEnemy extends Enemy
{
    private String normalSkin= "/Image/Enemy/normalEnemy.png";

    public NormalEnemy(String normalSkin)
    {
        super(normalSkin);

        setHitPoints(200);
        setMoveSpeed(100);
        setReward(50);
        loadSkin();
    }
    public ImageView loadSkin(){
        Image image = new Image("/Image/Enemy/normalEnemy.png",64,64 ,false,true);
        return new ImageView(image);
    }
}