import java.util.List;

/**
 * This is the ShortestPath class that holds data of the shortest route found between two airports.
 *
 */
public class ShortestPath implements ShortestPathInterface{

    private List<String> route = null; // list that stores airports
    private List<Integer> miles = null; // list that stores distances between airports of the shortest route
    private int totalMiles = 0; // stores the total miles from start to destination airport

    /**
     * Sets the list of airports.
     * @param route list of airports of the shortest route.
     */
    public void setRoute(List<String> route) {
        this.route = route;
    }

    /**
     * Sets the list of miles.
     * @param miles list of miles of the shortest route.
     */
    public void setMiles(List<Integer> miles) {
        this.miles = miles;
    }

    /**
     * Sets the total miles.
     * @param totalMiles total miles of the shortest route.
     */
    public void setTotalMiles(int totalMiles) {
        this.totalMiles = totalMiles;
    }

    /**
     * Gets a list of airports along the route.
     * @return  a list of airports
     */
    @Override
    public List<String> getRoute() {
        return route;
    }

    /**
     * Gets a list of the miles to travel for each segments of the route.
     * @return a list of miles
     */
    @Override
    public List<Integer> getMiles() {
        return miles;
    }

    /**
     * Gets the total miles for the route.
     * @return total miles of a route
     */
    @Override
    public int getTotalMiles() {
        return totalMiles;
    }
}

