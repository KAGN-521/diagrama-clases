package Views;

import Models.Model;
import Models.Node;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {

    Model model;

    public Panel(Model model) {
        this.model = model;
        this.setSize(700, 1000);
    }

    public void paintClass(Graphics g) {

        if (model.getClassList().isEmpty()) {
            return;
        }

        int gSize = model.getClassList().size();

        for (int i = 0; i < gSize; i++) {

            int width = model.getClassList().get(i).getWidth();
            int height = model.getClassList().get(i).getHeight();
            int x = model.getClassList().get(i).getX();
            int y = model.getClassList().get(i).getY();

            g.setColor(Color.RED);
            g.drawString(model.getClassList().get(i).getName(), x + (width / 4), y + 20);

            g.setColor(model.getClassList().get(i).getColor());
            g.drawRect(x, y, width, height);

            int n = model.getClassList().get(i).getAttributes().size();

            if (n > 0) {

                g.drawLine(x, y + 30, x + width, y + 30);
                g.setColor(Color.BLACK);

                int val = y + 50;
                for (int j = 0; j < n; j++) {
                    g.drawString(model.getClassList().get(i).getAttributes().get(j), x + (width / 4), val);
                    val += 28;
                }
            }
        }
    }

    public void paintLines(Graphics g) {
        int gSize = model.getGraph().getGraph().size();
        g.setColor(Color.BLACK);

        if (gSize == 0) {
            return;
        }

        for (int i = 0; i < gSize; i++) {
            Node actual = model.getGraph().getGraph().get(i);
            while (actual.getNext() != null) {

                int x1 = actual.getNext().getPointA().x;
                int y1 = actual.getNext().getPointA().y;
                int x2 = actual.getNext().getPointB().x;
                int y2 = actual.getNext().getPointB().y;

                g.drawLine(x1, y1, x2, y2);
                actual = actual.getNext();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintClass(g);
        paintLines(g);
    }

}
