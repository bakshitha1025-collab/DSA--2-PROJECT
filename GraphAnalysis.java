import java.util.*;

public class GraphAnalysis {

    static final int V = 5;

    // BFS
    static void bfs(int[][] graph, int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int i = 0; i < V; i++) {
                if (graph[node][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    // DFS
    static void dfs(int[][] graph, int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int i = 0; i < V; i++) {
            if (graph[node][i] != 0 && !visited[i]) {
                dfs(graph, i, visited);
            }
        }
    }

    // Prim's Algorithm
    static void primMST(int[][] graph) {

        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {

            int min = Integer.MAX_VALUE;
            int u = -1;

            for (int v = 0; v < V; v++) {
                if (!mstSet[v] && key[v] < min) {
                    min = key[v];
                    u = v;
                }
            }

            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 &&
                        !mstSet[v] &&
                        graph[u][v] < key[v]) {

                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        System.out.println("\nPrim's MST:");

        int total = 0;

        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i +
                    " : " + graph[i][parent[i]]);
            total += graph[i][parent[i]];
        }

        System.out.println("Total Cost = " + total);
    }

    // Edge class for Kruskal
    static class Edge implements Comparable<Edge> {

        int src, dest, weight;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }

        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }

    static int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;

        return find(parent, parent[i]);
    }

    static void union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    static void kruskalMST() {

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 3, 6));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 8));
        edges.add(new Edge(1, 4, 5));
        edges.add(new Edge(2, 4, 7));
        edges.add(new Edge(3, 4, 9));

        Collections.sort(edges);

        int[] parent = new int[V];

        for (int i = 0; i < V; i++)
            parent[i] = i;

        System.out.println("\nKruskal's MST:");

        int total = 0;
        int count = 0;

        for (Edge e : edges) {

            int x = find(parent, e.src);
            int y = find(parent, e.dest);

            if (x != y) {

                System.out.println(
                        e.src + " - " +
                        e.dest + " : " +
                        e.weight);

                total += e.weight;
                union(parent, x, y);
                count++;

                if (count == V - 1)
                    break;
            }
        }

        System.out.println("Total Cost = " + total);
    }

    public static void main(String[] args) {

        int[][] graph = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        bfs(graph, 0);

        System.out.print("DFS Traversal: ");
        boolean[] visited = new boolean[V];
        dfs(graph, 0, visited);
        System.out.println();

        primMST(graph);

        kruskalMST();
    }
}