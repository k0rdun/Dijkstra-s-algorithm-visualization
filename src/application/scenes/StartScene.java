package application.scenes;

import application.Application;
import application.Screens;

import java.awt.*;
import javax.swing.*;

import static application.screenBuilder.ScreenBuilder.createButton;

public class StartScene extends Scene {
    @Override
    public void create(JFrame frame, Application app) {
        JLabel label_one = new JLabel("Алгоритм Дейкстры для поиска");
        label_one.setFont(new Font("Inter", Font.BOLD, 30));
        label_one.setBounds(160, 130, 479, 36);
        frame.getContentPane().add(label_one);

        JLabel label_two = new JLabel("кратчайших путей в графе");
        label_two.setFont(new Font("Inter", Font.BOLD, 30));
        label_two.setBounds(205, 166, 409, 36);
        frame.getContentPane().add(label_two);

        // Создаём кнопку
        JButton button = createButton("Начать", 30, 274, 342, 252, 84);
        button.addActionListener(e -> {
            app.changeScreen(Screens.INPUT_CHOOSING);
        });

        frame.getContentPane().add(button);
    }
}
