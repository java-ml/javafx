package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Controller {
    boolean bool = true;
    Stage stage;
    @FXML
    Button on;
    @FXML
    TextField fie;
    @FXML
    ProgressBar prog;
    @FXML
    LineChart chart;
    @FXML
    ProgressIndicator pi;
    Timeline anm;
    Service<String> service = new Service<String>() {
        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    for (int a = 1; a <= 100; a++) {
                        //更新service的value属性

                        updateValue("process:" + a + "%");
                        updateProgress(a, 100);
                        Thread.sleep(50);
                    }

                    return "success";
                }
            };
        }
    };
    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
        Random rand = new Random();

        @Override
        public void handle(ActionEvent event) {

            int i = 2000;
            chart.getData().clear();
            XYChart.Series<String, Number> series = new XYChart.Series();
            while (i < 2018) {
                series.getData().addAll(new XYChart.Data<String, Number>(String.valueOf(i),rand.nextInt(40)+38 ));
                i++;
            }

            chart.getData().addAll(series);
        }
    };
       EventHandler<ActionEvent> event3=new EventHandler<ActionEvent>() {
            Random rand=new Random();

            @Override
            public void handle(ActionEvent event) {

                chart.getData().clear();
                XYChart.Series<String,Number>  series=new XYChart.Series();


                chart.getData().addAll(series);
            }
        };

    @FXML
    protected void ButtonAction(ActionEvent event) {

        pi.indeterminateProperty();
        prog.progressProperty().addListener((ObservableValue<? extends Number> ov, Number old_val,
                                             Number new_val) -> {

            pi.setProgress(new_val.doubleValue());
        });

        if (bool) {
            bool=false;
            anm = new Timeline();
            anm.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, event1) , new KeyFrame(Duration.seconds(1.5), event3));
            anm.setCycleCount(Animation.INDEFINITE);

            anm.play();
            prog.progressProperty().addListener((ObservableValue<? extends Number> ov, Number old_val,
                                                 Number new_val) -> {

                if (new_val.equals(0.99)){ anm.stop();bool=true;}

            });
            service.restart();
            fie.textProperty().bind(service.valueProperty());
            prog.progressProperty().bind(service.progressProperty());
        }

    }
}