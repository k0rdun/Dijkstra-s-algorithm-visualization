package application.scenes;

import application.Application;
import application.Screens;

import java.awt.*;
import javax.swing.*;

import static application.screenBuilder.ScreenBuilder.createButton;

public class InputChoosingScene extends Scene {
    @Override
    public void create(JFrame frame, Application app) {
        JLabel label = new JLabel("Выберите способ ввода");
        label.setFont(new Font("Inter", Font.BOLD, 39));
        label.setBounds(159, 177, 476, 48);
        frame.getContentPane().add(label);

        JButton button_keyboard = createButton("Клавиатура", 20, 50, 342, 150, 84);
        frame.getContentPane().add(button_keyboard);
        button_keyboard.addActionListener(e ->  {
            app.changeScreen(Screens.KEYBOARD_INPUT);
        });

        JButton button_file = createButton("Файл", 20, 325, 342, 150, 84);
        frame.getContentPane().add(button_file);
        button_file.addActionListener(e ->  {
            app.changeScreen(Screens.FILE_INPUT);
        });

        JButton button_graphic = createButton("Графический", 20, 600, 342, 150, 84);      
        frame.getContentPane().add(button_graphic);

        
    }
}
