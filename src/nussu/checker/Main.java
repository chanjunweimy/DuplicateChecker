package nussu.checker;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Stage _primaryStage;
	private BorderPane _rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		initApplicationPrimaryStage(primaryStage);
		initRootLayout();
		showCheckerInterface();
	}
	
	private void initApplicationPrimaryStage(Stage primaryStage) {
		_primaryStage = primaryStage;
		_primaryStage.setTitle("Duplicate Checker");
	}
	
	/**
     * Initializes the root layout.
     */
    public void initRootLayout() {
    	_rootLayout = null;
    	try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            _rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(_rootLayout);
            scene.getStylesheets().add(getClass().getResource("style/application.css").toExternalForm());
            _primaryStage.setScene(scene);
            _primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    /**
     * Shows the checker interface inside the root layout.
     */
    public void showCheckerInterface() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/CheckerInterface.fxml"));
            AnchorPane checkerInterface = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            _rootLayout.setCenter(checkerInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return _primaryStage;
    }
    
	public static void main(String[] args) {
		launch(args);
	}
}
