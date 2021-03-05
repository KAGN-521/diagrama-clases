package Views;

import Models.Model;

import javax.swing.JFrame;

public class Frame extends JFrame {

    Panel panel;

    public Frame(Model model) {
        panel = new Panel(model);
        this.add(panel);
        initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Diagrama de Clase");
        this.setSize(panel.getHeight(), panel.getWidth());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public Panel getPanel() {
        return panel;
    }

}
