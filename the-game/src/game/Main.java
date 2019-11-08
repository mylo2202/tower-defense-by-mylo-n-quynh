package game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage gameStage) {
        try{
            MenuGame menuGame = new MenuGame();
            gameStage = menuGame.getMainStage();
            gameStage.setTitle("Tower Defense");
            gameStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}
