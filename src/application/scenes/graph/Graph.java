package application.scenes.graph;

import application.scenes.graph.graphParts.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Graph extends JPanel {
    private ArrayList<Vertex> vertexes = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    private int startVertex = 0;
    private int vertexRadius = 5;
    private double multiplier = 1.0;
    private Vertex selectedVertex = null;
    private boolean block = false;

    public Graph(int n, int[][] graph, int centerX, int centerY, int radius) {
        this.setLayout(null);
        double a = (2 * Math.PI) / (n);
        for(int i = 0; i < n; i++){
            addVertex(
                    (int) (centerX - radius * Math.cos(a * ((double) i))),
                    (int) (centerY + radius * Math.sin(a * ((double) i))),
                    i
            );
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(graph[i][j] != 0) {
                    addEdge(i, j, graph[i][j]);
                }
            }
        }
    }

    public void resetChangesVisualization() {
        selectedVertex = null;
        if(block) {
            this.remove(label);
            this.remove(textEditorScroll);
            block = false;
        }
    }

    public int[][] getGraph() {
        int[][] graph = new int[vertexes.size()][vertexes.size()];
        for(Edge edge : edges) {
            graph[edge.getStartVertex().getNumber()][edge.getEndVertex().getNumber()] = edge.getWeight();
        }
        return graph;
    }

    public boolean setStartVertex(int startVertex) {
        if(startVertex >= 0 && startVertex < vertexes.size()) {
            this.startVertex = startVertex;
            return true;
        }
        return false;
    }

    public int getStartVertex() {
        return startVertex;
    }

    public int getGraphSize() {
        return vertexes.size();
    }

    public void setVertexRadius(int radius) {
        vertexRadius = radius;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public void addVertex(int x, int y, int number) {
        vertexes.add(new Vertex(x, y, number, Color.DARK_GRAY));
    }

    public void addEdge(int number1, int number2, int weight) {
        Vertex vertex1 = null;
        Vertex vertex2 = null;
        for(Vertex vertex : vertexes) {
            if(vertex.getNumber() == number1) {
                vertex1 = vertex;
            }
            if(vertex.getNumber() == number2) {
                vertex2 = vertex;
            }
        }
        if(vertex1 != null && vertex2 != null) {
            System.out.println("Ребро " + vertex1.getNumber() + " -> " + vertex2.getNumber() + " добавлено (" + weight + ")");
            edges.add(new Edge(vertex1, vertex2, weight, Color.DARK_GRAY));
        }
    }

    private JLabel label;
    private JScrollPane textEditorScroll;

    public void askEdgeWeight(int number1, int number2) {
        // Проверяем, существует ли уже ребро инцидентное данным вершинам
        for(Edge edge : edges) {
            if(edge.getStartVertex().getNumber() == number1 && edge.getEndVertex().getNumber() == number2) {
                System.out.println("Такое ребро уже существует!");
                return;
            }
        }

        block = true;
        // Подсказка
        label = new JLabel("Введите вес ребра");
        label.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 12));
        label.setBounds(25, 10, 150, 15);
        this.add(label);
        // Создаём поле для ввода тектса
        JTextPane textEditor = new JTextPane();
        textEditor.setFont(new Font("Inter", Font.ITALIC + Font.BOLD, 12));
        // Добавляем ему возможность прокручивать и задаём то, как оно будет выглядеть
        textEditorScroll = new JScrollPane(textEditor);
        textEditorScroll.setBackground(Color.WHITE);
        textEditorScroll.setBounds(25, 25, 150, 50);
        textEditorScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        this.add(textEditorScroll);
        textEditor.requestFocus();

        Graph graph = this;
        KeyAdapter enter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER && block) {
                    System.out.println("Вес ребра введён");
                    block = false;
                    graph.remove(textEditorScroll);
                    graph.remove(label);
                    String input = textEditor.getText();
                    try {
                        int weight = Integer.parseInt(input);
                        if(weight > 0){
                            addEdge(number1, number2, weight);
                            SwingUtilities.updateComponentTreeUI(graph);
                        }
                    } catch(NumberFormatException exc) {}
                }
            }
        };
        // Делаем ввод значения по нажатию на enter
        textEditor.addKeyListener(enter);
    }

    public void deleteVertex(int number) {
        System.out.println("Вершина №" + number + " удалена");
        vertexes.remove(number);
        for(int i = 0; i < edges.size(); i++) {
            if(edges.get(i).getStartVertex().getNumber() == number ||
               edges.get(i).getEndVertex().getNumber() == number) {
                System.out.println(
                        "Ребро " + edges.get(i).getStartVertex().getNumber() + " -> " +
                                edges.get(i).getEndVertex().getNumber() + " удалено"
                );
                edges.remove(i);
                i--;
            }
        }
        for(int i = number; i < vertexes.size(); i++) {
            vertexes.get(i).changeNumber(i);
        }
    }

    public void deleteEdge(Vertex vertexStart, Vertex vertexEnd) {
        for(int i = 0; i < edges.size(); i++){
            if(edges.get(i).getStartVertex() == vertexStart &&
               edges.get(i).getEndVertex() == vertexEnd) {
                System.out.println("Ребро " + vertexStart.getNumber() + " -> " + vertexEnd.getNumber() + " удалено");
                edges.remove(i);
                return;
            }
        }
    }

    public void selectVertex(int number) {
        if(number < 0 || number >= vertexes.size()){
            return;
        // Выбор вершины
        } if(selectedVertex == null) {
            selectedVertex = vertexes.get(number);
            System.out.println("Вершина №" + number + " выбрана");
        // Удаление вершины
        } else if(selectedVertex.getNumber() == number) {
            deleteVertex(number);
            selectedVertex = null;
        // Добавление ребра
        } else {
            askEdgeWeight(selectedVertex.getNumber(), number);
            //addEdge(selectedVertex.getNumber(), number, 10);
            selectedVertex = null;
        }
    }

    private boolean checkVertexClick(int x, int y, boolean edgeRemove) {
        for(int i = 0; i < vertexes.size(); i++) {
            if((x - vertexes.get(i).getX()) * (x - vertexes.get(i).getX()) +
                    (y - vertexes.get(i).getY()) * (y - vertexes.get(i).getY()) <=
                    vertexRadius * vertexRadius) {
                if(edgeRemove) {
                    deleteEdge(selectedVertex, vertexes.get(i));
                    selectedVertex = null;
                } else {
                    selectVertex(i);
                }
                return true;
            }
        }
        return false;
    }

    public void leftMouseClick(int x, int y) {
        // Блокируем действие
        if(block) {
            return;
        }
        // Выбор вершины
        if(checkVertexClick(x, y, false)) {
            return;
        }
        // Перемещение выбранной вершины
        if(selectedVertex != null) {
            System.out.println("Вершина №" + selectedVertex.getNumber() + " перемещена");
            selectedVertex.move(x, y);
            selectedVertex = null;
            return;
        }
        // Добавление новой вершины
        if(vertexes.size() < 25) {
            addVertex(x, y, vertexes.size());
        }
    }

    public void rightMouseClick(int x, int y) {
        // Блокируем действие
        if(block) {
            return;
        }
        // Удаление ребра
        if(checkVertexClick(x, y, true)) {
            return;
        }
        // Отмена выбора вершины
        selectedVertex = null;
    }

    public void setEdgeColor(int number1, int number2, Color color) {
        for(Edge edge : edges) {
            if(edge.getStartVertex().getNumber() == number1 && edge.getEndVertex().getNumber() == number2) {
                edge.setColor(color);
                break;
            }
        }
    }

    public void setVertexColor(int number, Color color) {
        vertexes.get(number).setColor(color);
    }

    public void resetColors() {
        for(Edge edge : edges) {
            edge.setColor(Color.DARK_GRAY);
        }
        for(Vertex vertex : vertexes) {
            vertex.setColor(Color.DARK_GRAY);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(2.0F));
        // Отрисовываем обозначение выбранной вершины
        if(selectedVertex != null) {
            g2d.drawOval(
                    (int) ((selectedVertex.getX() - vertexRadius - 4) * multiplier),
                    (int) ((selectedVertex.getY() - vertexRadius - 4) * multiplier),
                    (int) ((2 * vertexRadius + 8) * multiplier),
                    (int) ((2 * vertexRadius + 8) * multiplier)
            );
        }
        // Отрисовываем ориентированные рёбра (сначала обычные)
        g2d.setStroke(new BasicStroke((float) (2.5F * multiplier)));
        for(Edge edge : edges) {
            if(edge.getColor() == Color.DARK_GRAY) {
                edge.draw(g2d, multiplier, (int) (vertexRadius * 2 * multiplier * 1.5), (int) (vertexRadius / 2 * multiplier * 1.5));
            }
        }
        // После отрисовываем разноцветные
        for(Edge edge : edges) {
            if(edge.getColor() != Color.DARK_GRAY) {
                edge.draw(g2d, multiplier, (int) (vertexRadius * 2 * multiplier * 1.5), (int) (vertexRadius / 2 * multiplier * 1.5));
            }
        }
        // Отрисовываем вес рёбер
        g2d.setFont(new Font("Inter", Font.BOLD, (int) (20 * multiplier)));
        for(Edge edge : edges) {
            edge.drawWeight(g2d, multiplier);
        }
        // Отрисовываем вершины
        for(Vertex vertex : vertexes) {
            vertex.draw(g2d, multiplier, vertexRadius);
        }
    }
}
