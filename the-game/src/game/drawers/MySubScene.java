package game.drawers;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;

public class MySubScene extends SubScene {
    private static final String FONT_PATH = "/src/UI/Font/kenvector_future.ttf";
    private static final String BACKGROUND_IMAGE = "/Image/UI/grey_panel.png";
    // private MyButton build= new MyButton("Build",45,100,"-fx-background-color: transparent; -fx-background-image: url('/Image/UI/life.png');");
    private static final double WIDTH = 250;
    private static final double HEIGHT = 250;
    private static final double POSX = 1050;
    private static final double POSY = 300;
    private MyLabel myLabel = new MyLabel("");
    private Label info = new Label("");
    private boolean isHidden;


    public MySubScene(String s) throws IOException {
        super(new AnchorPane(), WIDTH, HEIGHT);
        setMyLabel(s);
        //   setButtonBuild();
        prefWidth(WIDTH);
        prefHeight(HEIGHT);

        isHidden = true;
        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, WIDTH, HEIGHT, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane root = (AnchorPane) this.getRoot();
        root.setBackground(new Background(image));
        root.getChildren().addAll(myLabel, info);

        setLayoutX(POSX + WIDTH);
        setLayoutY(POSY);
    }

    public Label getInfo() {
        return info;
    }

    public void setInfo(String s) {
        info.setFont(Font.font("Mongoose", 20));
        info.setText(s);
        info.setLayoutX(30);
        info.setLayoutY(80);

    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.4));
        transition.setNode(this);
        if (isHidden) {
            transition.setToX(-WIDTH - 53);
            isHidden = false;
        } else {
            transition.setToX(POSX);
            isHidden = true;
        }
        transition.play();
    }

    public void setMyLabel(String s) {
        this.myLabel.setText(s);
        myLabel.setLayoutX(28);
        myLabel.setLayoutY(10);
        myLabel.setLabelFont("src/Image/UI/AutourOne-Regular.otf", 13);
    }
    /*public void setButtonBuild(){
        build.setLayoutX(28);
        build.setLayoutY(190);
    }*/

    public AnchorPane getAnchorePane() {
        return (AnchorPane) this.getRoot();
    }
   /* public MyButton getBuild(){
        return build;
    }*/
}