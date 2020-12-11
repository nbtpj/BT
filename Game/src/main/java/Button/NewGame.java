package Button;

import Application.Choose_Bomberman;
import Loader.Data;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewGame extends Button {
    public static Image img = Data.get("New Game  col_Button");

    public NewGame(Stage stage, double x, double y, double w, double h) {
        super(img,x, y, w, h);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                stage.close();
                try {
                    (new Choose_Bomberman()).turnOn(stage);
                } catch (Exception exception) {

                }
            }
        });
    }

}
