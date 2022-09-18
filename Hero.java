
import java.awt.Point;
import java.util.Random;

public class Hero extends Entity implements Fighter, Magical, Archer{
    private Point loc;
    private int level;
    private int gold;
    private int keys;
    private int potions;

    /** 
     * Determines hero object attributes
     * @param n the name of the Hero
     * @param mHp the max Hp of the Hero
     */
    public Hero(String n, int mHp){
      super(n, mHp);
      level = 1;
      Map m = Map.getInstance();
      loc = m.findStart();
      gold = 25;
      potions = 1;
      keys = 0;
    }

  /** 
    * Return hero stats
    * @return string of hero stats
    */
    @Override
    public String toString(){
        return super.toString() + "\nLevel: " + level + "\nGold: " + gold + "\nP: " + potions + " K: " + keys;
    }

  /** 
    * Return hero's current location
    * @return loc of hero
    */
    public Point getLocation(){
      return loc;
    }

    /** 
      * Increment the level attribute
      */
    public void levelUp(){
      level++;
    }
  
    /** Return the user's direction input 
    * @param h to be moved 
    * @return input_choice to determine next move
    */
    public int getLevel(){
      return level;
    }

  /** Updates the hero's location to the north
    * @return char of new Point of the map
    */
    public char goNorth(){
      loc.x -= 1;  
      Map m = Map.getInstance();
      char room = m.getCharAtLoc(this.loc);
      m.reveal(this.loc);
      return room;
    }

    /** Updates the hero's location to the south
      * @return char of new Point of the map
      */
    public char goSouth(){
      loc.x += 1;
      Map m = Map.getInstance();
      char room = m.getCharAtLoc(this.loc);
      m.reveal(this.loc);
      return room;
    }

    /** Updates the hero's location to the east
    * @return char of new Point of the map
    */
    public char goEast(){
      loc.y += 1;
      Map m = Map.getInstance();
      char room = m.getCharAtLoc(this.loc);
      m.reveal(this.loc);
      return room;
    }

    /** Updates the hero's location to the west
    * @return char of new Point of the map
    */
    public char goWest(){
      loc.y -= 1;
      Map m = Map.getInstance();
      char room = m.getCharAtLoc(this.loc);
      m.reveal(this.loc);
      room = m.getCharAtLoc(this.loc);
      return room;
    }

    /** Return attack menu types
    * @return string of attack menu
    */
    public String getAttackMenu(){
      return "1. Physical \n2. Magical \n3. Ranged";
    }

    /** Return number of attack types
    * @return int of attack menu items
    */
    public int getNumAttackMenuItems(){
      return 3;
    }

    /** Return number of attack types
    * @param choice to be compared
    * @return int of attack menu items
    */
    public String getSubNumAttackMenu(int choice){
      if (choice == 1){
        return Fighter.FIGHTER_MENU;
      }
      else if (choice == 2){
        return Magical.MAGIC_MENU;
      }
      else {
        return Archer.ARCHER_MENU;
      }
    }

    /** Return number of attack types
    * @param choice to be compared
    * @return int of attack menu items
    */
    public int getNumSubNumAttackMenuItems(int choice){
      if (choice == 1){
        return Fighter.NUM_FIGHTER_MENU_ITEMS;
      }
      else if (choice == 2){
        return Magical.NUM_MAGIC_MENU_ITEMS;
      }
      else {
        return Archer.NUM_ARCHER_MENU_ITEMS;
      }
    }
  
    /** Prints string of sword damage to Enemy object
    * @param Entity e enemy object
    * @return String of attack description
    */
    public String sword(Entity e){
      Random rand = new Random();
      String n = getName();
      int d = rand.nextInt(5)+1;
      e.takeDamage(d);
      return n + " slashes " + e.getName() + " for " + d + " damage.";
    }

