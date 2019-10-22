package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MyBorderPane extends BorderPane {
    private VBox vBox = new VBox();
    private HBox hBox =new HBox();

    public  MyBorderPane(){
        super();
        this.vBox.setSpacing(50);
        this.vBox.setPadding(new Insets(5,5, 5,5));
        this.vBox.setAlignment(Pos.CENTER);
    }
    public void addButton(MyButton button){
        vBox.getChildren().add(button);

    }
    public void setAreasCenter(){
        setCenter(vBox);
    }
    public void setAreasTop(){
        setTop(hBox);
    }
    public void setAreasLeft(){
        setLeft(vBox);
    }
    public void setAreasRight(){
        setRight(vBox);
    }
    public void setAreasBottom(){
        setBottom(hBox);
    }


}
