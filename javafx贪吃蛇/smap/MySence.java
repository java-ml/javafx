package smap;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;

import javafx.animation.Timeline;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MySence extends Canvas {
    Random random =new Random();
    AtomicInteger x=new AtomicInteger(80);
    AtomicInteger y=new AtomicInteger(80);
    AtomicInteger i=new AtomicInteger(80);
    boolean up=false;
    private GraphicsContext gc;
    KeyFrame keyFrame;
    Timeline timeline;
    AllNode nodes=new AllNode();
    Eats eats=new Eats(nodes,getGraphicsContext2D());
    public MySence(double width, double height){
        super(width, height);

        gc = getGraphicsContext2D();

    }

  public void  CreatedTimeLine(){
        timeline=new Timeline();
        keyFrame=new KeyFrame(Duration.seconds(0.18), (event)->{
          grpach();
        });
      KeyFrame key = new KeyFrame(Duration.seconds(0.35), (event) -> {

      });
      KeyFrame key0=new KeyFrame(Duration.ZERO,(event )->{
       grpach();
      });
         timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().addAll(key0,keyFrame,key);

  }

    public void startTime() {
        new Thread(eats).start();
        timeline.play ();
    }
    public void stop() {
        timeline.stop();
    }

    public void initEnvent() {
        getParent().getScene().setOnKeyPressed(event -> {

           switch ( event.getCode().toString()){
               case "UP":{nodes.head.setRen(Return.UP);}break;
               case "DOWN":{nodes.head.setRen(Return.DOWN);}break;
               case "LEFT":{nodes.head.setRen(Return.LIFT);}break;
               case "RIGHT":{nodes.head.setRen(Return.RIGHT);}break;
           }
        });
    }
    private  void  grpach(){
        if (nodes.isLive){
            gc.clearRect(0,0,this.getWidth(),this.getHeight());
            gc.setFill(Color.color(0.5,00,0.5,0.9));
            nodes.grep(gc);
            nodes.moveAll();
            nodes.CheckMax(this.getWidth(),this.getHeight());
            gc.setFill(Color.color(0.5,0.6,0.1,1));
            gc.setFont(new Font(26));
            gc.fillText(nodes.getSize(),290,40,40);
            eats.draw();
        }
        else  {

            gc.setFont(new Font(56));
            gc.fillText("GAME OVER",290,240,150);
            timeline.stop();
        }

    }
}
