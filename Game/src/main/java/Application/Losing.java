package Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Losing implements Part_Of_Game{

    @Override
    public Scene turnOn(Stage stage) throws Exception {

        stage.close();
        return null;
    }
}
