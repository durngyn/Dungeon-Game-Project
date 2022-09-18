public abstract class Enemy extends Entity{

    /** 
     * Default constructor of Enemy
     * @param n the name of the enemy
     * @param mHp the max HP of the enemy
     */ 
    public Enemy(String n, int mHp){
        super(n, mHp);
    }

    /** 
     * Randomly select one of the enemy's abilities to attack the Hero with
     * @param h the targeted hero object 
     */
    public abstract String attack (Hero h);
}

