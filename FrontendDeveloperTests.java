import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrontendDeveloperTests extends TextUITester{

    /**
     * Creates a new test object with the specified string of simulated user input text.
     *
     * @param programInput the String of text that you want to simulate being typed in by the user.
     */
    /**
     * Creates a new test object with the specified string of simulated user input text.
     */
    public FrontendDeveloperTests() {
        super("");
    }
    BackendInterface backend = new BackendInterface() {
        @Override
        public void readDataFromFile(String filePath) throws IOException {

        }

        @Override
        public ShortestPathInterface getShortestRoute(String start, String destination) throws NoSuchElementException {
            return null;
        }

        @Override
        public String getDatasetStatistics() {
            return null;
        }
    };

    /**
     * This test checks the constructor of the frontend class and checks if the instances are passed correctly.
     */
    @Test
    public void testDisplay() {
        try {
            //Instances of scanner and backend are initiated.
            Scanner scanner = new Scanner(System.in);
            Frontend frontend = new Frontend(backend, scanner);
            //Makes sure the constructor is passed and the frontend instance is not null.
            Assertions.assertNotNull(frontend);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    /**
     * This test checks the exit method of the frontend class and uses TestUITester for programInput
     * and checks if the output contains the expected message.
     */
    @Test
    public void testExitMethod() {
        // Create a new TextUITester object with input "4\n"
        TextUITester tester = new TextUITester("4\n");

        // Use the tester to simulate user input and capture output
        Frontend frontend = new Frontend(new BackendPlaceholder(), new Scanner(System.in));
        frontend.startMainLoop();  // This will invoke the main menu and exit immediately

        // Capture the output after the exit method
        String output = tester.checkOutput();
        System.out.println(output);

        // Check if exit message was printed
        Assertions.assertTrue(output.contains("Exiting App"));
    }

    /**
     * This tester checks the loadFile method of the frontend and uses the TextUITester class to take in
     * programInput and check of the output contains the expected message.
     */
    @Test
    public void testValidFileLoading() {
        //Creates instance of TextUITester, backend and Scanner.
        TextUITester tester = new TextUITester("1\nflights.dot\n");
        Scanner scanner = new Scanner(System.in);
        FrontendInterface frontend = new Frontend(new BackendPlaceholder(), scanner);
        String fileName = "flights.dot";

        try {
            //Checks for a valid file
            frontend.loadDataFromFile(scanner.next());
            String output = tester.checkOutput();
            Assertions.assertTrue(output.contains("File loaded successfully"));
        } catch (FileNotFoundException e) {
            Assertions.fail("File is invalid " + e.getMessage());
        }
    }

    /**
     * The test checks the displayStats() method of the frontend and uses TextUITester class to take in
     * programInput and checks if the output contains the expected message.
     */
    @Test
    public void testPrintFlightStatistics() {
        //Instances of TextUITester, Scanner and backend is initiated.
        TextUITester tester = new TextUITester("2\n");
        Scanner scanner = new Scanner(System.in);
        FrontendInterface frontend = new Frontend(new BackendPlaceholder(), scanner);
        frontend.printFlightStatistics();
        String output = tester.checkOutput();
        System.out.println(output);
        Assertions.assertTrue(output.contains("Flight Statistics:"));
    }


    @Test
    public void integrationTest1() {

        try {

            // Create an instance of DijkstraGraph and Backend
            DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());

            // Read data from the file
            backend.readDataFromFile("./src/flights.dot");

            // Define start and destination airports
            String startAirport = "ORD";
            String destinationAirport = "SFO";

            // Get the shortest path
            ShortestPathInterface shortestPath =
                    (ShortestPathInterface) backend.getShortestRoute(startAirport, destinationAirport);

            // Define expected results
            String expectedRoute = "[ORD, OAK, SFO]";
            String expectedMilesPerSegment = "[1835, 11]";
            double expectedTotalMiles = 1846.0;

            // Assert the results
            assert (shortestPath.getRoute().toString().contains(expectedRoute));
            assert (shortestPath.getMiles().toString().contains(expectedMilesPerSegment));
            assertEquals(shortestPath.getTotalMiles(), expectedTotalMiles);

            // catch any exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void integrationTest2() {
        try {
            backend.readDataFromFile("./src/flights.dot");

            String expected1 = "Number of airports: 58";
            String expected2 = "Number of flights: 1598";
            String expected3 = "Total miles for all flights: 2142457 miles";

            assert (backend.getDatasetStatistics().contains(expected1));
            assert (backend.getDatasetStatistics().contains(expected2));
            assert (backend.getDatasetStatistics().contains(expected3));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
