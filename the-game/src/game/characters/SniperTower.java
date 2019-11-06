package game.characters;

import game.characters.*;

public class SniperTower extends Tower{
    public SniperTower(int posX, int posY)
    {
        super(posX, posY);

        setAttackDamage(80);
        setAttackRange(200);
        setAttackSpeed(25);
        setTowerLevel(1);
        setBuildCost(300);
        setUpgradeCost(250);
        setSellPrice(150);
    }

    public void setTower(int towerLevel) {
        switch (towerLevel) {
            case 1: {
                setAttackDamage(80);
                setAttackRange(200);
                setAttackSpeed(25);
            }

            case 2: {
                setAttackDamage(80);
                setAttackRange(300);
                setAttackSpeed(25);
            }

            case 3: {
                setAttackDamage(160);
                setAttackRange(300);
                setAttackSpeed(25);
            }

            case 4: {
                setAttackDamage(160);
                setAttackRange(300);
                setAttackSpeed(50);
            }
        }
    }

    //add a method that causes attacks splashes at and slows enemies at level 4 here
}
