package Principal;

import Controllers.Controller;
import Models.Model;
import Views.View;

public class App {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);

        controller.init();
    }

}
