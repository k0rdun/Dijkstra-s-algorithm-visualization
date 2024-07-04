package application.scenes;

import application.Application;

import java.awt.*;
import javax.swing.*;

import static application.screenBuilder.ScreenBuilder.*;

public class KeyboardInputScene extends Scene {
    @Override
    public void create(JFrame frame, Application app) {
        JLabel label = new JLabel("Ввод с клавиатуры");
        label.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 15));
        label.setBounds(595, 32, 160, 18);
        frame.getContentPane().add(label);

        // Создаём поле для ввода
        JScrollPane inputField = createInputField(
                "Введите размер, стартовую вершину и матрицу смежности графа",
                50, 50, 700, 396
        );
        frame.getContentPane().add(inputField);

        // Создаём кнопку
        JButton button = createButton("Графический ввод", 20, 290, 466, 220, 84);
        frame.getContentPane().add(button);

        button.addActionListener(e -> {
            // Получаем JTextArea из JScrollPane
            JTextArea inputText = (JTextArea) inputField.getViewport().getView();
            // Получаем граф из поля ввода
            Pair pair = checkTextInput(inputText.getText());
            if(pair == null) {
                // Если был некорректный ввод, то выводим сообщение об ошибке
                frame.getContentPane().add(errorMessage());
                SwingUtilities.updateComponentTreeUI(frame);
            } else {
                // Если удалось считать корректный граф, то переходим к графическому вводу
                app.graphicInput(pair.graph.length, pair.startVertex, pair.graph);
            }
        });
    }

    public class Pair {
        int startVertex;
        int[][] graph;
    }

    public Pair checkTextInput(String inputText) {
        if(inputText.isEmpty()) {
            Pair pair = new Pair();
            pair.startVertex = 0;
            pair.graph = new int[0][0];
            return pair;
        }
        String[] input = inputText.split("\n");
        try {
            // Считываем размер графа и стартовую вершину
            String[] info = input[0].split(" ");
            if(info.length != 2) { return null; }
            int n = Integer.parseInt(info[0]);
            Pair pair = new Pair();
            pair.startVertex = Integer.parseInt(info[1]);
            if(pair.startVertex < 0 || pair.startVertex >= n) { return null; }
            // Проверяем корректность входных данных
            if(n > 25 || n <= 0 || input.length != n + 1) { return null; }
            // Создаём массив для хранения матрицы смежности графа
            int[][] graph = new int[n][n];
            int current;
            String[] line;
            for(int i = 0; i < n; i++){
                // Разбиваем очередную строку на массив элементов по символу пробела
                line = input[i + 1].split(" ");
                if(line.length != n) { return null; }
                for(int j = 0; j < n; j++){
                    current = Integer.parseInt(line[j]);
                    graph[i][j] = current;
                    if((current < 0) || (j == i && graph[i][j] != 0)) { return null; }
                }
            }
            pair.graph = graph;
            return pair;
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public JLabel errorMessage() {
        JLabel label = new JLabel("Некорректный ввод");
        label.setFont(new Font("Inter", Font.BOLD, 15));
        label.setBounds(50, 32, 170, 18);
        label.setForeground(Color.RED);
        return label;
    }
}
