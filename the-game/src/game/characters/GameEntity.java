package game.characters;

import javafx.scene.image.ImageView;

public interface GameEntity {

    ImageView getTowerView();

    void setTowerView(ImageView towerView);

    String getTowerImgUrl();

    void setTowerImgUrl(String towerImgUrl);
}

