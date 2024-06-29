package application.scenes;

import application.Application;

import java.awt.*;
import javax.swing.*;

import static application.screenBuilder.ScreenBuilder.*;

public class FileInputScene extends Scene {
    @Override
    public void create(JFrame frame, Application app) {
        JLabel label = new JLabel("Ввод из файла");
        label.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 15));
        label.setBounds(635, 32, 120, 18);
        frame.getContentPane().add(label);

        // Создаём кнопку
        JButton button = createButton("Графический ввод", 20, 290, 466, 220, 84);
        frame.getContentPane().add(button);

        // Создаём поле для ввода
        JScrollPane inputField = createInputField(
                "Введите название файла",
                50, 50, 700, 396
        );
        frame.getContentPane().add(inputField);
    }
}
