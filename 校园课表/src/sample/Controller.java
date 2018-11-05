package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample2.MyClass;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    Button login;
    @FXML
    PasswordField password;
    @FXML
    TextField number;
    @FXML
    CheckBox hold, auto;
    Stage primary;
    Scene scene;
    RequestServer type;

    public void Login(Event event) {

        Pane root = new Pane();
        scene = primary.getScene();
        root.setMinHeight(305);
        root.setMinWidth(429);
        ProgressBar progressIndicator = new ProgressBar();
        Button cancel = new Button("cancel");
        cancel.getStylesheets().add(this.getClass().getResource("button.css").toExternalForm());
        cancel.setLayoutX(152);
        cancel.setLayoutY(220);
        root.setBackground(Background.EMPTY);
        Label label = new Label("数据加载中...");
        label.setFont(new Font(15));
        label.setLayoutX(153);
        label.setLayoutY(110);
        progressIndicator.setProgress(-1F);
        progressIndicator.setLayoutX(87);
        progressIndicator.setLayoutY(155);
        progressIndicator.prefHeight(18);
        progressIndicator.setMinWidth(220);
        root.getChildren().addAll(progressIndicator, label, cancel);
        Scene scene2 = new Scene(root);
        scene2.setFill(null);
        scene2.getStylesheets().add(this.getClass().getResource("button.css").toExternalForm());
        progressIndicator.getStyleClass().add("progress-bar-main");
        label.getStyleClass().add("common-label");
        primary.setScene(scene2);
        LoginTask task = new LoginTask();
        HashMap<String,String> map = task.map;
        String num=number.getText(),pass= password.getText();
        task.LoginTask(num,pass);

        task.messageProperty().addListener((observableValue, s, t1) -> {
            if ("登录成功".equals(t1)) {
                type = task.getType();
                StringBuffer buffer= null;
                try {
                    buffer = type.Get("http://219.148.85.172:7788/xkAction.do","actionType=6",map);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Pattern pattern=Pattern.compile(".*(第.小节).*\\s*(<td>&nbsp;\\s*.+\\s*.*\\s*</td>\\s*)+");
                Pattern pattern2=Pattern.compile(" <td>&nbsp;\\s+(.+\\s*.*)\\s+</td>");
                Matcher matcher=pattern.matcher(buffer.toString());
                Map<String, LinkedList<String>> clas=new TreeMap<>();
                while (matcher.find()){
                   Matcher matcher2=pattern2.matcher(matcher.group(0));
                   LinkedList<String> list=new LinkedList<>();
                   clas.put(matcher.group(1),list);
                   while (matcher2.find()){
                      String str= matcher2.group(1);
                        System.out.print(str);
                      if (str.indexOf("&nbsp;")!=-1)list.add("无课");
                       else list.add(str.replaceAll("[_]","\n").replaceAll("<br>\n*",""));
                   }
                }
                primary.close();
                new MyClass().start(new Stage(),clas);

                if(hold.isSelected()){
                    System.out.println(num+"-----"+pass+auto.isSelected());
                }

             } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(t1);
                alert.showAndWait();
                primary.setScene(scene);
            }

        });
        Thread thread = new Thread(task);
        cancel.addEventFilter(MouseEvent.MOUSE_PRESSED, (mouseEvent -> {
            primary.setScene(scene);
            thread.interrupt();
        }));
        thread.start();
    }

}
