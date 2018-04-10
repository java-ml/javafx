package sample;

import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import java.util.Random;

public class RandemDate extends Thread {

    ScatterChart<Double,Double> sc;
    public  RandemDate(ScatterChart<Double,Double> sc){
        this.sc=sc;
    }
    @Override
    public void run() {
        Random date=new Random(30);
        for (int i=0;i<25;i++) {
            XYChart.Series<Double, Double> series1 = new XYChart.Series();
            series1.getData().add(new XYChart.Data(date.nextDouble(), date.nextDouble()));

            sc.getData().addAll(series1);
        }

    }
}
