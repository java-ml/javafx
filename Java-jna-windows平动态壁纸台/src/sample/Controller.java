package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Controller {
	
    
    JnaUse jnaUse=new JnaUse();
	@FXML
	Label jtbz,spzz,dtbz;

	@FXML
	MenuBar bar;
	@FXML
    protected void Exit(ActionEvent event) {
		JnaUse.stop();
		Tools.getBg().stop();
		System.exit(0);
	}
	@FXML
    protected void Pause(ActionEvent event) {
		MenuItem item=(MenuItem) event.getSource();
		if("暂停".equals(item.getText())){
			item.setText("开始");
			Tools.getBg().setPause();
		}else {
			item.setText("暂停");
			Tools.getBg().startFromPause();
		}
	}
	@FXML
    protected void SetStatctBg(ActionEvent event) {
		
	}
	@FXML
    protected void SetAutoBg(ActionEvent event) {
		
	}
	@FXML
    protected void Make(ActionEvent event) {
		
	}
	
}
