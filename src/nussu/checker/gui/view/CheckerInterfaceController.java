package nussu.checker.gui.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import nussu.checker.model.CheckerStorage;

public class CheckerInterfaceController {
	@FXML
    private TextArea interfaceDisplayArea;
	
	@FXML
	private TextField interfaceInputBox;
	
	private CheckerStorage _storage = null;
	private static final String FORMAT_NOT_DUPLICATE = "\"%s\" is added";
	private static final String FORMAT_DUPLICATE = "\"%s\" is not added because it is a duplicate";
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	_storage = CheckerStorage.getInstance();
    	
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
    	            checkDuplicate(text);

    	            // clear text
    	            interfaceInputBox.setText("");
    	        }
    	    }
    	});
    }
    
    private void checkDuplicate(String text) {
    	boolean isAdded = _storage.addEntry(text);
    	String response = null;
        if (isAdded) {
        	response = String.format(FORMAT_NOT_DUPLICATE, text);	
        } else {
        	response = String.format(FORMAT_DUPLICATE, text);
        }
        interfaceDisplayArea.setText(response);
    }
    

}
