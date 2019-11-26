package game.characters;

import javafx.scene.image.ImageView;

public interface GameEntity {

    ImageView getView();

    void setView(ImageView view);

    String getImageUrl();

    void setImageUrl(String imageUrl);
}

