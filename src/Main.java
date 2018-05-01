import Scenes.LoginPage;
import Scenes.ProfileCreation;
import Scenes.ProfileScene;
import Scenes.SettingsScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application
{
	LoginPage loginPage;
	ProfileCreation profileCreation;
	ProfileScene profileScene;
	ProfileCreation settingsCreation;
	ProfileScene settingsScene;
	//SettingsScene settingsScene;
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage mainWindow) throws Exception
	{
		
		
		mainWindow.setTitle("Facebook Lite");
		mainWindow.getIcons().add(new Image("Images/fbl_main_icon.png"));
		
		initializeScenes(mainWindow);
		connectScenes(mainWindow);
		
		mainWindow.setScene(loginPage.getScene()); //Should initialize to the login page every time. May be set otherwise for testing purposes.
		mainWindow.show();
	}

	public void initializeScenes(Stage mainWindow)
	{
		loginPage = new LoginPage();
		profileCreation = new ProfileCreation(mainWindow);
		profileScene = new ProfileScene();
		settingsCreation = new ProfileCreation(mainWindow,true);
		
	}
	
	public void connectScenes(Stage mainWindow)
	{
		loginPage.loginButton.setOnAction(e -> mainWindow.setScene(profileCreation.getScene())); //temporary guaranteed move to profile Creation. CHANGE THIS LATER
		profileCreation.confirmButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
		profileCreation.confirmButton.setOnAction(e -> mainWindow.setScene(profileScene.getScene()));
		profileScene.settingsButton.setOnAction(e -> mainWindow.setScene(settingsCreation.getScene()));
		profileScene.logoutButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
		settingsCreation.confirmButton.setOnAction(e -> mainWindow.setScene(profileScene.getScene()));
		//still need to setup all button actions for profile scene

	}
	
}
