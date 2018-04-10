package sample;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.io.File;


public class Controller {
    @FXML
    Button OkB;
    @FXML
    TextField x,y;
    @FXML
    ScatterChart <Double,Double>sc;
    @FXML
    MenuItem ope;
    Stage st;
    Parent root;
    public  void  Check(ActionEvent e){

        XYChart.Series<Double,Double> series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data(Double.valueOf(x.getText()), Double.valueOf(y.getText())));
        System.out.println(e.getEventType()+"on");
        OkB.setText("On");
       sc.getData().addAll(series1);
    }
    public  void NeWindows(ActionEvent e){
        try {

            Parent anotherRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Another Window Triggered by Clicking");
            anotherStage.setScene(new Scene(anotherRoot, 600, 329));
            anotherStage.show();
        } catch (Exception e1){
            e1.printStackTrace();
        }
    }
    public  void OpenFile(ActionEvent e){

        try {
            FileChooser chooser =new FileChooser();
            chooser.setTitle("date file");
            chooser.setInitialDirectory(new File("/"));
            File file=chooser.showOpenDialog(st);

            System.out.print(file.getPath());
            } catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void Rand(ActionEvent e) {
        Platform.runLater(new RandemDate(sc));
    }

    public void closs(ActionEvent e) {
       System.exit(0);
    }
}
