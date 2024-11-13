package droids;
import java.util.Random;

public class SniperDroid extends Droid{
    public SniperDroid(String name, int health, int damage){
        super(name, health, damage);
    }

    public SniperDroid() {
        this.type = "Sniper";
        this.skill = "Накопичення здоров'я";
    }

    public SniperDroid(String name){
        super(name);
        Random rand = new Random();
        this.health = rand.nextInt(100-90) + 90;
        this.damage = rand.nextInt(70-65) + 65;
        this.type = "Sniper";
        this.skill = "Накопичення здоров'я";
    }

    @Override
    public void useSkill() {
        int addedHealth = (int)Math.round(damage * 0.15);
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
