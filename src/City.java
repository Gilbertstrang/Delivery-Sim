/**
 * @author Enes Duman
 * @studentID 2023719042
 * @date 17.12.23
 *
 * City class creates buildings from text file coordinates,
 * finds the shortest route between buildings and market.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;


public class City {
    Building migros;
    ArrayList<Building> buildings;

    /**
     * Construct city from text coordinates.
     * Per text line, reads x and y coordinates and puts it in a building object.
     * Counts every line as one building, so it numbers them with number attribute.
     * If the line has "migros" in it, sets the building as migros (true).
     * @param fileName
     * @throws IOException
     */
    public City(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        int count = 1;
        buildings = new ArrayList<>();
        while (line != null) {
            String[] coords = line.split(",");
            double xCoord = Double.parseDouble(coords[0]);
            double yCoord = Double.parseDouble(coords[1]);

            Building b = new Building(count++, xCoord, yCoord);

            b.setMigros(coords.length == 3);
            if(b.isMigros()) {
                migros = b;
            }
            buildings.add(b);

            line = reader.readLine();
        }
    }

    /**
     * Finds the shortest path from Building b and returns the distance (from xy-coordinate) and the node list as pair
     *
     * Sets the building's visited attribute true, if all the nodes visited, set b.visited false and return the pair
     * Iterate through each building neighbor nodes, if one of them not visited take the distance to that node and
     * call the function again, this time with the neighbor node itself. Add it to a list with the same logic.
     * If the calculated distance is shorter than minDist, update minDist. Also, update the minList.
     * Set visited false and return the pair
     *
     * @param b
     * @return distance, list
     */
    private SimpleEntry<Double, ArrayList<Building>> shortest(Building b) {
        b.setVisited(true);

        int i;
        for (i = 0; i < buildings.size(); i++) {
            if (!buildings.get(i).isVisited()) {
                break;
            }
        }

        if (i == buildings.size()) {
            b.setVisited(false);
            return new SimpleEntry<>(b.distance(migros), new ArrayList<>(Arrays.asList(b, migros))) ;
        }

        double dist;
        ArrayList<Building> list = new ArrayList<>();
        ArrayList<Building> minList = new ArrayList<>();
        double minDist = Double.MAX_VALUE; // high number for comparison

        for (Building neighbor : buildings) {
            if (!neighbor.isVisited()) {

                SimpleEntry<Double, ArrayList<Building>> entry = shortest(neighbor);
                dist = b.distance(neighbor) + entry.getKey();
                list = new ArrayList<>(Arrays.asList(b));
                list.addAll(entry.getValue());

                if (dist < minDist) {
                    minDist = dist;
                    minList = new ArrayList<>();
                    minList.addAll(list);
                }
            }
        }

        b.setVisited(false);



        return new SimpleEntry<>(minDist, minList);
    }


    /**
     * Starts the shortest function from migros node
     * @return minDistance, List
     */
    public SimpleEntry<Double, ArrayList<Building>> solveShortestPath() {
        return shortest(migros);
    }

}
