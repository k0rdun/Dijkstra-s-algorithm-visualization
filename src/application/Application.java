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
            case ALGORITHM_VISUALISATION:
                scene = new AlghorimtVisualisationScene();
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
}
