package game.characters;

import game.characters.*;

public abstract class Tower extends GameTile
{
    private int attackDamage;                       // Amount of health to reduce from enemies per attack
    private double attackSpeed;                     // Delayed time for each attack
    private int attackRange;                        // Maximum range the tower can attack
    private int towerLevel;                         // The higher level the tower is the more effective it is
    private int buildCost;                          // Cost for building
    private int upgradeCost;                        // Cost for upgrading
    private int sellPrice;                          // Gold gained for selling
    private Enemy attackTarget;

    public Tower()
    {
        super();

//        this.attackDamage = attackDamage;
//        this.attackSpeed = attackSpeed;
//        this.attackRange = attackRange;
//        this.towerLevel = towerLevel;
//        this.buildCost = buildCost;
//        this.upgradeCost = upgradeCost;
//        this.sellPrice = sellPrice;
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage)
    {
        this.attackDamage = attackDamage;
    }

    public double getAttackSpeed()
    {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed)
    {
        this.attackSpeed = attackSpeed;
    }

    public int getAttackRange()
    {
        return attackRange;
    }

    public void setAttackRange(int attackRange)
    {
        this.attackRange = attackRange;
    }

    public int getTowerLevel()
    {
        return towerLevel;
    }

    public void setTowerLevel(int towerLevel)
    {
        this.towerLevel = towerLevel;
    }

    public int getBuildCost()
    {
        return buildCost;
    }

    public void setBuildCost(int buildCost)
    {
        this.buildCost = buildCost;
    }

    public int getUpgradeCost()
    {
        return upgradeCost;
    }

    public void setUpgradeCost(int upgradeCost)
    {
        this.upgradeCost = upgradeCost;
    }

    public int getSellPrice()
    {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice)
    {
        this.sellPrice = sellPrice;
    }

    public void upgradeTower()
    {
        this.towerLevel++;
    }

    public Enemy getAttackTarget()
    {
        return attackTarget;
    }

    public void setAttackTarget(Enemy attackTarget)
    {
        this.attackTarget = attackTarget;
    }

    //attack method maybe goes here
}