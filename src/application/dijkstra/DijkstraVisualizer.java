package application.dijkstra;

import application.scenes.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class DijkstraVisualizer {
    private JTable table;
    private JTextArea textArea;
    private Graph graph;

    private int step = 1;

    public DijkstraVisualizer(JTable table, JTextArea textArea, Graph graph){
        this.table = table;
        this.textArea = textArea;
        this.graph = graph;
    }

    public void printStartInfo(int start, int[] distance, int[] parents){
        for(int i = 0; i < distance.length; i++) {
            table.setValueAt(i, 0, i + 1);
            table.setValueAt(distance[i] == -1 ? "inf" : distance[i], 1, i + 1);
            table.setValueAt(parents[i] == -1 ? "" : parents[i], 2, i + 1);
        }
        textArea.setText("Стартовая вершина - " + start);
    }

    public void printInfo(int vertex){
        textArea.setText(textArea.getText() + "\nШаг №" + step++);
        textArea.setText(textArea.getText() + "\nВершина с min расстоянием - " + vertex);
        graph.resetColors();
    }

    public void printInfoVertexes(int vertex1, int vertex2, int newValue, int oldValue){
        textArea.setText(textArea.getText() + "\n" + vertex1 + " -> " + vertex2 + ": dist = " + newValue +
                ((newValue < oldValue || oldValue == -1) ? (" < ") : (" >= ")) + (oldValue == -1 ? "inf" : oldValue));
        graph.setEdgeColor(vertex1, vertex2, (newValue < oldValue || oldValue == -1) ? (Color.GREEN) : (Color.RED));
    }

    public void printTable(int n, int[] distance, int[] parents, int vertex, int start){
        for(int i = 0; i < n; i++) {
            table.setValueAt(distance[i] == -1 ? "inf" : distance[i], 1, i + 1);
            table.setValueAt(parents[i] == -1 ? "" : parents[i], 2, i + 1);
        }
        graph.setVertexColor(vertex, Color.BLUE);
        while(vertex != start) {
            graph.setEdgeColor(parents[vertex], vertex, Color.CYAN);
            vertex = parents[vertex];
            graph.setVertexColor(vertex, Color.CYAN);
        }
    }

    public void printEnd(){
        textArea.setText(textArea.getText() + "\nРабота завершена!");
        graph.resetColors();
    }
}
