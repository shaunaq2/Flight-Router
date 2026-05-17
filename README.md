# Flight Router

A Java application that finds the shortest flight path between airports using Dijkstra's algorithm. Parses real airport network data from a DOT file (58 airports, 1,598 flights) and computes optimal routes by total mileage.

## Features

- Shortest path between any two airports with per-segment and total mileage
- Dataset statistics — airport count, flight count, total network miles
- Clean frontend/backend architecture with interface-driven design
- JUnit test suite covering path correctness, cost computation, and edge cases

## Tech Stack

Java, Dijkstra's Algorithm, JUnit 5, DOT file parsing (regex)

## How to Run

```bash
javac *.java
java Backend
```

Then follow the menu:
1. Load File — enter the path to your `.dot` flight data file
2. Display Stats — shows network statistics
3. Find Shortest Path — enter two airport codes (e.g. `ORD`, `SFO`)
4. Exit

## Architecture

| Class | Role |
|---|---|
| `DijkstraGraph` | Generic weighted graph with Dijkstra's shortest path |
| `Backend` | Parses DOT file, builds graph, computes routes |
| `Frontend` | CLI interface, handles user input/output |
| `ShortestPath` | Data model for route results |
| `PlaceholderMap` | HashMap-backed map implementation |

## Dataset

The included `flights.dot` file contains 58 US airports and 1,598 bidirectional flight routes with mileage weights.
