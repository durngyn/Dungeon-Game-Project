public interface Magical{
    public static final String MAGIC_MENU = "1. Magic Missile\n2. Fireball";
    public static final int NUM_MAGIC_MENU_ITEMS = 2;

    /** 
     * Deal a random damage from the enemy's abilities to the Hero
     * @param e the targeted Entity object 
     */
    public String magicMissile (Entity e);

    /** 
     * Deal a random damage from the enemy's abilities to the Hero
     * @param e the targeted Entity object 
     */    
    public String fireball (Entity e);
}
