import java.util.*;
public class Bee {
    
    private int movesMade;
    private int[] position;
    private int[] goal;
    
    int id;

    public Bee(int id, int[] position, int[] goal)
    {
        this.position = position;
        this.goal = goal;
        movesMade = 0;
        
        this.id = id;
        
        System.out.println("Bee " + Integer.toString(id) + "started at " + Arrays.toString(position));
    }
    

    public int[] getPosition()
    {
        return position;
    }
    
    
    public void setPosition(int[] newPosition)
    {
        position = newPosition;
        addMoves(1);
    }
    
    public void addMoves(int num)
    {
        movesMade += num;
    }
    
    public int[] getGoal()
    {
        return goal;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setGoal(int[] goal)
    {
        this.goal = goal;
    }
    
    public int getMoves()
    {
        return movesMade;
    }
    
    public String toString()
    {
        return "Bee" + Integer.toString(id) + " was move into " + Arrays.toString(position) + " in " + Integer.toString(movesMade); 
    }
}
