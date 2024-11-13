package droids;

import java.util.Random;

public class AtackerDroid extends Droid{
    public AtackerDroid(String name, int health, int damage){
        super(name, health, damage);
    }

    public AtackerDroid() {
        this.type = "Atacker";
        this.skill = "Збалансоване накопичення";
    }

    public AtackerDroid(String name){
        super(name);
        Random rand = new Random();
        this.health = rand.nextInt(150-140) + 140;
        this.damage = rand.nextInt(55-50) + 50;
        this.type = "Atacker";
        this.skill = "Збалансоване накопичення";
    }

    @Override
    public void useSkill() {
        int addedHealth = (int)Math.round(damage * 0.05);
        int addedDamage = (int)Math.round((250-health) * 0.05);
        damage += addedDamage;
        health += addedHealth;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", type='" + type + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
