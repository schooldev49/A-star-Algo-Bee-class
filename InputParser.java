import java.io.*;
import java.util.*;

public class InputParser
{
    private static int[] parseCoordinates(String line)
    {
        String[] commasplit = line.split(",");
        if (commasplit == null || commasplit.length != 3)
        {
            return null;
        }
        int[] coords = new int[commasplit.length];
        for (int i=0; i<commasplit.length; i++)
        {
            coords[i] = Integer.valueOf(commasplit[i]);
        }
        
        return coords;
    }
    
    
    public static Cube parseInput(String filePath) throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            int[] gridDimensions = null;

            ArrayList<Bee> bees = new ArrayList<>();
            ArrayList<int[]> hiveSlots = new ArrayList<>();
            int hives = 0;
            
            while ((line = reader.readLine()) != null)
            {
                line = line.trim();
                int[] coordinates = parseCoordinates(line);
                if (line.isEmpty() || coordinates == null) continue;
                if (gridDimensions == null)
                {
                    gridDimensions = coordinates;
                }
                else if (hiveSlots.size() < 15)
                {
                    hiveSlots.add(coordinates);
                }
                else if (bees.size() < 15)
                {
                    bees.add(new Bee((bees.size()+1),coordinates, hiveSlots.get(bees.size())));
                }
                else 
                {
                    break;
                }
            }
            Cube cube = new Cube(gridDimensions, bees);
            System.out.println("The hive is from" + Arrays.toString(hiveSlots.get(0)) + "to " + Arrays.toString(hiveSlots.get(hiveSlots.size()-1)));
            return cube;
        }
    }
}
