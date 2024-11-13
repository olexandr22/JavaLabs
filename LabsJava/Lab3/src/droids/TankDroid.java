package droids;
import java.util.Random;

public class TankDroid extends Droid {
    public TankDroid(String name, int health, int damage) {
        super(name, health, damage);
    }

    public TankDroid() {
        this.type = "Tank";
        this.skill = "Накопичення шкоди";
    }

    public TankDroid(String name) {
        super(name);
        Random rand = new Random();
        this.health = rand.nextInt(200-175) + 175;
        this.damage = rand.nextInt(30-25) + 25;
        this.type = "Tank";
        this.skill = "Накопичення шкоди відносно здоров'я";
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void useSkill() {
        int addedDamage = (int)Math.round((250-health) * 0.15);
        damage += addedDamage;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", type='" + type + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
