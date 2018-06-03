package smap;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {



    @Override
    public void start(final Stage stage) throws Exception {
        stage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        Group group=new Group();

        Scene scene =new Scene(group,700,700,Color.rgb(0XDD, 0, 0, 0.25));

         MySence sence = new MySence( 900, 400 );
            controller.stage=stage;
            group.setStyle("-fx-background:transparent;");
            root.setLayoutX(20);
            root.setLayoutY(5);
             root.addEventHandler(MouseEvent.MOUSE_PRESSED,event -> {
           if (!sence.nodes.isLive) {
               sence.nodes.rester();
               sence.startTime();
           }
         });



        sence.widthProperty().bind(scene.widthProperty());
        sence.heightProperty().bind(scene.heightProperty());


         group.getChildren().add(sence);
        group.getChildren().add(root);
         stage.setScene(scene);

        sence.CreatedTimeLine();

        sence.initEnvent();

        stage.setTitle("JavaFX 2 Animations");
        stage.show();


    }

    public static void main(final String[] arguments) {

        Application.launch(arguments);


    }

}

