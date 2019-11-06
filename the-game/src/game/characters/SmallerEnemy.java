package game.characters;

import game.characters.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SmallerEnemy extends Enemy {
    private  String URL="/Image/Enemy/smaller.png";
    private ImageView skin;

    public SmallerEnemy(int posX, int posY, int width, int height)
    {


        super(posX, posY, width, height);

        setHitPoints(200);
        setMoveSpeed(100);
        setReward(50);
        Image image =new Image(URL,64,64,false,true);
        skin = new ImageView(image);

    }

    public ImageView getEnemy(){
        return skin;
    }
}
