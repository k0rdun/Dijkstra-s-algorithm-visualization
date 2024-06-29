package application.scenes;

import application.Application;

import javax.swing.*;

import static application.screenBuilder.ScreenBuilder.*;

public class KeyboardInputScene extends Scene {
    @Override
    public void create(JFrame frame, Application app) {
        // Создаём кнопку
        JButton button = createButton("Графический ввод", 20, 290, 466, 220, 84);
        frame.getContentPane().add(button);

        // Создаём поле для ввода
        JScrollPane inputField = createInputField(
                "Введите размер и матрицу смежности графа",
                50, 50, 700, 396
        );
        frame.getContentPane().add(inputField);
    }
}
