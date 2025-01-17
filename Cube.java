import java.util.*;
public class Cube {
    private int[] dimensions; 
    private ArrayList<Bee> bees;
    private boolean[][][] grid;

    public Cube(int[] dimensions, ArrayList<Bee> bees)
    {
        this.dimensions = dimensions;
        this.bees = bees;
        this.grid = new boolean[dimensions[0]][dimensions[1]][dimensions[2]];
    
       
    
        addDebris();
        
        startFinding();
        
        
    }
    
    private void startFinding()
    {   
        int sum = 0;
        for (Bee bee: bees)
        {
            ArrayList<int[]> path = Pathfinding.findPath(bee.getPosition(), bee.getGoal(), grid);
            if (path.isEmpty())
            {
                System.out.println("No path found for " + bee.getId());
            }
            else
            {
                for (int i=1; i<path.size(); i++)
                {
                    bee.setPosition(path.get(i));
                    sum += 1;
                }
            }
            
            System.out.println(bee);
        }
        
        System.out.println("The sum of all moves is " + sum);

            
    } 

    private void addDebris()
    {
        HashSet<String> locked = new HashSet<>();

        int numDebris = (int)((dimensions[0]*dimensions[1]*dimensions[2]*0.3));
        Random random = new Random();
        
        for (Bee bee: bees)
        { // prevents bugs 
            locked.add(Arrays.toString(bee.getPosition()));
            locked.add(Arrays.toString(bee.getGoal()));
        }
        while (numDebris > 0)
        {
        
            int x = random.nextInt(dimensions[0]);
            int y = random.nextInt(dimensions[1]);
            int z = random.nextInt(dimensions[2]);
            
            String key = Arrays.toString(new int[]{x,y,z});
            
            if (grid[x][y][z] == false && !locked.contains(key))
            {
                grid[x][y][z] = true;
                numDebris -= 1;
            }
        }
    }
    
    public boolean[][][] getGrid()
    {
        return grid;
    }
    
    public int[] getDimensions()
    {
        return dimensions;
    }
    
 
    
    public ArrayList<Bee> getBees()
    {
        return bees;
    }

    
   
    
}
