public class Node implements Comparable<Node>
{
    private int[] position;
    private int g;  // Cost from the start
    private int h; // Heurestic (Estimated cost to reach goal)
        
    private Node parent; // parent node
        
    public Node(int[] position, int g, int h, Node parent)
    {
        this.position = position;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }
        
    public int getInitialCost()
    {
        return g;
    }
    
    public Node getParent()
    {
        return parent;
    }
    
    public int cost()
    {
        return g+h;
    }
    
    public int getEstimate()
    {
        return h;
    }
    
    public int[] getPosition()
    {
        return position;
    }
        
    public int compareTo(Node other)
        {
            return Integer.compare(this.cost(), other.cost());
        }
    }  
