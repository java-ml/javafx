package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader =new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
                //FXMLLoader.load(getClass().getResource("sample.fxml"));
        Controller con=loader.getController();
       con.st=primaryStage;
       con.root=root;
        Group g=new Group(root);

        Circle circle = new Circle();

        circle.setCenterX(100);
        circle.setCenterY(400);
        circle.setRadius(50.0f);
        g.getChildren().add(circle);
        primaryStage.setTitle("数据可视化图表");
        primaryStage.setScene(new Scene(g, 600, 420));

        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();

    }

    public static void main(String[] args) {

        launch(args);

    }
}
