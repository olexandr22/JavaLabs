package droids;

import java.io.Serializable;

public class Droid implements Serializable {
    protected String name;
    protected int health;
    protected int damage;
    protected String type = "Default";
    protected String skill = "None";

    public Droid() {
    }

    public Droid(String name) {
        this.name = name;
    }

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public String getSkill() {
        return skill;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void attack(Droid enemy){
        System.out.println(this.name + " hits " + enemy.name + " by " + this.damage);
        enemy.health -= this.damage;
        if(enemy.health < 0){
            enemy.health = 0;
        }
    };

    public void useSkill() {
    }

    @Override
    public String toString() {
        return "Droid{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage;
    }
}
