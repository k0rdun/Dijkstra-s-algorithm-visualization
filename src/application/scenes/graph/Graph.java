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
            // Отрисовываем вершины
            g2d.fillOval(
                    (int) ((vertex.getX() - vertexRadius) * multiplier),
                    (int) ((vertex.getY() - vertexRadius) * multiplier),
                    (int) (2 * vertexRadius * multiplier),
                    (int) (2 * vertexRadius * multiplier)
            );
        }
        int x1, y1, x2, y2, d, h;
        for(Edge edge : edges) {
            // Получаем координаты рёбер
            x1 = (int) (edge.getStartVertex().getX() * multiplier);
            y1 = (int) (edge.getStartVertex().getY() * multiplier);
            x2 = (int) (edge.getEndVertex().getX() * multiplier);
            y2 = (int) (edge.getEndVertex().getY() * multiplier);
            // Отрисовываем ребро
            g2d.drawLine(x1, y1, x2, y2);
            // Отрисовываем направление ребра
            d = (int) (vertexRadius * 2 * multiplier * 1.5);
            h = (int) (vertexRadius / 2 * multiplier * 1.5);
            // Расстояния между точками
            int dx = x2 - x1, dy = y2 - y1;
            // Длина стрелки
            double D = Math.sqrt(dx * dx + dy * dy);
            double xm = D - d, xn = xm, ym = h, yn = -h, x;
            double sin = dy / D, cos = dx / D;

            x = xm * cos - ym * sin + x1;
            ym = xm * sin + ym * cos + y1;
            xm = x;

            x = xn * cos - yn * sin + x1;
            yn = xn * sin + yn * cos + y1;
            xn = x;

            // Отрисовываем направление ребра
            g2d.fillPolygon(
                    new int[] {x2, (int) xm, (int) xn},
                    new int[] {y2, (int) ym, (int) yn},
                    3
            );
        }
    }
}
