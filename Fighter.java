public interface Fighter{
    public static final String FIGHTER_MENU = "1. Sword\n2. Axe";
    public static final int NUM_FIGHTER_MENU_ITEMS = 2;

    /** 
     * Deal a random damage from the enemy's abilities to the Hero
     * @param e the targeted Entity object 
     */    
    public String sword (Entity e);

    /** 
     * Deal a random damage from the enemy's abilities to the Hero
     * @param e the targeted Entity object 
     */    
    public String axe (Entity e);
}
