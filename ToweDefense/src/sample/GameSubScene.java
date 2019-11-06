package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class GameSubScene extends SubScene {
    private static final String FONT_PATH="/src/UI/Font/kenvector_future.ttf";
    private static final String BACKGROUND_IMAGE= "/Image/UI/green_panel.png";
    private boolean isHidden;
    private static final int WIDTH=256;
    private static final int HEIGHT=769;
    private static final int POSX=768;

    public GameSubScene() {
        super(new AnchorPane(), WIDTH, HEIGHT);
        prefWidth(WIDTH);
        prefHeight(HEIGHT);
        isHidden =true;
        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,WIDTH,HEIGHT,false,true),
                BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        AnchorPane root= (AnchorPane) this.getRoot();
        root.setBackground(new Background(image));
        setLayoutX(POSX+WIDTH);
        setLayoutY(-1);
    }
    public void moveSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.4));
        transition.setNode(this);
        if( isHidden ){
            transition.setToX(-WIDTH);
            isHidden=false;
        }else {
            transition.setToX(POSX);
            isHidden=true;
        }
        transition.play();
    }
    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
}
