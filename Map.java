import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Point;

class Map{
    private char[][] map = new char[5][5];
    private boolean[][] revealed = new boolean[5][5];
    private static Map instance = null;

    /** 
     * default Map constructror
     */
    private Map(){
        map = new char[5][5];
        revealed = new boolean[5][5];
    }

    /** 
     * If the instance is null, make a new instance and return it.
     * @return instance a new Map instance.
     */
    public static Map getInstance(){
        if(instance == null){
            instance = new Map();
        }
        return instance;
    }

    /**
     * Reads in the map from the file and stores it in the char character.
     * @param mapNum the map number to read in the array.
     */
    public void loadMap(int mapNum){
        String m = "";
        if (mapNum == 1){
            m = "Map1.txt";
            revealed = new boolean[5][5];
        }else if (mapNum == 2){
            revealed = new boolean[5][5];
            m = "Map2.txt";
        }else if (mapNum == 3){
            revealed = new boolean[5][5];
            m = "Map3.txt";
        }
        try {
            Scanner read = new Scanner(new File(m));
            while(read.hasNextLine()){
                for(int i = 0; i < map.length; i++){
                    String line = read.nextLine();
                    for(int j = 0; j < map[i].length; j++){
                        map[i][j] = line.charAt(2*j);
                    }
                }
            }
            read.close();
        }catch(FileNotFoundException fnf){
            System.out.println("File was not found");
        }
    }

    /**
     * Pass in the point and get the character at that location.
     * @param p the location of the character.
     * @return c the character at Point p.
     */
    public char getCharAtLoc(Point p){
        char c = map[p.x][p.y];
        return c;
    }

    /**
     * Find the start of each map.
     * @return p the start of the Map.
     */
    public Point findStart(){
        char start = 's';
  		int x = 0, y = 0; 
  		for (int r = 0; r < map.length; r++) {
  			for (int c = 0; c < map[0].length; c++) {
  				if (map[r][c] == start) {
  					x = r;
  					y = c;
  				}
  			}
  		}
        this.revealed[x][y] = true;
  		Point p = new Point(x, y);	
        return p;
    }

    /**
     * Reveal the character at the point p.
     * @param p the location to reveal.
     */
    public void reveal(Point p){
      revealed[p.x][p.y] = true;
    }

    /**
     * Remove charcter at the point p.
     * @param p the location to remove.
     */
    public void removeCharAtLoc(Point p){
      this.map[p.x][p.y] = 'n';
    }

    /**
     * Print out the map according layout.
     * @param p the current location of the hero.
     * @return temp the string to dispaly the map.
     */
    public String mapToString(Point p){
        String temp = "";
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if(i == p.x && j == p.y){
                  temp += "* ";
                }
                else if (revealed[i][j] == false){
                  temp += "x ";
                }
                else{
                  temp += this.map[i][j] + " ";
                }
            }
            if(i + 1 != map.length){
                temp += "\n";
           }
        }
        return temp;
    }
}
