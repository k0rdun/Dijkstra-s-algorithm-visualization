package application;

import application.scenes.*;

import javax.swing.*;

import static application.screenBuilder.ScreenBuilder.createFrame;

public class Application {
    private final JFrame frame;

    public Application() {
        frame = createFrame("Алгоритм Дэйкстры", 800, 600);
    }

    public void changeScreen(Screens screen) {
        frame.getContentPane().removeAll();
        Scene scene = null;
        switch(screen) {
            case Screens.START_SCREEN:
                scene = new StartScene();
                break;
            case Screens.INPUT_CHOOSING:
                scene = new InputChoosingScene();
        }
        if(scene != null) {
            scene.create(frame);
        }
    }

    public void start() {
        changeScreen(Screens.START_SCREEN);
        frame.setVisible(true);
    }
}
