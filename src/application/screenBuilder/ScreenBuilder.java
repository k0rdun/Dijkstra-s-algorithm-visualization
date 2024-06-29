package application.screenBuilder;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class ScreenBuilder {
    public static JFrame createFrame(String title, int width, int height){
        // Создаём окно с названием
        JFrame frame = new JFrame(title);
        // Задаём размеры окна
        frame.setSize(width, height);
        // Делаем его неизменяемым
        frame.setResizable(false);
        // Заливаем белым цветом фон
        frame.getContentPane().setBackground(Color.WHITE);
        // Задаём завершение приложения при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Располагаем окно в центре экрана при запуске приложения
        frame.setLocationRelativeTo(null);
        // Чтобы мы могли произвольно расставлять элементы на экране
        frame.setLayout(null);

        return frame;
    }

    public static JButton createButton(String text, int text_size, int x, int y, int width, int height) {
        // Создаём кнопку
        JButton button = new JButton();
        // Задаём расположение и размеры кнопки
        button.setBounds(x, y, width, height);
        // Задаём цвет заливки кнопки
        button.setBackground(Color.WHITE);
        // Задаём границу кнопки
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        // Изменяем текст кнопки
        JLabel label = new JLabel(text);
        label.setFont(new Font("Inter", Font.BOLD, text_size));
        // Создаём макет, с расположение по оси X
        button.setLayout(new BoxLayout(button, BoxLayout.X_AXIS));
        // Располагаем надпись в центре
        button.add(Box.createHorizontalGlue());
        button.add(label);
        button.add(Box.createHorizontalGlue());

        return button;
    }
}
