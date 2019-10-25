package game.characters;

import game.characters.*;

public class Hill extends GameTile{
    public Hill(int posX, int posY, int width, int height)
    {
        super(posX, posY, width, height);
    }

    public boolean isTouched(GameEntity entity)
    {
        return entity.getPosX() == this.getPosX() || entity.getPosY() == this.getPosY() || entity.getWidth() == this.getWidth() || entity.getHeight() == this.getHeight();
    }

}
