import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Backend implements BackendInterface{

    protected DijkstraGraph<String, Integer> graph; // graph to use
    protected PlaceholderMap<String, String> mapCity; // map to store names of city based on city codes
    protected int numLinesContainingEdge; // number of lines from a file that indicate edges needed to be inserted
    private int totalMiles; // keeps count the total miles of all flight routes

    /**
     * Constructor for the backend class and accepts a graph instance.
     * @param graph graph to be used.
     */
    public Backend(GraphADT<String, Integer> graph){
        if (graph instanceof DijkstraGraph) {
            this.graph = (DijkstraGraph<String, Integer>) graph;
            mapCity = new PlaceholderMap<>();
        }
    }

    /**
     * Reads graphics data from a file.
     *
     * @param filePath The path to the DOT file.
     * @throws IOException If an error occurred while reading the file.
     */
    @Override
    public void readDataFromFile(String filePath) throws IOException {

        File file = new File(filePath);
        if (!file.exists()){
            throw new FileNotFoundException();
        }

        Scanner scnr = new Scanner(file);
        String pred;
        String succ;
        String weight;
        String match;
        String code;
        String city;
        Pattern p;
        Matcher m;
        numLinesContainingEdge = 0;
        totalMiles = 0;

        while (scnr.hasNextLine()) {

            String current = scnr.nextLine();

            p = Pattern.compile("--");
            m = p.matcher(current);

            if (m.find()) {

                p = Pattern.compile("\"\\w{3,}\"\\s--\\s\"\\w{3,}\"");
                m = p.matcher(current);
                if (m.find()) {

                    match = m.group();
                    pred = "" + match.substring(1, 4);
                    succ = "" + match.substring(10, 13);
                    graph.insertNode(pred);
                    graph.insertNode(succ);

                    p = Pattern.compile("\\d+");
                    m = p.matcher(current);
                    if (m.find()) {
                        weight = "" + m.group();
                        Integer miles = Integer.parseInt(weight);
                        graph.insertEdge(pred, succ, miles);
                        graph.insertEdge(succ, pred, miles);
                        numLinesContainingEdge++;
                    }
                }

            } else {

                p = Pattern.compile("\"\\w+\"");
                m = p.matcher(current);

                if (m.find()) {
                    code = m.group().substring(1, 4);
                    city = current.substring(current.indexOf('=') + 2, current.lastIndexOf('"'));
                    mapCity.put(code, city);

                    int size = graph.nodes.get(code).edgesLeaving.size();
                    for (int i = 0; i < size; i++) {
                        totalMiles += graph.nodes.get(code).edgesLeaving.get(i).data;
                    }
                }

            }
        }
    }

    /**
     * Calculates the shortest path from the starting point to the destination airport.
     *
     * @param start The identifier of the start airport.
     * @param destination The identifier of the destination airport.
     * @return An instance of the shortest path search result.
     * @throws NoSuchElementException If the start or destination airport does not exist or there is no available route
     *                                  from start to destination.
     */
    @Override
    public ShortestPathInterface getShortestRoute(String start, String destination) throws NoSuchElementException {

        if ( !graph.containsNode(start) || !graph.containsNode(destination)){
            throw new NoSuchElementException();
        }

        ShortestPath path = new ShortestPath();
        List<String> route;
        List<Integer> miles = new ArrayList<>();


        route = graph.shortestPathData(start, destination);

        for (int i = 0; i < route.size() - 1; i++) {
            miles.add(graph.getEdge(route.get(i),route.get(i + 1)));
        }

        path.setRoute(route);
        path.setMiles(miles);
        path.setTotalMiles((int)graph.shortestPathCost(start, destination));

        return path;
    }

    /**
     * Get statistics for the dataset, including the number of airports, the number of flights,
     * and the total number of miles for all flights.
     *
     * @return A string describing the statistics of the dataset.
     */
    @Override
    public String getDatasetStatistics() {

        String numAirports = "Number of airports: " + graph.getNodeCount();
        String numFlights = "\nNumber of flights: " + graph.getEdgeCount();
        String totalDistances = "\nTotal miles for all flights: " + totalMiles + " miles";

        return numAirports + numFlights + totalDistances;
    }

    public static void main(String[] args) {

        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        Backend backend = new Backend(graph);
        Scanner scanner = new Scanner(System.in);
        FrontendInterface frontend = new Frontend(backend, scanner);

        frontend.startMainLoop();
    }
}

