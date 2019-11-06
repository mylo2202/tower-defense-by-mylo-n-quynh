package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SmallerEnemy extends Enemy
{
    public SmallerEnemy(int posX, int posY)
    {
        super(posX, posY);

        setHitPoints(100);
        setMoveSpeed(200);
        setReward(50);
    }

    public ImageView loadSkin(){
        Image image = new Image("/Image/Enemy/smallerEnemy.png",64,64 ,false,true);
        return new ImageView(image);
    }
}
