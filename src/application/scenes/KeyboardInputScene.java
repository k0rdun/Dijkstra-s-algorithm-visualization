package application.scenes;

import application.Application;

import java.awt.*;
import java.awt.im.InputContext;
import java.util.Arrays;
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
                "Введите размер и матрицу смежности графа",
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
            int[][] graph = checkTextInput(inputText.getText());
            if(graph == null) {
                // Если был некорректный ввод, то выводим сообщение об ошибке
                frame.getContentPane().add(errorMessage());
                SwingUtilities.updateComponentTreeUI(frame);
            } else {
                // Если удалось считать корректный граф, то переходим к графическому вводу
                app.graphicInput(graph.length, graph);
            }
        });
    }

    public int[][] checkTextInput(String inputText) {
        String[] input = inputText.split("\n");
        try {
            // Считываем размер графа
            int n = Integer.parseInt(input[0]);
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
                }
            }
            //System.out.println(Arrays.deepToString(graph));
            return graph;
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
