public abstract class Entity{
    private String name;
    private int hp;
    private int maxHp;
  
    /** 
     * Determines attribute for Entity object.
     */
    public Entity(String n, int mHp){
        name = n;
        hp = mHp;
        maxHp = mHp;
    }
  
    /** Return the object's name
    * @return String name
    */
    public String getName(){
        return name;
    }

    /** Return the object's current health.
    * @return int health.
    */
    public int getHp(){
        return hp;
    }

    /** Set object's hp equal to max hp 
    */
    public void heal(){
        hp = maxHp;
    }

    /** Return the user's direction input 
    * @param d subtract hp from
    */
    public void takeDamage(int d){
        if((hp - d) <= 0){
            this.hp = 0;
        }else {
            this.hp -= d;
        }
    }

    /** Return string of object attributes
    * @return String description
    */
    @Override
    public String toString(){
        return getName() + "\n" + "HP: " + getHp() + "/" + this.maxHp;
    }
}
