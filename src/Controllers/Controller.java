package Controllers;

import Models.Model;
import Views.View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

// Controlador encargado de gestionar los eventos que ocurren en la clase View.
public class Controller implements MouseListener, MouseMotionListener {

    private Model model;
    private View view;

    // Variable para conocer la direccion del mouseDragger.
    private int lastY = 0;
    private int lastX = 0;
    private String dirY = "";
    private String dirX = "";

    // Constructor que recibe un Modelo y una Vista.
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.setController(this); // Le seteamos a la vista el controlador para que este gestione los eventos.
    }

    public void init() {
        this.view.start();
    }

    // Metodo que dice hacia que dirección se esta moviendo el mouse: U-D-L-R
    private void setDirection(int x, int y) {

        if (y < lastY) { // Movimiento hacia arriba.
            dirY = "U";
        } else if (y > lastY) { // Movimiento hacia abajo.
            dirY = "D";
        } else {
            dirY = "";
        }
        if (x < lastX) { // Movimiento hacia la izquierda.
            dirX = "L";
        } else if (x > lastX) { // Movimiento hacia la derecha.
            dirX = "R";
        } else {
            dirX = "";
        }

        lastY = y;
        lastX = x;
    }

    // Metodo que retorna si el punto esta en el frame.
    private boolean isOnFrame(int x, int y) {
        if (y < 700 && y > 0) {
            if (x < 1000 && x > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) { // En caso que le de doble click izquierdo.
            this.model.createClass(this.view.getMousePosition());
        }
        if (SwingUtilities.isRightMouseButton(e)) { // En caso que sea solo un click derecho.
            this.model.createHeritage(this.view.getMousePosition());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Seteamos la posición del primer click antes de arrastrar.
        lastY = this.view.getMousePosition().x;
        lastX = this.view.getMousePosition().y;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Movimiento del raton...
    @Override
    public void mouseDragged(MouseEvent e) {
        try {
            // Creamos variables para conocer la dereccion hacia donde se esta moviendo el mouse.
            int y = this.view.getMousePosition().y;
            int x = this.view.getMousePosition().x;

            if (!isOnFrame(x, y)) { // En caso que el punto este fuera del frame.
                return;
            }

            setDirection(x, y);
            this.model.moveClass(this.view.getMousePosition(), dirY, dirX);
        } catch (Exception i) {
            System.out.println("PFF"); // PFF = Punto fuera de Frame.
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
