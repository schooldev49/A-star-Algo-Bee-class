import java.lang.Math;
import java.util.*;
public class Pathfinding {
    public static ArrayList<int[]> findPath(int[] start, int[] goal, boolean[][][] obstacles)
    {
        int[] movements = {1, -1, 0}; // allows for diagonals
        PriorityQueue<Node> potentialNodes = new PriorityQueue<>();
        HashSet<String> visitedNodes = new HashSet<String>();
        
        potentialNodes.add(new Node(start, 0, heuristic(start, goal), null));
        visitedNodes.add(Arrays.toString(start));

        while (!potentialNodes.isEmpty())
        {
            Node current = potentialNodes.poll();
            if (Arrays.equals(current.getPosition(), goal))
            {
                // Yay we hit the path
                return createPath(current);
            }
            
            // Nope!
            
            visitedNodes.add(Arrays.toString(current.getPosition()));
            
            for (int changeX: movements)
            {
                for (int changeY: movements)
                {  
                    for (int changeZ: movements)
                    {
                        if (changeX == 0 && changeY == 0 && changeZ == 0)
                        {
                           
                            continue;
                        }
                    
                        
                        if (changeX != 0 && changeY != 0 && changeZ != 0)
                        {
                           
                            continue;
                        }
                        
                        int[] neighbor = {
                            current.getPosition()[0] + changeX,
                            current.getPosition()[1] + changeY,
                            current.getPosition()[2] + changeZ
                        };
                        
                        if (checkBoundary(neighbor, obstacles) == true || visitedNodes.contains(Arrays.toString(neighbor)))
                        {
                            continue;
                        }
                        
                        int g = current.getInitialCost()+1;
                        int h = heuristic(neighbor, goal);
                        
                        potentialNodes.add(new Node(neighbor, g, h, current));
                    }
                }
            }
        }
        
        return new ArrayList<int[]>();
    }
    
    private static int heuristic(int[] pos, int[] goal)
    {
        int sum = 0;

        for (int i=0; i<3; i++)
        {
            sum += (Math.abs(pos[i] - goal[i]));
        
        }
        return sum;
    }
    
    private static boolean checkBoundary(int[] pos, boolean[][][] obstacles)
    {
        int x = pos[0];
        int y = pos[1];
        int z = pos[2];
        
        boolean xCheck = x >= 0 && x < obstacles.length;
        boolean yCheck = y >= 0 && y < obstacles[0].length;
        boolean zCheck = z >= 0 && z < obstacles[0][0].length;
        
        if (!xCheck || !yCheck || !zCheck) return true;
        
        boolean obstacleCheck = (obstacles[x][y][z] == true);
        
        return obstacleCheck;
    }
    
    private static ArrayList<int[]> createPath(Node node)
    {
        ArrayList<int[]> path = new ArrayList<>();
        while (node != null)
        {
            path.add(node.getPosition());
            node = node.getParent();
        }
        
        Collections.reverse(path);
        return path;
    }
}
