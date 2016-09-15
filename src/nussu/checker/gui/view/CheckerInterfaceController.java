package nussu.checker.gui.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CheckerInterfaceController {
	@FXML
    private TextArea interfaceDisplayArea;
	
	@FXML
	private TextField interfaceInputBox;
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Listen for TextField text changes
    	interfaceInputBox.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable,
    	            String oldValue, String newValue) {

    	    	System.err.print("TextField Text Changed (newValue: " + newValue + ")\n");
    	    }
    	});
    	
    	// wherever you assign event handlers...
    	interfaceInputBox.setOnKeyReleased(new EventHandler<KeyEvent>() {
    	    @Override
    	    public void handle(KeyEvent keyEvent) {
    	        if (keyEvent.getCode() == KeyCode.ENTER)  {
    	            String text = interfaceInputBox.getText();

    	            // do your thing...
    	            interfaceDisplayArea.setText(text);

    	            // clear text
    	            interfaceInputBox.setText("");
    	        }
    	    }
    	});
    }
    

}
