package application.scenes.graph.graphParts;

import java.awt.*;

public class Vertex {
    private int x;
    private int y;
    private int number;

    public Vertex(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNumber() {
        return number;
    }

    public void move(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public void changeNumber(int newNumber) {
        number = newNumber;
    }
}
