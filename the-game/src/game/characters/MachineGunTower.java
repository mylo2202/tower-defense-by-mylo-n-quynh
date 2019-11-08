package game.characters;

public class MachineGunTower extends Tower {
    public MachineGunTower(int posX, int posY)
    {
        super();

        setAttackDamage(5);
        setAttackRange(50);
        setAttackSpeed(200);
        setTowerLevel(1);
        setBuildCost(50);
        setUpgradeCost(50);
        setSellPrice(25);
    }

    public void setTower(int towerLevel)
    {
        switch (towerLevel)
        {
            case 1:
            {
                setAttackDamage(5);
                setAttackRange(50);
                setAttackSpeed(200);
            }

            case 2:
            {
                setAttackDamage(10);
                setAttackRange(50);
                setAttackSpeed(200);
            }

            case 3:
            {
                setAttackDamage(10);
                setAttackRange(75);
                setAttackSpeed(200);
            }

            case 4:
            {
                setAttackDamage(10);
                setAttackRange(75);
                setAttackSpeed(400);
            }
        }
    }

    //add a method that causes attacks splashes at and slows enemies at level 4 here
}
