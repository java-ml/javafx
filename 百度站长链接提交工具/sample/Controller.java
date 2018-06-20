package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Controller {

    Timeline line=new Timeline();
    TestMain main;
    @FXML
    TextField url,token,time;

    @FXML
    TextArea update,resul;
    boolean isstart=false;
    Stage stage;
    int  t=0;

    public void setCloss(Event event) {
        System.exit(0);
    }
public  void  Stop(Event event){
        if(isstart){
            line.getKeyFrames().clear();
             line.stop();
              isstart=!isstart;
        }
}
    EventHandler<ActionEvent> startHand=(ActionEvent event)-> {

        startinit();
    };
    EventHandler<ActionEvent> start0=(ActionEvent event)-> {

        resul.appendText(main.start().toString()+"\n");

    };
    public void Start(Event event) {
        if(!isstart ){
            t=Integer.valueOf(time.getText().trim());
            t=t<=10?t:10;
            t=t>=1?t:1;
            line.setCycleCount(Animation.INDEFINITE);
             resul.setWrapText(true);
             line.getKeyFrames().addAll(new KeyFrame(Duration.ZERO,start0),new KeyFrame(Duration.seconds(t),  startHand));

            line.play();

            isstart=!isstart;
        }
    }

public void startinit(){

    StringBuffer buffer =new StringBuffer("http://data.zz.baidu.com/urls?site=");


    buffer.append(url.getText().trim());
    buffer.append("&token=");
    buffer.append(token.getText().trim());
    String updates=update.getText();

    time.setText(String.valueOf(t));

    main=new TestMain();
    main.setUrl(buffer.toString());
    main.setStr(updates);


    System.out.println(buffer.toString()+"   "+updates+"   "+t);
}
    public void setMini(Event event) {
        stage.setIconified(true);
    }
}
