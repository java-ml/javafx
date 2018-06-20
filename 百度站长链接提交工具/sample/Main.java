package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));

        Pane root =loader.load();
        Scene scene=new Scene(root, 500, 475,new Color(0.5,0.5,0.5,0.6));
        root.setStyle("-fx-background:transparent;");
        primaryStage.setTitle("BaiDu");
        Controller controller=loader.getController();
        controller.stage=primaryStage;
        root.prefWidth(scene.getWidth());
        root.prefHeight(scene.getHeight());
        root.prefHeightProperty().bind(scene.heightProperty());
        root.prefWidthProperty().bind(scene.widthProperty());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
