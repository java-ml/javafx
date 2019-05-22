package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.awt.event.ActionListener;

public class Main extends Application {
    Dimension size=Tools.getSize();
	private Stage s2=Tools.getS2();
	private GcBg bg=Tools.getBg();
	 private TrayIcon trayIcon;
    @Override
    public void start(Stage primaryStage) throws Exception{
            primaryStage.setTitle("云桌面");
            primaryStage.initStyle(StageStyle.DECORATED); 
            Platform.setImplicitExit(false);
            primaryStage.setResizable(false);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/mimi.fxml"));
            Pane pane = fxmlLoader.load();
            Controller controller = fxmlLoader.getController();
            createTrayIcon(primaryStage);
            primaryStage.setOnCloseRequest(e->{
            	primaryStage.hide();
            });
            Scene scene=new Scene(pane);
            //initmove(primaryStage,controller.bar);
            primaryStage.setScene(scene);
            primaryStage.show();
            showMyBg(s2,String.valueOf(s2.hashCode()),bg);
          
    }

    public static void main(String[] args) {
        launch(args);
    }
  
    public void  showMyBg(final Stage primaryStage,final String title,final GcBg bg){
    	JnaUse jnaUse=new JnaUse();
        Group root=new Group();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initModality(Modality.NONE);
        primaryStage.setScene(new Scene(root, size.width, size.height));
        primaryStage.setTitle(title);
        primaryStage.setIconified(true);
        bg.CreatedTimeLine();
        root.getChildren().add(bg);  
        primaryStage.show();
        
        	if(!jnaUse.setPreantMyjna(title)) {
        		if(jnaUse.setPreant(title)!=1) {
        			Alert error = new Alert(Alert.AlertType.ERROR,"ERROR: NUll Windows");
        			error.showAndWait();    	
        		}
        	}

        bg.startTime();
         
    }
    /*private void initmove(Stage primaryStage,MenuBar bar ) {
    	bar.setOnMouseDragged(mouseEvent -> {
    		myPoint point=Tools.getMyPoint();
            if(point.y==0&&point.x==0) {
                point.x = mouseEvent.getX();
                point.y = mouseEvent.getY();
            }
            double x=-point.x+mouseEvent.getX(),y=-point.y+mouseEvent.getY();
             if(Math.abs(x)>30.0||Math.abs(y)>30.0) {
                 primaryStage.setX(primaryStage.getX()+x%25);
                 primaryStage.setY(primaryStage.getY() +y%20);
             }

         });
	}*/
    public void createTrayIcon(final Stage stage) {
        if (SystemTray.isSupported()) {
            // get the SystemTray instance
            SystemTray tray = SystemTray.getSystemTray();
            
            java.awt.Image image = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/sample/inc.png"));
            
            final ActionListener closeListener = e ->{JnaUse.stop();Tools.getBg().stop();System.exit(0);};
 
            ActionListener showListener = e -> Platform.runLater(stage::show);
            // create a popup menu
            PopupMenu popup = new PopupMenu();
 
            MenuItem showItem = new MenuItem("Show");
            showItem.addActionListener(showListener);
            popup.add(showItem);
 
            MenuItem closeItem = new MenuItem("Close");
            closeItem.addActionListener(closeListener);
            popup.add(closeItem);
            trayIcon = new TrayIcon(image, "dasket", popup);
            trayIcon.addActionListener(showListener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
           
        }
    }
}

