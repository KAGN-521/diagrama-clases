package Models;

import java.awt.Point;

// Clase nodo que solamente la necesita la clase graph, por eso esta en el mismo .java.
public class Node {

    // Variables principales.
    private int id;
    private Node next;

    // Variables de posición.
    Point pointA; // Padre
    Point pointB; // Hijo

    public Node(int id, Point A, Point B) {
        this.id = id;
        this.next = null;

        pointA = A;
        pointB = B;
    }

    // Se tiene que poder configurar los puntos.
    public void configPoints(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointA(int x, int y) {
        this.pointA.x = x;
        this.pointA.y = y;
    }

    public void setPointB(int x, int y) {
        this.pointB.x = x;
        this.pointB.y = y;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }
}
