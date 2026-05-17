import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public interface FrontendInterface {

    /**
     * Constructor for the FrontendInterface.
     * @param backend A reference to the backend implementation
     * @param scanner A Scanner instance for reading user input.
     */
    // public FrontendInterface(BackendInterface backend, Scanner scanner);
    // this.backend = backend;
    // this.scanner = scanner;


    /**
     * Starts the main command loop for the user.
     */
    public void startMainLoop();

    /**
     * Shows the main menu.
     */
    public void displayMainMenu();

    /**
     * Loads the file.
     * @param fileName The name of the file to load.
     * @throws FileNotFoundException If the file is not found.
     */
    public void loadDataFromFile(String fileName) throws FileNotFoundException;


    /**
     * Shows the stats: number of airports(nodes), number of edges(flights),
     * and total number of miles(sum of all edge weights)
     */
    public void printFlightStatistics();

    /**
     * Shows the shortest path between two airports including all airports on the way,
     * the distance for each segment, and the total number of miles from start to destination airport
     * @param startAirport The starting airport
     * @param destinationAirport The destination airport
     * @throws IllegalArgumentException
     */
    public List<String> shortestPath(String startAirport, String destinationAirport) throws IllegalArgumentException;

    /**
     * Exits the app.
     */
    public void exit();


}
