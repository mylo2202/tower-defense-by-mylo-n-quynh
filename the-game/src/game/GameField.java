package game;

import game.*;
import game.characters.*;

public class GameField
{
    private NormalEnemy normalEnemy;
    private GameEntity tower;
    public GameField(){
       // normalEnemy= new NormalEnemy(0,64,64,64);
        tower= new NormalTower(64,64,64,64);
    }
   /* public void enemyMove(){

            normalEnemy.enemyMove(0,64);

    }*/

}