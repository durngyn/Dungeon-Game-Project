import java.util.*;
import java.io.*;

public class EnemyGenerator{
    static HashMap <String, Integer> enemies;
  
    /** 
     *Creates hashmap attribute for Enemy Generator.
     */
    public EnemyGenerator(){
      String[] split;
      enemies = new HashMap<String, Integer>();
      try{
        Scanner read = new Scanner(new File("Enemies.txt"));
        while(read.hasNextLine()){
          String line = read.nextLine();
          split = line.split(",") ;
          int num = Integer.parseInt(split[1]);
          enemies.put(split[0],num);
        } 
      }   
      catch(FileNotFoundException fnf){
        System.out.println("File was not found");
      }  
    }
  public HashMap <String, Integer> getMap(){
    return enemies;
  }
    
    /** 
     *Return a random Enemy object from the hashmap of Enemy.txt contents
     * @param level to scale enemy hp
     * @return Enemy of three different types
     */
    public static Enemy generateEnemy(int level){
      Enemy e = new Warrior("UNNAMED WARRIOR", 1000);
      int cnt = 0;
      int size = enemies.size();
      int rand = (int)(Math.random() * (size-1)+1) + 1;
      String name = "";
      int type = (int)(Math.random() * 3); 
      int hp = 0;
      for (HashMap.Entry<String, Integer> entry : enemies.entrySet()) {
        hp = 0;
        cnt++;
        if (rand == cnt){
          name = entry.getKey();
          hp = entry.getValue();
          hp += (level - 1);
          break;
        }
      }
      if (type == 0){
        name = name + " Warrior";
        e = new Warrior(name, hp);
        }
      else if (type == 1){
        name = name + " Ranger";
        e = new Ranger(name, hp);
      }
      else{
        name = name + " Wizard";
        e = new Wizard(name, hp);
      }
      return e;
    }
}
