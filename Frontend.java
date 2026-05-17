import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements the FrontendInterface and provides a user interface for
 * the flight destination app.
 */
public class Frontend implements FrontendInterface {
    Scanner scanner;
    BackendInterface backend;
    private Boolean loop;

    /**
     * This is a constructor for the frontend and takes 2 arguments.
     *
     * @param backend
     * @param scanner
     */
    public Frontend(BackendInterface backend, Scanner scanner) {
        this.scanner = scanner;
        this.backend = backend;
    }

    /**
     * This method starts the main command loop which the backend will access later.
     */
    @Override
    public void startMainLoop() {
        //sets the loopState to true
        loop = true;
        //runs the mainMenu while the loopState is true
        while (loop) {
            displayMainMenu();
        }
    }

    /**
     * This menu displays the main menu to the user and lets them access the commands
     * required to them.
     */
    @Override
    public void displayMainMenu() {
        System.out.println("Welcome to the Flight Router App! Here is the main menu");
        System.out.println("1. Load File");
        System.out.println("2. Display Stats");
        System.out.println("3. Find Shortest Path");
        System.out.println("4. Exit");

        int userChoice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        //Takes in user input and does the commands accordingly.
        switch (userChoice) {
            case 1:
                System.out.print("Enter file name: ");
                String fileName = scanner.nextLine().trim();
                try {
                    loadDataFromFile(fileName);
                } catch (FileNotFoundException e) {
                    System.out.println("File name is wrong");
                }
                break;
            case 2:
                printFlightStatistics();
                break;
            case 3:
                //fixed
                System.out.print("Enter start airport:");
                String startAirport = scanner.next();
                System.out.print("Enter destination airport:");
                String destinationAirport = scanner.next();
                try {
                    List<String> path = shortestPath(startAirport, destinationAirport);
                    //fixed
                    System.out.println(path.toString().substring(1, path.toString().lastIndexOf("]")) + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input. " + e.getMessage());
                }
                break;
            case 4:
                exit();
                break;
            default:
                System.out.println("Invalid choice.Please enter a number between 1 and 4.");
        }

        if (userChoice != 4) {
            loop = true;
        } else {
            loop = false;
        }
    }

    /**
     * This method takes in input from the user and loads the file specified by them.
     *
     * @param fileName The name of the file to load.
     * @throws FileNotFoundException
     */
    @Override
    public void loadDataFromFile(String fileName) throws FileNotFoundException {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        try {
            backend.readDataFromFile(fileName);
            System.out.println("File loaded successfully");
        } catch (Exception e) {
            throw new FileNotFoundException("The file provided is not found");
        }
    }


    /**
     * This method displays the statistics dataset that includes the number of airports,
     * the number of edges, and the total number of miles.
     */
    @Override
    public void printFlightStatistics() {
        System.out.println(backend.getDatasetStatistics());
    }

    /**
     * This method displays the shortest route taken from the start to the destination inputted
     * by the user. This displays all the airports, distance and the total number of miles.
     *
     * @param startAirport
     * @param destinationAirport
     */
    @Override
    public List<String> shortestPath(String startAirport, String destinationAirport) throws IllegalArgumentException {
        System.out.println("The shortest path is: ");

        List<String> data = new ArrayList<>();
        ShortestPathInterface path = backend.getShortestRoute(startAirport, destinationAirport);
        if (path != null) {
            data.add("Path: " + path.getRoute());
            data.add("Miles: " + path.getMiles());
            data.add("Total: " + path.getTotalMiles() + " miles");
        }
        return data;

    }

    /**
     * This method exits out of the main command loop
     */
    @Override
    public void exit() {
        System.out.println("Exiting App");
        loop = false;
    }
    public static void main(String[] args) {

        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        Backend backend = new Backend(graph);
        Scanner scanner = new Scanner(System.in);
        FrontendInterface frontend = new Frontend(backend, scanner);

        frontend.startMainLoop();
    }

}


