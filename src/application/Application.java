package application;

import javax.swing.*;

import application.scenes.*;
import static application.screenBuilder.ScreenBuilder.createFrame;

public class Application {
    private final JFrame frame;

    public Application() {
        frame = createFrame("Алгоритм Дэйкстры", 800, 600);
    }

    public void changeScreen(Screens screen) {
        Scene scene = null;
        switch(screen) {
            case START_SCREEN:
                scene = new StartScene();
                break;
            case INPUT_CHOOSING:
                scene = new InputChoosingScene();
                break;
            case KEYBOARD_INPUT:
                scene = new KeyboardInputScene();
                break;
            case FILE_INPUT:
                scene = new FileInputScene();
                break;
            case GRAPHICAL_INPUT:
                scene = new GraphicInputScene();
                break;
            default:
                break;
        }
        if(scene != null) {
            frame.getContentPane().removeAll();
            scene.create(frame, this);
            SwingUtilities.updateComponentTreeUI(frame);
        }
    }

    public void start() {
        frame.setVisible(true);
        changeScreen(Screens.START_SCREEN);
    }

    public void graphicInput(int n, int[][] graph) {
        GraphicInputScene scene = new GraphicInputScene();
        frame.getContentPane().removeAll();
        scene.setGraph(n, graph);
        scene.create(frame, this);
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void visualizeAlgorithm(int n, int[][] matrix) {
        AlghorimtVisualisationScene scene = new AlghorimtVisualisationScene(n, matrix);
        frame.getContentPane().removeAll();
        scene.create(frame, this);
        SwingUtilities.updateComponentTreeUI(frame);
    }
}
