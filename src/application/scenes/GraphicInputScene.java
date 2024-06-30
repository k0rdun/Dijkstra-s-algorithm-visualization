package application.scenes;

import static application.screenBuilder.ScreenBuilder.createButton;

import java.awt.*;
import javax.swing.*;

import application.Application;
import application.Screens;
import application.scenes.graph.Graph;

public class GraphicInputScene extends Scene {
    private int n = 0;
    private int[][] matrix = null;

    @Override
    public void create(JFrame frame, Application app) {
        JLabel label = new JLabel("Графический ввод");
        label.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 15));
        label.setBounds(600, 32, 150, 18);
        frame.getContentPane().add(label);

        // Создаём кнопку
        JButton button = createButton("Визуализация алгоритма", 20, 258, 466, 288, 84);
        frame.getContentPane().add(button);
        button.addActionListener(e ->  {
            app.visualizeAlgorithm(n, matrix);
        });

        // Визуализируем существующий граф
        Graph graph = new Graph(n, matrix, 350, 198, 150);
        graph.setVertexRadius(8);
        graph.setMultiplier(0.5);
        graph.setBounds(50, 50, 700, 396);
        graph.setBackground(Color.WHITE);
        graph.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        frame.getContentPane().add(graph);
    }

    public void setGraph(int n, int[][] graph){
        this.n = n;
        this.matrix = graph;
    }
}
