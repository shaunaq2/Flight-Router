import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Back-end functional interfaces
 */
public interface BackendInterface {

    /**
     * Add constructors as comments to the interface definitions.
     * It takes an instance of the GraphADT as a constructor parameter
     */
    //public IndividualBackendInterface(GraphADT graphADT);

    /**
     * Reads graphics data from a file.
     *
     * @param filePath The path to the DOT file.
     * @return
     * @throws IOException If an error occurred while reading the file.
     */
    void readDataFromFile(String filePath) throws IOException;

    /**
     * Calculates the shortest path from the starting point to the destination airport.
     *
     * @param start The identifier of the start airport.
     * @param destination The identifier of the destination airport.
     * @return An instance of the shortest path search result.
     * @throws NoSuchElementException If the start or destination airport does not exist.
     */
    ShortestPathInterface getShortestRoute(String start, String destination) throws NoSuchElementException;

    /**
     * Get statistics for the dataset, including the number of airports, the number of flights,
     * and the total number of miles for all flights.
     *
     * @return A string describing the statistics of the dataset.
     */
    String getDatasetStatistics();
}
