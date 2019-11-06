package game.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BossEnemy extends Enemy
{
    public BossEnemy(int posX, int posY)
    {
        super(posX, posY);

        setHitPoints(1600);
        setMoveSpeed(25);
        setReward(350);
    }

    public ImageView loadSkin(){
        Image image = new Image("/Image/Enemy/bossEnemy.png",64,64 ,false,true);
        return new ImageView(image);
    }
}
