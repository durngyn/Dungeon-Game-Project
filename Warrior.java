
import java.util.Random;

public class Warrior extends Enemy implements Fighter{
    
    /** 
     * Default constructor of Warrior enemy
     * @param n the name of the enemy
     * @param mHp the max HP of the enemy
     */
    public Warrior(String n, int mHp){
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
        String attk = "";
        int a = rand.nextInt(2);
        if (a == 0){
            attk = sword(h);
        }else if (a == 1){
            attk = axe(h);
        }
        return attk;
    }
    
    /** 
     * Deal a random damage from the enemy's abilities to the Hero
     * @param e the targeted Entity object 
     * @return attk string display the description of the attack.
     */
    @Override
    public String sword(Entity e){
      
        Random rand = new Random();
        String n = getName();
        int d = rand.nextInt((5))+1;
        e.takeDamage(d);
        return n + " slashes " + e.getName() + " for " + d + " damage.";
    }

    /** 
     * Deal a random damage from the enemy's abilities to the Hero
     * @param e the targeted Entity object 
     * @return attk string display the description of the attack.
     */
    @Override
    public String axe(Entity e){
        Random rand = new Random();
        String n = getName();
        int d = rand.nextInt((5))+1;
        e.takeDamage(d);
        return n + " hatches " + e.getName() + " for " + d + " damage.";
    }
}
