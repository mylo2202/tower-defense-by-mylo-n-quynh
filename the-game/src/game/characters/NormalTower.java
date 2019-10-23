package game.characters;

import game.characters.*;

public class NormalTower extends Tower {
    public NormalTower(int posX, int posY, int width, int height)
    {
        super(posX, posY, width, height);

        setAttackDamage(10);
        setAttackRange(100);
        setAttackSpeed(100);
        setTowerLevel(1);
        setBuildCost(100);
        setUpgradeCost(100);
        setSellPrice(50);
    }

    public void setTower(int towerLevel)
    {
        switch (towerLevel)
        {
            case 1:
            {
                setAttackDamage(10);
                setAttackRange(100);
                setAttackSpeed(100);
            }

            case 2:
            {
                setAttackDamage(10);
                setAttackRange(100);
                setAttackSpeed(200);
            }

            case 3:
            {
                setAttackDamage(20);
                setAttackRange(100);
                setAttackSpeed(200);
            }

            case 4:
            {
                setAttackDamage(20);
                setAttackRange(150);
                setAttackSpeed(200);
            }
        }
    }

    //add a method that causes attacks splashes at and slows enemies at level 4 here
}
