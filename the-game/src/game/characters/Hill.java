package game.characters;

import game.characters.*;

public class Hill extends GameTile{
    public Hill(int posX, int posY)
    {
        super(posX, posY);
    }

    public boolean isTouched(GameEntity entity)
    {
        return entity.getPosX() == this.getPosX() || entity.getPosY() == this.getPosY();
    }

}
