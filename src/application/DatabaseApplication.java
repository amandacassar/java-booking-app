package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DatabaseApplication extends Application
{
    public Stage primaryStage;

    // declaring the home view
    public HomeView homeView;

    @Override
    public void start(Stage primaryStage)
    {
	// instantiating the home view
	homeView = new HomeView(primaryStage);

	// create the scene and place it in the stage
	Scene homeScene = new Scene(homeView.asParent(), 1200, 800);
        primaryStage.setScene(homeScene);
	primaryStage.setTitle("Home Page");
	primaryStage.show();
    }

    public static void main(String[] args)
    {
	launch();
    }
}


    
