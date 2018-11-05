package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static javafx.stage.StageStyle.UNIFIED;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.primary = primaryStage;
        primaryStage.setTitle("登陆");
        primaryStage.initStyle(UNIFIED);
        primaryStage.setResizable(false);
        Scene scene = new Scene(root);
        scene.setFill(null);
        scene.getStylesheets().add(this.getClass().getResource("button.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("inc.jpg")));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
