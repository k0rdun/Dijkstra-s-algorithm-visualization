package application.scenes.graph.graphParts;

import java.awt.*;

public class Edge {
    private final Vertex startVertex;
    private final Vertex endVertex;

    public Edge(Vertex start, Vertex end) {
        startVertex = start;
        endVertex = end;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }
}
