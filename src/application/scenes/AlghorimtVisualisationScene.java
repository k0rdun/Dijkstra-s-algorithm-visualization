package application.scenes;

import static application.screenBuilder.ScreenBuilder.createButton;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import application.Application;
import application.dijkstra.Dijkstra;
import application.dijkstra.DijkstraVisualizer;
import application.scenes.graph.Graph;

public class AlghorimtVisualisationScene extends Scene {
    private final int n;
    private final int[][] matrix;
    private Graph graph;

    public AlghorimtVisualisationScene(Graph graph) {
        this.graph = graph;
        matrix = graph.getGraph();
        n = matrix.length;
    }

    @Override
    public void create(JFrame frame, Application app) {
        JLabel label_one = new JLabel("Граф");
        label_one.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 15));
        label_one.setBounds(50, 32, 50, 18);
        frame.getContentPane().add(label_one);

        JLabel label_two = new JLabel("Информация");
        label_two.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 15));
        label_two.setBounds(645, 32, 110, 18);
        frame.getContentPane().add(label_two);

        JLabel label_three = new JLabel("Таблица значений");
        label_three.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 15));
        label_three.setBounds(50, 286, 150, 18);
        frame.getContentPane().add(label_three);


        graph.setMultiplier(0.5);
        graph.resetChangesVisualization();
        graph.setBounds(50, 50, 350, 208);

        frame.getContentPane().add(graph);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(462, 50, 288, 208);
        textArea.setBackground(Color.WHITE);
        textArea.setEnabled(false);

        JScrollPane panel_info = new JScrollPane(textArea);
        panel_info.setBounds(462, 50, 288, 208);
        panel_info.setBackground(Color.WHITE);
        panel_info.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        frame.getContentPane().add(panel_info);
        
        JTable table = new JTable(3, 1);
        table.setTableHeader(null);
        table.setEnabled(false);
        table.setRowHeight(44);
        table.setBackground(Color.WHITE);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getModel().setValueAt("№ вершины", 0, 0); 
        table.getModel().setValueAt("Расстояние", 1, 0);
        table.getModel().setValueAt("Родитель", 2, 0);

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        for (int i = 0; i < n; i++) {
            tableModel.addColumn("Column " + i);
        }
                
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 304, 700, 142);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        scrollPane.setBackground(Color.WHITE);
        frame.getContentPane().add(scrollPane);

        // Создаём кнопку
        JButton button = createButton("Следующий шаг", 20, 299, 466, 202, 84);
        frame.getContentPane().add(button);

        DijkstraVisualizer visualizer = new DijkstraVisualizer(table, textArea);
        Dijkstra algorithm = new Dijkstra(n, matrix, graph.getStartVertex(), visualizer);

        button.addActionListener(e -> {
            algorithm.nextStep();
        });
    }
}
