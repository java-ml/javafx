package sample2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.RequestServer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import static javafx.stage.StageStyle.UNIFIED;

public class MyClass{


    public  void start(Stage primaryStage, Map<String, LinkedList<String>> clas) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("table.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller con = fxmlLoader.getController();
        con.table.setFixedCellSize(110);
        con.table.getStylesheets().add(this.getClass().getResource("table.css").toExternalForm());
        con.table.setId("table-view");

        con.num.setCellValueFactory( new PropertyValueFactory<>("num"));
        con.Yi.setCellValueFactory( new PropertyValueFactory<>("yi"));
        con.Er.setCellValueFactory( new PropertyValueFactory<>("er"));
        con.San.setCellValueFactory( new PropertyValueFactory<>("san"));
        con.Si.setCellValueFactory( new PropertyValueFactory<>("si"));
        con.Wu.setCellValueFactory( new PropertyValueFactory<>("wu"));
        con.Liu.setCellValueFactory( new PropertyValueFactory<>("liu"));
        con.Qi.setCellValueFactory( new PropertyValueFactory<>("qi"));
        ObservableList<ClassData> data =  FXCollections.observableArrayList( );
        for (Map.Entry<String,LinkedList<String>> entry:clas.entrySet()){
            data.add(new ClassData(entry.getKey(),entry.getValue()));
        }
        con.table.setItems(data);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(MyClass.class.getResourceAsStream("/sample/inc.jpg")));

        primaryStage.show();
    }



}
