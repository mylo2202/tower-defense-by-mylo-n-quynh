package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TankerEnemy extends Enemy
{
    public TankerEnemy(int posX, int posY)
    {
        super(posX, posY);

        setHitPoints(600);
        setMoveSpeed(50);
        setReward(125);
    }

    public ImageView loadSkin(){
        Image image = new Image("/Image/Enemy/tankerEnemy.png",64,64 ,false,true);
        return new ImageView(image);
    }
}
