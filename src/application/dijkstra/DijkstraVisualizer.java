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

    private int step = 1;

    public DijkstraVisualizer(JTable table){
        this.table = table;
    }

    public void printStartInfo(int start, int[] distance, int[] parents){
        for(int i = 0; i < distance.length; i++) {
            table.setValueAt(i, 0, i + 1);
            table.setValueAt(distance[i] == -1 ? "inf" : distance[i], 1, i + 1);
            table.setValueAt(parents[i] == -1 ? "" : parents[i], 2, i + 1);
        }
        System.out.println("Начальные значения:");
        System.out.println("Стартовая вершина - " + start);
        System.out.println("Кратчайшие расстояния: " + Arrays.toString(distance));
        System.out.println("Вершины-родители:      " + Arrays.toString(parents));
    }

    public void printInfo(int vertex){
        System.out.println("\nШаг №" + step++);
        System.out.println("Вершина с min расстоянием - " + vertex);
    }

    public void printInfoVertexes(int vertex1, int vertex2, int newValue, int oldValue){
        System.out.println(vertex1 + " -> " + vertex2 + ": dist = " + newValue +
                ((newValue < oldValue || oldValue == -1) ? (ANSI_BLUE + " < ") : (ANSI_RED + " >= ")) +
                ANSI_RESET + oldValue);
    }

    public void printTable(int n, int[] distance, int[] parents, boolean[] visited, int[] lastDistance){
        System.out.print("Кратчайшие расстояния: ");
        for(int i = 0; i < n; i++){
            System.out.print(visited[i] ? ANSI_GREEN : (distance[i] != lastDistance[i]) ? ANSI_CYAN : "");
            System.out.print(distance[i] + ANSI_RESET + "  ");
        }
        System.out.print("\nВершины-родители:      ");
        for(int i = 0; i < n; i++){
            System.out.print(visited[i] ? ANSI_GREEN : (distance[i] != lastDistance[i]) ? ANSI_CYAN : "");
            System.out.print(parents[i] + ANSI_RESET + "  ");
        }
        System.out.println();
    }

    public void printEnd(){
        System.out.println("\nРабота завершена!");
    }
}
