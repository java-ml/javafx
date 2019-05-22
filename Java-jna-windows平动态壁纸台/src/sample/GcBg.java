package sample;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;

import javafx.animation.Timeline;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;


public class GcBg extends Canvas {

    private  GraphicsContext gc;
    private Timeline timeline=new Timeline();
    private  volatile RandomAccessFile randomFile = null;
    private  volatile  long len=-1;
    private  byte [] img=new byte[1095202*3];
    public GcBg(final double width,final double height){
        super(width, height);
        gc = getGraphicsContext2D();   
        try {
			randomFile=new RandomAccessFile(new File(GcBg.class.getResource("/").getPath()+"/images/data.acc"), "r");
			len=randomFile.length();
		} catch (Exception e) {
			e.printStackTrace();
		}
      //  this.img=Tools.getImageArray(GcBg.class.getResource("/").getPath()+"/images/");
    }
 public  Image getImage(){  
        try {
            if(randomFile.getFilePointer()==len)randomFile.seek(0);
			int seek=randomFile.readInt();
			seek=randomFile.read(img,0,seek);
			return new Image(new ByteArrayInputStream(img,0,seek));
		} catch (Exception e) {
			
			 return Tools.getDefImages()[0];
			}
		}
  public GcBg  CreatedTimeLine(){
      
      KeyFrame key1=new KeyFrame(Duration.seconds(0.10),(e)->{
          grpach(getImage());
      });
      KeyFrame key0=new KeyFrame(Duration.ZERO,event -> {
          grpach(getImage());
      });
         timeline.setCycleCount(Animation.INDEFINITE);
         timeline.getKeyFrames().addAll(key0,key1);
         return this;
  }

    public void startTime() {
        timeline.play ();
    }
    public void stop() {
        timeline.stop();
    }

    private  void  grpach(Image image){

           // gc.clearRect(0,0,this.getWidth(),this.getHeight());
            gc.drawImage(image,0,0,this.getWidth(),this.getHeight());
    }
    
    public void setPause() {
        timeline.pause();
    }
    public void startFromPause(){
        timeline.playFromStart();
    }
}
