package application.scenes;

import application.Application;
import application.Screens;

import javax.swing.*;
import java.awt.event.*;

import static application.screenBuilder.ScreenBuilder.createButton;

public class InputChoosingScene extends Scene {
    @Override
    public void create(JFrame frame, Application app) {
        // Создаём кнопку
        JButton button = createButton("Клавиатура", 30, 0, 0, 252, 84);
        frame.getContentPane().add(button);

        button.addActionListener(e ->  {
                app.changeScreen(Screens.KEYBOARD_INPUT);
        });
    }
}
