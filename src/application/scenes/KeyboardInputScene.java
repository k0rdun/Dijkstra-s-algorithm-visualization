package application.scenes;

import application.Application;
import application.Screens;

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

        // Создаём кнопку
        JButton button = createButton("Графический ввод", 20, 290, 466, 220, 84);
        frame.getContentPane().add(button);
        button.addActionListener(e ->  {
            app.changeScreen(Screens.GRAPHICAL_INPUT);
        });

        // Создаём поле для ввода
        JScrollPane inputField = createInputField(
                "Введите размер и матрицу смежности графа",
                50, 50, 700, 396
        );
        frame.getContentPane().add(inputField);
    }
}
