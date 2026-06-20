public class CO4ShortestPath {

    public static void main(String[] args) {

        System.out.println("SHORTEST PATH OPTIMIZATION\n");

        System.out.println("Parking Route Costs\n");

        System.out.println("Gate -> Zone A = 4");
        System.out.println("Gate -> Zone B = 2");
        System.out.println("Zone B -> Zone C = 3");
        System.out.println("Zone C -> Zone D = 2\n");

        System.out.println("OPTIMAL ROUTE\n");

        System.out.println("Gate -> Zone B -> Zone C -> Zone D");

        System.out.println("\nMinimum Distance = 7\n");

        System.out.println("Time Complexity:");
        System.out.println("Dijkstra Algorithm -> O(V²)");
    }
}