package application.dijkstra;

import javax.swing.*;
import java.util.Arrays;

public class DijkstraVisualizer {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private JTable table;
    private JTextArea textArea;

    private int step = 1;

    public DijkstraVisualizer(JTable table, JTextArea textArea){
        this.table = table;
        this.textArea = textArea;
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
        textArea.setText("Шаг №" + step++);
        textArea.setText(textArea.getText() + "\nВершина с min расстоянием - " + vertex);
    }

    public void printInfoVertexes(int vertex1, int vertex2, int newValue, int oldValue){
        textArea.setText(textArea.getText() + "\n" + vertex1 + " -> " + vertex2 + ": dist = " + newValue +
                ((newValue < oldValue || oldValue == -1) ? (" < ") : (" >= ")) + (oldValue == -1 ? "inf" : oldValue));
    }

    public void printTable(int n, int[] distance, int[] parents, boolean[] visited, int[] lastDistance){
        for(int i = 0; i < distance.length; i++) {
            table.setValueAt(distance[i] == -1 ? "inf" : distance[i], 1, i + 1);
            table.setValueAt(parents[i] == -1 ? "" : parents[i], 2, i + 1);
        }
    }

    public void printEnd(){
        textArea.setText("Работа завершена!");
    }
}
