package Models;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JOptionPane;

public class Model extends Observable {

    private ArrayList<Class> classList;
    private Graph graph;

    private Class son;

    private int contador;

    public Model() {
        classList = new ArrayList<Class>();
        graph = new Graph();

        this.son = null;
        this.contador = 1;
    }

    private void addClass(Class newClass) {
        classList.add(newClass);
    }

    private Class getClickedClass(Point coor) {

        int i = 0;
        Class response = null;

        while (response == null && i < classList.size()) {
            int x = classList.get(i).getX();
            int x2 = x + classList.get(i).getWidth();
            int y = classList.get(i).getY();
            int y2 = y + classList.get(i).getHeight();

            if (coor.x > x && coor.x < x2) {
                if (coor.y > y && coor.y < y2) {
                    response = classList.get(i);
                }
            }
            i++;
        }

        return response;
    }

    private void changeColor(Class var) {
        if (var.getColor() == Color.BLACK) {
            var.setColor(Color.green);
        } else if (var.getColor() == Color.green) {
            var.setColor(Color.BLACK);
        }
    }

    public void moveClass(Point coor, String dirY, String dirX) {

        Class obj = getClickedClass(coor);

        if (obj == null) {
            return;
        }

        Node node = graph.getNodeOnGraph(obj.getId());
        if (node == null) {
            return;
        }

        if ("U".equals(dirY)) {
            obj.setY(obj.getY() - 1);

            node.setPointA(node.getPointA().x, node.getPointA().y - 1);
            node.setPointB(node.getPointB().x, node.getPointB().y - 1);

        } else if ("D".equals(dirY)) {
            obj.setY(obj.getY() + 1);

            node.setPointA(node.getPointA().x, node.getPointA().y + 1);
            node.setPointB(node.getPointB().x, node.getPointB().y + 1);

        }
        if ("R".equals(dirX)) {
            obj.setX(obj.getX() + 1);

            node.setPointA(node.getPointA().x + 1, node.getPointA().y);
            node.setPointB(node.getPointB().x + 1, node.getPointB().y);

        } else if ("L".equals(dirX)) {
            obj.setX(obj.getX() - 1);

            node.setPointA(node.getPointA().x - 1, node.getPointA().y);
            node.setPointB(node.getPointB().x - 1, node.getPointB().y);

        }
        setChanged();
        notifyObservers();
    }

    public void createClass(Point coordinates) {

        Class obj = getClickedClass(coordinates);

        if (obj != null) {
            String attributteName = JOptionPane.showInputDialog("Nombre del Atributo: ");
            obj.addAttribute(attributteName);
            graph.updateSize(obj.getId(), 30);
        } else {
            String className = JOptionPane.showInputDialog("Nombre de Clase: ");
            if (className != null) {
                if (!"".equals(className)) {
                    Class newClass = new Class(className, contador, coordinates.x, coordinates.y);
                    addClass(newClass);

                    int x1 = newClass.getX() + 100;
                    int y1 = newClass.getY() + newClass.getHeight();
                    Point A = new Point(x1, y1);

                    int x2 = newClass.getX() + 100;
                    int y2 = newClass.getY();
                    Point B = new Point(x2, y2);

                    graph.addNode(contador, A, B);
                    contador++;
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede agregar una clase sin nombre");
                }
            }
        }

        setChanged();
        notifyObservers();
    }

    public void createHeritage(Point coor) {

        Class obj = getClickedClass(coor);

        if (obj == null) {

            if (son != null) {
                changeColor(son);
                son = null;
            }

            setChanged();
            notifyObservers();
            return;
        }

        if (son == null) {
            son = obj;
            changeColor(son);

            setChanged();
            notifyObservers();
            return;
        }

        changeColor(son);
        if (graph.addEdge(obj.getId(), son.getId())) {
            combineAttributes(obj, son);
        }
        son = null;

        setChanged();
        notifyObservers();
    }

    private void combineAttributes(Class a, Class b) {
        if (a.getAttributes().isEmpty()) {
            return;
        }
        for (int i = 0; i < a.getAttributes().size(); i++) {
            b.addAttribute(a.getAttributes().get(i));
            graph.updateSize(graph.getNodeOnGraph(b.getId()).getId(), 30);
        }
    }

    public ArrayList<Class> getClassList() {
        return classList;
    }

    public Graph getGraph() {
        return graph;
    }

}
