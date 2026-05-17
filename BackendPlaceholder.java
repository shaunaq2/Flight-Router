import java.util.List;
import java.util.ArrayList;
public class BackendPlaceholder implements BackendInterface{
    //private GraphADT<String, Integer> routeGraph;

    // Constructor for the placeholder class
    //public BackendPlaceholder(GraphADT<String, Integer> routeGraph) {
    //  this.routeGraph = routeGraph;
    //}
    @Override
    public void readDataFromFile(String fileName) {
    }

    @Override
    public ShortestPathInterface getShortestRoute(String departureAirport, String arrivalAirport) {
        List<String> dummyList = new ArrayList<>();
        dummyList.add("Airport1");
        dummyList.add("Airport2");
        return (ShortestPathInterface) dummyList;
    }

    @Override
    public String getDatasetStatistics() {
        return "Flight Statistics:";
    }
}