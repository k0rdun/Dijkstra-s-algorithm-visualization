package application.scenes.graph.graphParts;

public class Edge {
    private final Vertex startVertex;
    private final Vertex endVertex;
    private final int weight;

    public Edge(Vertex start, Vertex end, int weight) {
        startVertex = start;
        endVertex = end;
        this.weight = weight;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public int getWeight() {
        return weight;
    }
}
