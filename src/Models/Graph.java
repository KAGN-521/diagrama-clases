package Models;

import java.awt.Point;
import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> graph;

    public Graph() {
        graph = new ArrayList<Node>();
    }

    // Agrega un nuevo nodo al grafo.
    public void addNode(int i, Point A, Point B) {
        graph.add(new Node(i, A, B));
    }

    // Actualiza el punto A de un nodo especifico.
    public void updateSize(int nodeId, int y) {
        if (graph.isEmpty()) {
            return;
        }
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).getId() == nodeId) {
                graph.get(i).setPointA(graph.get(i).getPointA().x, graph.get(i).getPointA().y + y);
            }
        }
    }

    // Agrega una nueva relación entre nodos.
    public boolean addEdge(int f, int s) {

        Node father = getNodeOnGraph(f);

        if (father == null) {
            return false;
        }

        Node son = getNodeOnGraph(s);

        // En caso que la relación ya existe.
        if (existEdge(f, s)) {
            System.out.println("Ya la relación existe!");
            return false;
        }

        Node actual = father;
        while (actual.getNext() != null) {
            actual = actual.getNext();
        }

        Node newNode = new Node(s, father.getPointA(), son.getPointB());
        actual.setNext(newNode);

        return true;
    }

    public boolean existEdge(int father, int son) {
        Node actual = getNodeOnGraph(father);

        boolean response = false;

        while (response == false && actual != null) {
            if (actual.getId() == son) {
                response = true;
            }
            actual = actual.getNext();
        }

        return response;
    }

    public Node getNodeOnGraph(int nodeId) {
        if (graph.isEmpty()) {
            return null;
        }

        Node response = null;
        int i = 0;

        while (response == null && i < graph.size()) {
            if (graph.get(i).getId() == nodeId) {
                response = graph.get(i);
            }
            i++;
        }

        return response;
    }

    public void showGraph() {
        System.out.println("");
        for (int i = 0; i < graph.size(); i++) {
            Node actual = graph.get(i);
            int j = actual.getId();
            System.out.println("Node: " + j + " A: " + actual.getPointA() + " B: " + actual.getPointB());
            while (actual != null) {
                if (actual.getNext() != null) {
                    System.out.println("Hijo:" + actual.getNext().getId() + "  A: " + actual.getNext().getPointA() + " B: " + actual.getNext().getPointB());
                }
                actual = actual.getNext();
            }
        }
    }

    public ArrayList<Node> getGraph() {
        return graph;
    }

}
