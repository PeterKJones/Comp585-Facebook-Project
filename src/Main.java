import Scenes.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application
{
	FXMLLoginController loginPage;
	FXMLProfileController profileCreation;
	// ProfileCreation profileCreation;
	ProfileScene profileScene;
	ProfileCreation settingsCreation;
	ProfileScene settingsScene;
	private static Stage pStage;


	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage mainWindow) throws Exception
	{
		setPrimaryStage(mainWindow);
		mainWindow.setTitle("Facebook Lite");
		mainWindow.getIcons().add(new Image("Images/fbl_main_icon.png"));

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/LoginPage.fxml"));
		loader.setController(new FXMLLoginController());
		Parent root = FXMLLoader.load(getClass().getResource("Scenes/LoginPage.fxml"));
		//Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("Scenes/SceneControl.fxml"));
		//Pane myPane = FXMLLoader.load(getClass().getResource("Scenes/LoginPage.fxml"));

		//Scene loginScene = new Scene(root,400, 500);
		Scene loginScene = new Scene(root);
		//initializeScenes(mainWindow);
		//connectScenes(mainWindow);

		mainWindow.setScene(loginScene);
		mainWindow.show();
	}

	public void initializeScenes(Stage mainWindow) throws Exception
	{

		loginPage = new FXMLLoginController();
		profileCreation = new FXMLProfileController();
		//loginPage = new LoginPage();
		//profileCreation = new ProfileCreation(mainWindow);
		profileScene = new ProfileScene();
		settingsCreation = new ProfileCreation(mainWindow,true);
		
	}


	public void connectScenes(Stage mainWindow)
	{

		//profileCreation.confirmButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
		//loginPage.loginButton.setOnAction(e -> mainWindow.setScene(profileCreation.getScene())); //temporary guaranteed move to profile Creation. CHANGE THIS LATER
		//profileCreation.confirmButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
		//profileCreation.confirmButton.setOnAction(e -> mainWindow.setScene(profileScene.getScene()));
		//profileScene.settingsButton.setOnAction(e -> mainWindow.setScene(settingsCreation.getScene()));
		//profileScene.logoutButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
		//settingsCreation.confirmButton.setOnAction(e -> mainWindow.setScene(profileScene.getScene()));
		//still need to setup all button actions for profile scene

	}
	public static Stage getPrimaryStage() {
		return pStage;
	}

	private void setPrimaryStage(Stage pStage) {
		Main.pStage = pStage;

	}
	
}
