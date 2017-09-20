
import java.util.HashMap;


/**
 *
 * @author ANTS
 */
public class Player {
    HashMap<Integer,int[]> map = new HashMap<Integer,int[]>();
    String player;
    int score;
    int x;
    int y;
    public Player(String player,int score,int x,int y){
        this.player = player;
        this.score = score;
        this.x = x;
        this.y = y;
        dotset();
    }
    public void update(int x0,int y0){
        
        x += x0;
        y += y0;
        if(x >= 45){
            x = 0;
        }else if(x < 0){
            x = 44;
        }
        if(y >= 45){
            y =0;
        }else if(y < 0){
            y =44;
        }
        int count = 0;
        for(int i=0; i < map.size();i++){
            if(x == map.get(i)[0] && y == map.get(i)[1]){
                map.replace(i,new int[]{50,y+3});
                count++;
                if(i <4){
                    score += 2;
                }else if(i <8){
                    score += 4;
                }else{
                    score += 10;
                }    
            }
        }
        if(count == 2){
             
        }
        
        
    }
    public String getupdate(){
        return "[\""+player+"\","+score+" , "+x+", "+y+"]";
    }
    public void dotset(){
        // Blue dots
        map.put(0,new int[] {5,6});
        map.put(1,new int[] {25,8});
        map.put(2,new int[] {15,36});
        map.put(3,new int[] {25,28});
        
        //Red dots
        map.put(4,new int[] {10,21});
        map.put(5,new int[] {34,7});
        map.put(6,new int[] {42,17});
        map.put(7,new int[] {5,37});
        
        //Green dots
        map.put(8,new int[] {23,12});
        map.put(9,new int[] {28,1});
        map.put(10,new int[] {22,22});
        map.put(11,new int[] {9,39});
      
    }
    public String getdot(){
        String dot = "";
        dot += "[\"B\", "+map.get(0)[0]+","+map.get(0)[1]+"]";
        for(int i=1; i < 4;i++){
            dot += ",[\"B\", "+map.get(i)[0]+","+map.get(i)[1]+"]";
          
        }
        for(int i=4; i < 8;i++){
            dot += ",[\"R\", "+map.get(i)[0]+","+map.get(i)[1]+"]";
          
        }
        for(int i=8; i < 12;i++){
            dot += ",[\"G\", "+map.get(i)[0]+","+map.get(i)[1]+"]";
        }
        return dot;
    }
}
