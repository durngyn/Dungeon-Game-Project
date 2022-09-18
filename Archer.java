
public interface Archer{
    public static final String ARCHER_MENU = "1. Arrow\n2. Fire Arrow";
    public static final int NUM_ARCHER_MENU_ITEMS = 2;

    /** 
     * Deal a random damage from the enemy's abilities to the Hero
     * @param e the targeted Entity object 
     */
    public String arrow (Entity e);

    /** 
     * Deal a random damage from the enemy's abilities to the Hero
     * @param e the targeted Entity object 
     */
    public String fireArrow (Entity e);
}

