package application.scenes;

import static application.screenBuilder.ScreenBuilder.createButton;

import java.awt.*;
import javax.swing.*;

import application.Application;
import application.dijkstra.Dijkstra;
import application.dijkstra.DijkstraVisualizer;

public class AlghorimtVisualisationScene extends Scene {
    private final int n;
    private final int[][] matrix;

    public AlghorimtVisualisationScene(int n, int[][] matrix) {
        this.n = n;
        this.matrix = matrix;
    }

    @Override
    public void create(JFrame frame, Application app) {
        JLabel label_one = new JLabel("Граф");
        label_one.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 15));
        label_one.setBounds(50, 32, 50, 18);
        frame.getContentPane().add(label_one);

        JLabel label_two = new JLabel("Информация");
        label_two.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 15));
        label_two.setBounds(640, 32, 110, 18);
        frame.getContentPane().add(label_two);

        JLabel label_three = new JLabel("Таблица значений");
        label_three.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 15));
        label_three.setBounds(50, 286, 150, 18);
        frame.getContentPane().add(label_three);

        // Создаём кнопку
        JButton button = createButton("Следующий шаг", 20, 299, 466, 202, 84);
        frame.getContentPane().add(button);

        DijkstraVisualizer visualizer = new DijkstraVisualizer();
        Dijkstra algorithm = new Dijkstra(n, matrix, 0, visualizer);

        button.addActionListener(e -> {
            algorithm.nextStep();
        });
    }
}
