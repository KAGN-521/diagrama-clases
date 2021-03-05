package Views;

import Controllers.Controller;
import Models.Model;

import java.awt.Point; // Para retornar un objeto punto que devuelva una coordenada (x,y)

import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    Model model;
    Frame mainFrame;
    Controller controller;

    public View(Model model) {
        mainFrame = new Frame(model);

        model.addObserver(this);
        this.model = model;
    }

    public void start() {
        this.mainFrame.setVisible(true);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        this.mainFrame.panel.addMouseListener(controller);
        this.mainFrame.panel.addMouseMotionListener(controller);
    }

    public Point getMousePosition() {
        return this.mainFrame.getPanel().getMousePosition();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.mainFrame.getPanel().repaint();
    }

}
