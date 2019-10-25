package game.characters;

import game.characters.*;

//this class manages everything that doesn't move
//including Towers, Hills, and Roads

abstract class GameTile extends GameEntity {
    GameTile(int posX, int posY, int width, int height)
    {
        super(posX, posY, width, height);
    }

    //any other methods? idk
}
