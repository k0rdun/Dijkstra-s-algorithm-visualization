package application.scenes.graph;

import application.scenes.graph.graphParts.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graph extends JPanel {
    private ArrayList<Vertex> vertexes = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    private int vertexRadius = 5;
    private double multiplier = 1.0;

    public Graph(int n, int[][] graph, int centerX, int centerY, int radius) {
        double a = (2 * Math.PI) / (n);
        for(int i = 0; i < n; i++){
            addVertex(
                    (int) (centerX - radius * Math.cos(a * ((double) i))),
                    (int) (centerY + radius * Math.sin(a * ((double) i))),
                    i
            );
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(graph[i][j] != 0) {
                    addEdge(i, j);
                }
            }
        }
    }

    public void setVertexRadius(int radius) {
        vertexRadius = radius;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public void addVertex(int x, int y, int number) {
        vertexes.add(new Vertex(x, y, number));
    }

    public void addEdge(int number1, int number2) {
        Vertex vertex1 = null;
        Vertex vertex2 = null;
        for(Vertex vertex : vertexes) {
            if(vertex.getNumber() == number1) {
                vertex1 = vertex;
            }
            if(vertex.getNumber() == number2) {
                vertex2 = vertex;
            }
        }
        if(vertex1 != null && vertex2 != null) {
            edges.add(new Edge(vertex1, vertex2));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2.5F));
        for(Vertex vertex : vertexes) {
            g2d.fillOval(
                    (int) ((vertex.getX() - vertexRadius) * multiplier),
                    (int) ((vertex.getY() - vertexRadius) * multiplier),
                    (int) (2 * vertexRadius * multiplier),
                    (int) (2 * vertexRadius * multiplier)
            );
        }
        for(Edge edge : edges) {
            g2d.drawLine(
                    (int) (edge.getStartVertex().getX() * multiplier),
                    (int) (edge.getStartVertex().getY() * multiplier),
                    (int) (edge.getEndVertex().getX() * multiplier),
                    (int) (edge.getEndVertex().getY() * multiplier)
            );

        }
    }
}
