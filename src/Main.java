import Scenes.LoginPage;
import Scenes.ProfileCreation;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application
{
	LoginPage loginPage;
	ProfileCreation profileCreation;
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage mainWindow) throws Exception
	{
		
		
		mainWindow.setTitle("Facebook Lite");
		
		initializeScenes(mainWindow);
		connectScenes(mainWindow);
		
		mainWindow.setScene(loginPage.getScene());
		mainWindow.show();
	}

	public void initializeScenes(Stage mainWindow)
	{
		loginPage = new LoginPage(mainWindow);
		profileCreation = new ProfileCreation(mainWindow);
		
	}
	
	public void connectScenes(Stage mainWindow)
	{
		loginPage.loginButton.setOnAction(e -> mainWindow.setScene(profileCreation.getScene())); //temporary guaranteed move to profile Creation. CHANGE THIS LATER
		profileCreation.confirmButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
	}
	
}
