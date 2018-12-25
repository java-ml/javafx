package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Random;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        LineChart chart=controller.chart;
        Random rand = new Random();
            int i = 2000;
            chart.getData().clear();
            XYChart.Series<String, Number> series = new XYChart.Series();
            while (i < 2018) {
                series.getData().addAll(new XYChart.Data<String, Number>(String.valueOf(i),rand.nextInt(40)+38 ));
                i++;
            }

            chart.getData().addAll(series);

        primaryStage.setTitle("折线图");
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
        Group group=new Group();
       Scene scene= new Scene(group, 600, 375);
       group.getChildren().add(root);


       // view.setRotate(90);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
