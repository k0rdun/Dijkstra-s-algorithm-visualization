package application.scenes;

import static application.screenBuilder.ScreenBuilder.createButton;

import java.awt.*;
import javax.swing.*;

import application.Application;
import application.Screens;

public class GraphicInputScene extends Scene {
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
            app.changeScreen(Screens.ALGORITHM_VISUALISATION);
        });
    }
}
