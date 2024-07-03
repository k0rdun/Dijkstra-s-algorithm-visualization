package application.dijkstra;

import application.scenes.graph.Graph;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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

    public void printStartInfo(int n, int start, int[] distance, int[] parents){
        for(int i = 0; i < n; i++) {
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

    class CustomRenderer extends DefaultTableCellRenderer {
        private Color color;
        CustomRenderer(Color color) {
            super();
            this.color = color;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setForeground(color);
            return c;
        }
    }

    public void colorTable(int n, int vertex, boolean[] visited, int[] distance, int[] lastDistance) {
        for(int i = 0; i < n; i++){
            if(i == vertex){
                table.getColumnModel().getColumn(i + 1).setCellRenderer(new CustomRenderer(Color.BLUE));
            } else if(visited[i]) {
                table.getColumnModel().getColumn(i + 1).setCellRenderer(new CustomRenderer(Color.GRAY));
            } else if(distance[i] != lastDistance[i]) {
                table.getColumnModel().getColumn(i + 1).setCellRenderer(new CustomRenderer(Color.GREEN));
            } else {
                table.getColumnModel().getColumn(i + 1).setCellRenderer(new CustomRenderer(Color.BLACK));
            }
        }
    }

    public void printTable(int n, int vertex, int start, int[] distance, int[] parents, boolean[] visited, int[] lastDistances){
        colorTable(n, vertex, visited, distance, lastDistances);
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
