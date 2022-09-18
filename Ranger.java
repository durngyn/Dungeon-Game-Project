
import java.util.Random;

public class Ranger extends Enemy implements Archer{

    /** 
     * Default constructor of Ranger enemy
     * @param n the name of the enemy
     * @param mHp the max HP of the enemy
     */    
    public Ranger(String n, int mHp){
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
            attk = arrow(h);
        }else if (a == 1){
            attk = fireArrow(h);
        }
        return attk;
    }

    /** 
     * Deal a random damage from the enemy's abilities to the Hero
     * @param e the targeted Entity object 
     * @return attk string display the description of the attack.
     */
    @Override
    public String arrow(Entity e){
        Random rand = new Random();
        String n = getName();
        int d = rand.nextInt(5)+1;
        e.takeDamage(d);
        String attk = n + " shoots " + e.getName() + " with an arrow for " + d + " damage.";
        return attk;
    }

    /** 
     * Deal a random damage from the enemy's abilities to the Hero
     * @param e the targeted Entity object 
     * @return attk string display the description of the attack.
     */    
    @Override
    public String fireArrow(Entity e){
        Random rand = new Random();
        String n = getName();
        int d = rand.nextInt(5)+1;
        e.takeDamage(d);
        String attk = n + " shoots " + e.getName() + " with a fire arrow for " + d + " damage.";
        return attk;
    }
}
