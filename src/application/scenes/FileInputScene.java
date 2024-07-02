package application.scenes;

import application.Application;

import java.awt.*;
import java.io.*;
import javax.swing.*;

import static application.screenBuilder.ScreenBuilder.*;

public class FileInputScene extends KeyboardInputScene {
    @Override
    public void create(JFrame frame, Application app) {
        JLabel label = new JLabel("Ввод из файла");
        label.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 15));
        label.setBounds(635, 32, 120, 18);
        frame.getContentPane().add(label);

        // Создаём поле для ввода
        JScrollPane inputField = createInputField(
                "Введите название файла",
                50, 50, 700, 396
        );
        frame.getContentPane().add(inputField);

        // Создаём кнопку
        JButton button = createButton("Графический ввод", 20, 290, 466, 220, 84);
        frame.getContentPane().add(button);

        button.addActionListener(e ->  {
            // Получаем JTextArea из JScrollPane
            JTextArea inputText = (JTextArea) inputField.getViewport().getView();
            // Получаем граф из поля ввода
            Pair pair = checkFileInput(inputText.getText());
            if(pair == null) {
                // Если был некорректный ввод, то выводим сообщение об ошибке
                frame.getContentPane().add(errorMessage());
                SwingUtilities.updateComponentTreeUI(frame);
            } else {
                // Если удалось считать корректный граф, то переходим к графическому вводу
                app.graphicInput(pair.graph.length, pair.startVertex, pair.graph);
            }
        });
    }

    public Pair checkFileInput(String input) {
        try(BufferedReader br = new BufferedReader(new FileReader(input))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return checkTextInput(sb.toString());
        } catch(IOException e){
            return null;
        }
    }
}
