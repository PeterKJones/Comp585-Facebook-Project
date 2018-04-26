import Scenes.LoginPage;
import Scenes.ProfileCreation;
import Scenes.ProfileScene;
import Scenes.SettingsScene;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.util.Objects.isNull;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application
{
	LoginPage loginPage;
	ProfileCreation profileCreation;
	ProfileScene profileScene;
	SettingsScene settingsScene;
	
	public static void main(String[] args)  throws Exception
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

	public void initializeScenes(Stage mainWindow) throws Exception
	{
		loginPage = new LoginPage();
		profileCreation = new ProfileCreation(mainWindow);
		profileScene = new ProfileScene();
		settingsScene = new SettingsScene(mainWindow);
		
	}
	
	public void connectScenes(Stage mainWindow) throws Exception
	{
		try{
			loginPage.loginButton.setOnAction(e -> {
				try {
					login(loginPage.userField.getText(), loginPage.passField.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}); //temporary guaranteed move to profile Creation. CHANGE THIS LATER
			profileCreation.confirmButton.setOnAction(e -> mainWindow.setScene(profileScene.getScene()));
			profileScene.settingsButton.setOnAction(e -> mainWindow.setScene(settingsScene.getScene()));
			profileScene.logoutButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
			settingsScene.confirmButton.setOnAction(e -> mainWindow.setScene(profileScene.getScene()));
		}
		catch(Exception e){
			System.out.println(e);
		}
		//still need to setup all button actions for profile scene
	}

	public void login(String user, String pass) throws Exception{
		System.out.println("username: " + user);
		System.out.println("password: " + pass);
		Connection connect = getConnection();
		PreparedStatement statement = connect.prepareStatement("SELECT * FROM users WHERE username=" + "'" + user + "'" + " AND password=" + "'" + pass + "'");
		ResultSet result = statement.executeQuery();
		if (!result.next() ) {
			System.out.println("Incorrect Credentials");
		}
		while(result.next()){
			System.out.println("Name: " + result.getString("first_name"));
			System.out.println("Username: " + result.getString("username"));
			System.out.println("Password: " + result.getString("password"));
		}

		//Should return id or something to save who the current user is
	}

	public Connection getConnection() throws Exception{
		try{
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/facebooklite";
			String username = "root";
			String password = "";
			Class.forName(driver);
			Connection connect = DriverManager.getConnection(url,username,password);
			System.out.println("Connection Established");
			return connect;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return null;
	}

}
