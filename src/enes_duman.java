/**
 * @author Enes Duman
 * @studentID 2023719042
 * @date 17.12.2023
 *
 * Finds the quickest delivery route of a market car. Starts from market node, travels to each node once
 * then returns to market.
 */


import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;

public class enes_duman {

    /**
     * Main function. Creates a city object from text file coordination.
     * Prints the results.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        City city = new City("input04.txt");    //CHANGE FILE NAME HERE
        AbstractMap.SimpleEntry<Double, ArrayList<Building>> entry = city.solveShortestPath();

        //prints the result in a readable way.
        System.out.println("Distance: " + entry.getKey());
        System.out.print("Shortest route: [");
        for(int i = 0; i < entry.getValue().size(); i++) {
            System.out.print(entry.getValue().get(i).getNumber() + ((i == entry.getValue().size() - 1) ? "" : ", "));
        }
        System.out.println("]");

    }
}
