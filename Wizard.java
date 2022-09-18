import java.util.Random;

public class Wizard extends Enemy implements Magical{

    /** 
     * Default constructor of Wizard enemy
     * @param n the name of the enemy
     * @param mHp the max HP of the enemy
     */
    public Wizard(String n, int mHp){
        super(n, mHp);
    }

    /** 
     * Randomly select one of the enemy's abilities to attack the Hero with
     * @param h the targeted hero object 
     * @return attk that is chosen
     */
    @Override
    public String attack(Hero h){
        Random rand = new Random();
        String attk = " ";
        int a = rand.nextInt(2);
        if (a == 0){
            attk = magicMissile(h);
            return attk;
        }else if (a == 1){
            attk = fireball(h);
        }
      return attk;
    }

    /** 
     * Deal a random damage from the enemy's abilities to the Hero
     * @param e the targeted Entity object 
     * @return attk string display the description of the attack.
     */
    @Override
    public String magicMissile(Entity e){
        Random rand = new Random();
        String n = getName();
        int d = rand.nextInt((5-1)+1)+1;
        e.takeDamage(d);
        String attk = n + " zaps " + e.getName() + " with a Magic Missile for " + d + " damage.";
        return attk;
    }

    /** 
     * Deal a random damage from the enemy's abilities to the Hero
     * @param e the targeted Entity object 
     * @return attk string display the description of the attack.
     */
    @Override
    public String fireball(Entity e){
        Random rand = new Random();
        String n = getName();
        int d = rand.nextInt((5-1)+1)+1;
        e.takeDamage(d);
        String attk = n + " shoots " + e.getName() + " with a Fireball for " + d + " damage.";
        return attk;
    }
}

