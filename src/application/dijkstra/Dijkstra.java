import java.util.Arrays;

public class Dijkstra {
    private final int n, start;
    private final int[] distance, parents;
    private final int[][] graph;
    private final boolean[] visited;
    private int step = 1;
    private DijkstraVisualizer visualizer;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_CYAN = "\u001B[36m";

    public Dijkstra(int n, int[][] matrix, int start, DijkstraVisualizer visualizer){
        this.n = n;
        graph = matrix;
        this.start = start;
        distance = new int[n];
        Arrays.fill(distance, -1);
        distance[start] = 0;
        parents = new int[n];
        Arrays.fill(parents, -1);
        parents[start] = start;
        visited = new boolean[n];

        this.visualizer = visualizer;
        this.visualizer.printStartInfo(this.start, distance, parents);
    }

    private void calculate(int vertex){
        for(int i = 0; i < n; i++){
            if(i == vertex || graph[vertex][i] == 0 || visited[i]){
                continue;
            }
            visualizer.printInfoVertexes(vertex, i, distance[vertex] + graph[vertex][i], distance[i]);
            if((distance[i] == -1) || (distance[vertex] + graph[vertex][i] < distance[i])){
                distance[i] = distance[vertex] + graph[vertex][i];
                parents[i] = vertex;
            }
        }
    }

    public boolean nextStep(){
        int[] lastDistance = distance.clone();

        int vertex = -1;
        for(int i = 0; i < n; i++){
            if(!visited[i] && distance[i] != -1){
                if((vertex == -1) || (distance[vertex] > distance[i])){
                    vertex = i;
                }
            }
        }
        if(vertex == -1){
            visualizer.printEnd();
            return true;
        }

        visualizer.printInfo(vertex);

        visited[vertex] = true;
        calculate(vertex);

        visualizer.printTable(n, distance, parents, visited, lastDistance);

        return false;
    }
}