   /** Prints string of axe damage to Enemy object
    * @param Entity e enemy object
    * @return String of attack description
    */
    public String axe(Entity e){
      Random rand = new Random();
      String n = getName();
      int d = rand.nextInt(5)+1;
      e.takeDamage(d);
      return n + " hatches " + e.getName() + " for " + d + " damage.";
    }

    /** Prints string of magicMissile damage to Enemy object
    * @param Entity e enemy object
    * @return String of attack description
    */
    public String magicMissile(Entity e){
      Random rand = new Random();
      String n = getName();
      int d = rand.nextInt(5)+1;
      e.takeDamage(d);
      return n + " shoots " + e.getName() + " with a Magic Missile for " + d + " damage.";
    }

    /** Prints string of fireball damage to Enemy object
    * @param Entity e enemy object
    * @return String of attack description
    */
    public String fireball(Entity e){
      Random rand = new Random();
      String n = getName();
      int d = rand.nextInt(5)+1;
      e.takeDamage(d);
      return  n + " shoots " + e.getName() + " with a Fireball for " + d + " damage.";
    }

    /** Prints string of arrow damage to Enemy object
    * @param Entity e enemy object
    * @return String of attack description
    */
    public String arrow(Entity e){
      Random rand = new Random();
      String n = getName();
      int d = rand.nextInt(5)+1;
      e.takeDamage(d);
      return n + " shoots " + e.getName() + " with an arrow for " + d + " damage.";
    }

    /** Prints string of  fireArrow damage to Enemy object
    * @param Entity e enemy object
    * @return String of attack description
    */
    public String fireArrow(Entity e){
      Random rand = new Random();
      String n = getName();
      int d = rand.nextInt(5)+1;
      e.takeDamage(d);
      return n + " shoots " + e.getName() + " with a fire arrow for " + d + " damage.";
    }

    /** Returns String of attack type and subchoice
    * @param Enemy e object
    * @param choice of attack type
    * @param subchoice of attack based on type
    * @return String of subchoice description based on type
    */
    public String attack(Enemy e, int choice, int subChoice){
      if (choice == 1){
        if (subChoice == 1){
          return sword(e);
        }
        else {
          return axe(e);
        }
      }
      else if (choice == 2){
        if (subChoice == 1){
          return magicMissile(e);
        }
        else {
          return fireball(e);
        }
      }
      else {
        if (subChoice == 1){
          return arrow(e);
        }
        else {
          return fireArrow(e);
        }
      }
    }

  /** Returns amount of gold
    * @return int of gold
    */
    public int getGold(){
      return gold;
    }

  /** Adds amount of gold passed in into currect gold
    * @param int gold to be added
    */
    public void collectGold(int g){
      gold += g;
    }

    /** Difference of currect gold and gold passed in.
    * @param int gold passed in
    * @return boolean if gold is greater than gold passed in
    */
    public boolean spendGold(int g){
      if (g <= gold){
        gold -= g;
        return true;
      }
      else {
        return false;
      }
    }

    /** Checks if hero has any keys
    * @return boolean of keys avaliable
    */
    public boolean hasKey(){
      if (keys > 0 ){
        return true;
      }
      else{
        return false;
      }
    }

    /** Increments key attribute
    */
    public void pickUpKey(){
      keys++;
    }

  /** Decrements key attribute if keys are avaliable
    * @return boolean of keys avaliable
    */
    public boolean useKey(){
        if (keys > 0 ){
          keys--;
          return true;
        }
        else{
          return false;
        }
    }

   /** Checks if hero has any potions
    * @return boolean of potions avaliable
    */
    public boolean hasPotion(){
        if (potions > 0 ){
          return true;
        }
        else{
          return false;
        }
    }

    /** Increments potion attribute
    */
    public void pickUpPotion(){
        potions++;
    }

  /** Decrements potion attribute
    * @return boolean whether potion can be used
    */
    public boolean usePotion(){
        if (potions > 0 ){
          potions--;
          heal();
          return true;
        }
        else{
          return false;
        }
    }
}