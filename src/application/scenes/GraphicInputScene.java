package application.scenes;

import static application.screenBuilder.ScreenBuilder.createButton;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.*;

import application.Application;
import application.scenes.graph.Graph;

public class GraphicInputScene extends Scene {
    private int n = 0;
    private int startVertex = 0;
    private int[][] matrix = null;

    @Override
    public void create(JFrame frame, Application app) {
        JLabel label = new JLabel("Графический ввод");
        label.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 15));
        label.setBounds(600, 32, 150, 18);
        frame.getContentPane().add(label);

        // Визуализируем существующий граф
        Graph graph = new Graph(n, matrix, 350, 198, 150);
        graph.setStartVertex(startVertex);
        graph.setVertexRadius(8);
        graph.setFrame(frame);
        graph.setBounds(50, 50, 700, 396);
        graph.setBackground(Color.WHITE);
        graph.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        // Добавляем возможность изменения графа
        MouseListener graphChanger = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    graph.leftMouseClick(e.getX(), e.getY());
                    SwingUtilities.updateComponentTreeUI(frame);
                } else if(e.getButton() == MouseEvent.BUTTON3) {
                    graph.rightMouseClick(e.getX(), e.getY());
                    SwingUtilities.updateComponentTreeUI(frame);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
        graph.addMouseListener(graphChanger);

        frame.getContentPane().add(graph);

        // Создаём поле ввода значения стартовой вершины
        JTextPane startVertexEditor = new JTextPane();
        startVertexEditor.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 12));
        startVertexEditor.setBounds(50, 20, 100, 25);
        startVertexEditor.setBackground(Color.WHITE);
        startVertexEditor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        startVertexEditor.setToolTipText("Номер стартовой вершины");
        startVertexEditor.setText(Integer.toString(graph.getStartVertex()));
        frame.getContentPane().add(startVertexEditor);

        // Создаём кнопку
        JButton button = createButton("Визуализация алгоритма", 20, 258, 466, 288, 84);
        button.addActionListener(e ->  {
            try {
                String input = startVertexEditor.getText();
                int startVertexNumber = Integer.parseInt(input);
                if (graph.getGraphSize() > 0 && graph.setStartVertex(startVertexNumber)) {
                    app.visualizeAlgorithm(graph);
                    graph.removeMouseListener(graphChanger);
                }
            } catch(NumberFormatException exc) {}
        });
        frame.getContentPane().add(button);
    }

    public void setGraph(int n, int startVertex, int[][] graph){
        this.n = n;
        this.startVertex = startVertex;
        this.matrix = graph;
    }
}
