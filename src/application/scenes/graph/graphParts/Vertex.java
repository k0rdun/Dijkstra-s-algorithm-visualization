package application.scenes.graph.graphParts;

import java.awt.*;

public class Vertex {
    private int x;
    private int y;
    private int number;
    private Color color;

    public Vertex(int x, int y, int number, Color color) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.color = color;
    }

    public void move(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public void changeNumber(int newNumber) {
        number = newNumber;
    }

    public void setColor(Color newColor) {
        color = newColor;
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

    public Color getColor() {
        return color;
    }

    public void draw(Graphics2D g2d, double multiplier, int vertexRadius) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(
                (int) ((x - vertexRadius) * multiplier),
                (int) ((y - vertexRadius) * multiplier),
                (int) (2 * vertexRadius * multiplier),
                (int) (2 * vertexRadius * multiplier)
        );
        // Отрисовываем вершины
        g2d.setColor(color);
        g2d.drawOval(
                (int) ((x - vertexRadius) * multiplier),
                (int) ((y - vertexRadius) * multiplier),
                (int) (2 * vertexRadius * multiplier),
                (int) (2 * vertexRadius * multiplier)
        );
        // Нумерация вершин
        g2d.setColor(Color.BLACK);
        g2d.drawString(
                Integer.toString(number),
                (int) ((x - g2d.getFontMetrics().stringWidth(Integer.toString(number)) / 2) * multiplier - 2),
                (int) ((y + vertexRadius / 2) * multiplier)
        );
    }
}
