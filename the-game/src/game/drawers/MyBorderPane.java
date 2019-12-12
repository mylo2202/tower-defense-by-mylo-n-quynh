package game.drawers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MyBorderPane extends BorderPane {
    private final VBox vBox = new VBox();

    public MyBorderPane() {
        super();
        this.vBox.setSpacing(50);
        this.vBox.setPadding(new Insets(5, 5, 5, 5));
        this.vBox.setAlignment(Pos.CENTER);
        setCenter(vBox);
    }

    public void addButton(MyButton button) {
        vBox.getChildren().add(button);
    }

}
