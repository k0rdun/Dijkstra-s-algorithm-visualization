package application.scenes;

import javax.swing.*;

import static application.screenBuilder.ScreenBuilder.createButton;

public class StartScene extends Scene {
    @Override
    public void create(JFrame frame) {
        // Создаём кнопку
        JButton button = createButton("Начать", 30, 274, 342, 252, 84);
        button.addActionListener(e -> {

        });

        frame.getContentPane().add(button);
    }
}
