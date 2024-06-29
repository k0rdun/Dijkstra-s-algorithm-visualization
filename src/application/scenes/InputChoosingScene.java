package application.scenes;

import javax.swing.*;

import static application.screenBuilder.ScreenBuilder.createButton;

public class InputChoosingScene extends Scene {
    @Override
    public void create(JFrame frame) {
        // Создаём кнопку
        JButton button = createButton("Клавиатура", 30, 0, 0, 252, 84);
        frame.getContentPane().add(button);
    }
}
