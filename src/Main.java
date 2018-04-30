import Scenes.FXMLLoginController;
import Scenes.LoginPage;
import Scenes.ProfileCreation;
import Scenes.ProfileScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application
{
	LoginPage loginPage;
	ProfileCreation profileCreation;
	ProfileScene profileScene;
	ProfileCreation settingsCreation;
	ProfileScene settingsScene;


	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage mainWindow) throws Exception
	{
		mainWindow.setTitle("Facebook Lite - Login Page");
		mainWindow.getIcons().add(new Image("Images/fbl_main_icon.png"));

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/LoginPage.fxml"));
		loader.setController(new FXMLLoginController());
		Parent root = FXMLLoader.load(getClass().getResource("Scenes/LoginPage.fxml"));
		//Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("Scenes/SceneControl.fxml"));
		//Pane myPane = FXMLLoader.load(getClass().getResource("Scenes/LoginPage.fxml"));

		Scene loginScene = new Scene(root,400, 700);

		//initializeScenes(mainWindow);
		//connectScenes(mainWindow);

		mainWindow.setScene(loginScene);
		mainWindow.show();
	}

	public void initializeScenes(Stage mainWindow) throws Exception
	{
		//loginPage = new LoginPage();
		profileCreation = new ProfileCreation(mainWindow);
		profileScene = new ProfileScene();
		settingsCreation = new ProfileCreation(mainWindow,true);
		
	}


	public void connectScenes(Stage mainWindow)
	{
		//loginPage.loginButton.setOnAction(e -> mainWindow.setScene(profileCreation.getScene())); //temporary guaranteed move to profile Creation. CHANGE THIS LATER
		profileCreation.confirmButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
		profileCreation.confirmButton.setOnAction(e -> mainWindow.setScene(profileScene.getScene()));
		profileScene.settingsButton.setOnAction(e -> mainWindow.setScene(settingsCreation.getScene()));
		profileScene.logoutButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
		settingsCreation.confirmButton.setOnAction(e -> mainWindow.setScene(profileScene.getScene()));
		//still need to setup all button actions for profile scene

	}
	
}
