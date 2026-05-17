import java.util.List;

public interface ShortestPathInterface {
    /**
     * get a list of airports along the route
     * @return  a list of airports
     */
    public List<String> getRoute();

    /**
     * get a list of the miles to travel for each segments of the route
     * @return a list of miles
     */
    public List<Integer> getMiles();

    /**
     * get the total miles for the route
     * @return total miles of a route
     */
    public int getTotalMiles();

}
