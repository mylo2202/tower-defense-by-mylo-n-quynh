package game.characters;

import game.characters.*;

//this class manages everything that doesn't move
//including Towers, Hills, and Roads

abstract class GameTile extends GameEntity {

    public GameTile(int posX, int posY) {
        super(posX, posY);
    }

    //any other methods? idk
}
