/*
GROUP #15 - Sec #5
Charlene Tran - 028606667
Darren Nguyen - 025951183
Twan Tran - 029136612
3/27/2022
    
This program involves a user exploring a dungeon maze while also fighting monsters along the way. The attack types of enemies can consist of either warrior, wizard, or ranger while the hero has access to all three types. Throughout the journey, the hero can obtain gold, increase his/her level, and purchase items. The game ends once the user quits or dies. 

Work Split: 
Charlene - main, menu, hero, map, monster room, fight
Darren - hero, enemy generator, map, main, store, menu
Twan - Map, Ranger, Warrior, Wizard, Magical, Archer, Fighter, Entity, main
*/

import java.util.*;
import java.awt.Point; 

class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    
    System.out.print("\nWhat is your name, traveler? ");
    
    String name = in.nextLine();
    Map m1 = Map.getInstance();
    Map m2 = Map.getInstance();
    Map m3 = Map.getInstance();
    m1.loadMap(1);
    
    EnemyGenerator eg = new EnemyGenerator();
    ArrayList<Map> maps = new ArrayList<Map>();
    maps.add(m1);
    maps.add(m2);
    maps.add(m3);

    //initial level
    int user_level = 1;
    int level = user_level;
    int cycle = 0;
    Point p = new Point();

    boolean game_done = false;

    //have map # load once
    boolean used_1 = false;
    boolean used_2 = false;
    boolean used_3 = false;
    
    Hero h = new Hero(name, 25);
    maps.get(0).mapToString(h.getLocation());
    
    //loop the game until the user quits or dies
    while(!game_done) {
      if(user_level <= 3){
        level = user_level;
      }
      if(cycle != 0 && user_level > 3){
        level = user_level - (3 * cycle); // user 4 cycle 0 4-3
      }
      if (level == 1 && !used_1 && cycle != 0){
        m1.loadMap(1);
        used_1 = true;
        m1.findStart();
      }
      if (level == 2 && !used_2){
        m2.loadMap(2);
        used_2 = true;
        m2.findStart();
      }
      if (level == 3 && !used_3){
        m2.loadMap(3);
        used_3 = true;
        m3.findStart();
      }
      
      System.out.println(h);
      System.out.println(maps.get(0).mapToString(h.getLocation())); 
      int input_choice = mainMenu(h);
      if(input_choice == 5){
        System.out.println("\nScore: " + user_level + "\nGame Over ");
        game_done = true;
      }
      user_level = h.getLevel();
      if (level > 3 || (user_level > 3 && cycle == 0)){
        used_1 = false;
        used_2 = false;
        used_3 = false;
        cycle++;
      }
    }
  }
  
   /**
    * Return the user's direction input 
    * @param h to be moved 
    * @return input_choice to determine next move
    */
  public static int mainMenu(Hero h){
    System.out.println("1. Go North (^)\n2. Go South (v)\n3. Go East  (>)\n4. Go West  (<)\n5. Quit ");
    Scanner input = new Scanner(System.in);

    //create constants for main menu range
    int MIN_MAIN_MENU_INPUT = 1;
    int MAX_MAIN_MENU_INPUT = 5;
    
    boolean inputValid = false;
    int input_choice = 0;
    
    //checking for user choice validity
    while(!inputValid) {
        if(input.hasNextInt()) {
			input_choice = input.nextInt();
			inputValid = true;
          if(input_choice < MIN_MAIN_MENU_INPUT || input_choice > MAX_MAIN_MENU_INPUT){
    			inputValid = false;
    			System.out.println( "Invalid Range." );
          }
        }
        else {
          input.next(); 
          System.out.println( "Invalid Input." );
        }
    }
    //allow user to move in a direction
    char room = ' ';
    try{
      if(input_choice == 1){
        room = h.goNorth(); 
      }
      if(input_choice == 2){
        room = h.goSouth();
      }
      if(input_choice == 3){
        room = h.goEast();
      }
      if(input_choice == 4){
        room = h.goWest();
      }
    }
    catch(ArrayIndexOutOfBoundsException exc){
      System.out.println("You walked into a wall.");
      if(input_choice == 2){
        room = h.goNorth();
      }
      if(input_choice == 1){ 
        room = h.goSouth();
      }
      if(input_choice == 4){
        room = h.goEast();
      }
      if(input_choice == 3){
        room = h.goWest();
      }
      room = ' ';
    }
    
    //check if room is a special character, then perform specific actions
    if (room == 'm'){
      Enemy e = EnemyGenerator.generateEnemy(h.getLevel());
      boolean hero_alive = monsterRoom(h, e);
      if(hero_alive == false){
        System.out.println("You died!");
        input_choice = 5;
      }
    }
    else if (room == 'i'){
      int item = (int)(Math.random()*2)+1;
      Map m = Map.getInstance();
      m.removeCharAtLoc(h.getLocation());
      if (item == 1){
        h.pickUpKey();
        System.out.println("You found a key!");
      }
      else{
        h.pickUpPotion();
        System.out.println("You found a potion!");
      }
    }
    else if (room == 'f'){
       System.out.print("You found a locked gate.");
      if (h.hasKey() == true){
        System.out.println(" Luckily you have a key. You proceed to the next area.");
        h.useKey();
        h.levelUp();
      }
      else{
        System.out.print(" However, you do not have a key.\n");
      }
    }
      else if (room == 's'){
        store(h);
      }
      else if (room == 'n'){
        System.out.println("The room is empty...");
      }
    return input_choice;
  }
  
   /**
    * Hero decisions when encountering a monster
    * @param h to fight, run away, or heal
    * @param e to display the enemy name
    * @return true if the Hero is still alive after the encounter
    */
  public static boolean monsterRoom(Hero h, Enemy e){
    System.out.println("You've encountered a " + e);
    
    Scanner decision = new Scanner(System.in);
    
    //create constants for decision range
    int MIN_DECISION_INPUT = 1;
    int MAX_DECISION_INPUT = 3;
    
    boolean decisionValid = false;
    int decision_choice = 0;

    boolean results = true;
    
    while(h.getHp() != 0 && e.getHp() != 0)  {
      if(h.hasPotion() == true){
        System.out.println("1. Fight\n2. Run Away\n3. Drink Potion");
      }
      else  {
        System.out.println("1. Fight\n2. Run Away");
        MAX_DECISION_INPUT = 2;
      }   
      //checking for user decision validity
      while(!decisionValid) {
        if(decision.hasNextInt()) {
  			  decision_choice = decision.nextInt();
  			  decisionValid = true;
          if(decision_choice < MIN_DECISION_INPUT || decision_choice > MAX_DECISION_INPUT){
  				  decisionValid = false;
  				  System.out.println( "Invalid Range." );
          }
        }
        else {
  			  decision.next(); 
  			  System.out.println( "Invalid Input." );
        }
      }  
      //user chooses to fight
      if(decision_choice == 1)  {
        boolean monster_alive = fight(h, e);
        decisionValid = false;
        if(monster_alive == false){
          //user won the fight
          results = true;
          Map m = Map.getInstance();
          m.removeCharAtLoc(h.getLocation());
        }
        else{
          //user is dead
          results = false;
        }
      }
      //user chooses to run away in random direction
      else if (decision_choice == 2)  {
        int rand_direction = 0;
        boolean in_bounds = true;
        char room = ' ';
        while(in_bounds){
          in_bounds = false;
          rand_direction = (int)(Math.random() * 4) + 1;
          try{
            if(rand_direction == 1){
              room = h.goNorth();
            }
            else if(rand_direction == 2){
              room = h.goSouth();
            }
            else if(rand_direction == 3){
              room = h.goEast();
            }
            else{
              room = h.goWest();
            }
   
          }
          catch(ArrayIndexOutOfBoundsException exc){
            decisionValid = false;
            in_bounds = true; 
            if(rand_direction == 2){
              h.goNorth();
            }
            else if(rand_direction == 1){
              h.goSouth();
            }
            else if(rand_direction == 4){
              h.goEast();
            }
            else{
              h.goWest();
            }
            continue;
          }
        System.out.println("You ran away...");
         if (room == 'm'){
          monsterRoom(h, e);
          }
        else if (room == 'i'){
          int item = (int)(Math.random() * 1)+1;
          if (item == 1){
            h.pickUpKey();
            System.out.println("You found a key!");
          }
          else{
            h.pickUpPotion();
            System.out.println("You found a potion!");
          }
          Map m = Map.getInstance();
          m.removeCharAtLoc(h.getLocation());
        }
        else if (room == 'f'){
           System.out.print("You found a locked gate.");
          if (h.hasKey() == true){
            System.out.println(" Luckily you have a key. You proceed to the next area.");
            h.levelUp();
            h.useKey();
          }
          else{
            System.out.print(" However, you do not have a key.\n");
          }
        }
          else if (room == 's'){
            store(h);
          }
          else if (room == 'n'){
            System.out.println("The room is empty...");
          }
        results = true;
        return results;
      }
    }
      //user uses potion
      else  {
        System.out.println("You drink the potion... Your health replenished.");
        h.usePotion();
        decisionValid = false;
      }
    }
    return results;
  }
  
  /** 
    * Hero fights a monster
    * @param h to fight
    * @param e to fight back
    * @return true if the monster is alive after the fight
    */
  public static boolean fight(Hero h, Enemy e){

    boolean monster_alive = true;
	  
    // displays types menu
    System.out.println(h.getAttackMenu()); 
    Scanner type = new Scanner(System.in);
      
    boolean typeValid = false;
    int type_choice = 0;

    int MIN_FIGHT_TYPE = 1;
    int MAX_FIGHT_TYPE = 3;
    
    //checking for user type choice validity
    while(!typeValid) {
      if(type.hasNextInt()) {
  			type_choice = type.nextInt();
  			typeValid = true;
        if(type_choice < MIN_FIGHT_TYPE || type_choice > MAX_FIGHT_TYPE){
  				typeValid = false;
  				System.out.println( "Invalid Range." );
        }
      }
      else {
  			type.next(); 
  			System.out.println( "Invalid Input." );
      }
    }
  
    //displays attack moves based on type chosen
    System.out.println(h.getSubNumAttackMenu(type_choice));
      
    //define range of sub attacks
    int MIN_SUB_ATTACK = 1;
    int MAX_SUB_ATTACK = h.getNumSubNumAttackMenuItems(type_choice);
  
    Scanner fight = new Scanner(System.in);
    
    boolean fightValid = false;
    int fight_choice = 0;
      
    //checking for user fight choice validity
    while(!fightValid) {
      if(fight.hasNextInt()) {
  			fight_choice = fight.nextInt();
  			fightValid = true;
        if(fight_choice < MIN_SUB_ATTACK || fight_choice > MAX_SUB_ATTACK){
  				fightValid = false;
  				System.out.println( "Invalid Range." );
        }
      }
      else {
  			fight.next(); 
  			System.out.println( "Invalid Input." );
      }
    }
  
    System.out.println(h.attack(e, type_choice, fight_choice));
      
    if(e.getHp() != 0){
      System.out.print(e.attack(h));
      System.out.println("\n" + e );
    }
    else{
      //monster is dead
      monster_alive = false;
      System.out.println("You defeated the " + e.getName()+"!");
      //randomize gold dropped from corpse
      Map m = Map.getInstance();
      int rand_gold = (int)(Math.random() * 10) + 1;
      h.collectGold(rand_gold);
      System.out.print("You find " + rand_gold + " gold on the corpse.\n");
    }
    if(h.getHp() == 0){
      //user is dead
      monster_alive = true;
    } 
  return monster_alive;
}
  
  /** 
    * Hero enters a store
    * @param h to purchase items using gold
    */
  public static void store(Hero h){
    
    System.out.println("Welcome to the store.");
    boolean shop_done = false;
    
    while (!shop_done){
      System.out.println("What would you like to buy?\n1. Health Potion - 25g\n2. Key - 50g\n3. Nothing, just browsing....");
      int MIN_CHOICE = 1;
      int MAX_CHOICE = 3;
    
      Scanner buy = new Scanner(System.in);
      
      boolean buyValid = false;
      int buy_choice = 0; 
      //checking for user fight choice validity
      while(!buyValid) {
        if(buy.hasNextInt()) {
    			buy_choice = buy.nextInt();
    			buyValid = true;
          if(buy_choice < MIN_CHOICE || buy_choice > MAX_CHOICE){
    				buyValid = false;
    				System.out.println( "Invalid Range." );
          }
        }
        else {
    			buy.next(); 
    			System.out.println( "Invalid Input." );
        }
      }

      if (buy_choice == 1){
        int potion_price = 25;
        boolean pay = h.spendGold(potion_price);
        if(pay == false){
          System.out.println("Not enough gold.");
        }
        else{
          System.out.println("You purchased a potion.");
          h.pickUpPotion();
        }
      }
      else if(buy_choice == 2){
        int key_price = 50;
        boolean pay = h.spendGold(key_price);
        if(pay == false){
          System.out.println("Not enough gold.");
        }
        else{
          System.out.println("You purchased a key.");
          h.pickUpKey();
        }
      }
      else{
        System.out.println("Leaving shop...");
        shop_done = true;
      }
    }
  }
}