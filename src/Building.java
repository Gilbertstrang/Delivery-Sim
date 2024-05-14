/**
 * @author Enes Duman
 * @studentID 2023719042
 * @date 17.12.23
 *
 * Building class. Building object has coordinates, whether it is migros, which building number it is and whether
 * if it's visited or not.
 */


public class Building {

    public int getNumber() {
        return number;
    }

    private int number;
    private double x;
    private double y;
    private boolean migros;

    public void setMigros(boolean migros) {
        this.migros = migros;
    }

    public boolean isMigros() {
        return migros;
    }

    public Building(int number, double x, double y) {
        this.number = number;
        this.x = x;
        this.y = y;
    }

    public boolean visited;


    public boolean isVisited() {
        return visited;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Calculates distance between this building and building b.
     * @param b
     * @return distance
     */
    public double distance(Building b) {
        return Math.sqrt((b.y - this.y) * (b.y - this.y) + (b.x - this.x) * (b.x - this.x));
    }

}
