package smap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    Stage stage=null;
    @FXML
    Button stop;

    @FXML
    protected void ButtonAction(ActionEvent event) {
       System.exit(0);
    }
}