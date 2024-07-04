package application.scenes.graph.graphParts;

import java.awt.*;

public class Edge {
    private final Vertex startVertex;
    private final Vertex endVertex;
    private final int weight;
    private Color color;

    public Edge(Vertex start, Vertex end, int weight, Color color) {
        startVertex = start;
        endVertex = end;
        this.weight = weight;
        this.color = color;
    }

    public void setColor(Color newColor) {
        color = newColor;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public void draw(Graphics2D g2d, double multiplier, int arrowLength, int arrowWidth) {
        g2d.setColor(color);
        // Получаем координаты рёбер
        int x1 = (int) (startVertex.getX() * multiplier);
        int y1 = (int) (startVertex.getY() * multiplier);
        int x2 = (int) (endVertex.getX() * multiplier);
        int y2 = (int) (endVertex.getY() * multiplier);
        // Отрисовываем ребро
        g2d.drawLine(x1, y1, x2, y2);
        // Отрисовываем направление ребра
        int d = arrowLength;
        int h = arrowWidth;
        // Расстояния между точками
        int dx = x2 - x1, dy = y2 - y1;
        // Длина стрелки
        double D = Math.sqrt(dx * dx + dy * dy) / multiplier;
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        // Отрисовываем направление ребра
        g2d.fillPolygon(
                new int[] {x2, (int) xm, (int) xn},
                new int[] {y2, (int) ym, (int) yn},
                3
        );
    }

    public void drawWeight(Graphics2D g2d, double multiplier) {
        // Получаем координаты рёбер
        int x1 = (int) (startVertex.getX() * multiplier);
        int y1 = (int) (startVertex.getY() * multiplier);
        int x2 = (int) (endVertex.getX() * multiplier);
        int y2 = (int) (endVertex.getY() * multiplier);
        // Расстояния между точками
        int dx = x2 - x1, dy = y2 - y1;
        // Расстояние от вершины до числа
        double D = Math.sqrt(dx * dx + dy * dy);
        // Вычисляем координаты
        int d = (int) (D / 4 * 3);
        double xm = D - d, ym, x;
        double sin = dy / D, cos = dx / D;
        x = xm * cos + x1;
        ym = xm * sin + y1;
        xm = x;

        // Отрисовываем ребро
        int textWidth = g2d.getFontMetrics().stringWidth(Integer.toString(weight));
        int textHeight = (int) (14 * multiplier);
        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) xm, (int) ym - textHeight, textWidth, textHeight);
        g2d.setColor(Color.BLACK);
        g2d.drawString(
                Integer.toString(weight),
                (int) xm,
                (int) ym
        );
    }
}
