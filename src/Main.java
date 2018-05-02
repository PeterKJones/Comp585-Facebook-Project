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
import javafx.scene.control.RadioButton;
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
		settingsCreation = new ProfileCreation(mainWindow,true);
		
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
			profileCreation.confirmButton.setOnAction(e -> {
				try {
					RadioButton gender = (RadioButton)profileCreation.genderGroup.getSelectedToggle(); //Needed to cast as radiobutton, then getText() later.
					createUser(
							profileCreation.usernameField.getText(),
							profileCreation.passwordField.getText(),
							profileCreation.fNameField.getText(),
							profileCreation.lNameField.getText(),
							profileCreation.aboutMeField.getText(),
							profileCreation.locationField.getText(),
							gender.getText(),
							Integer.parseInt(profileCreation.ageBox.getValue()),
							profileCreation.levOfEduBox.getValue()
					);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			profileScene.settingsButton.setOnAction(e -> mainWindow.setScene(settingsScene.getScene()));
			profileScene.logoutButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
			profileScene.logoutButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
			settingsScene.confirmButton.setOnAction(e -> mainWindow.setScene(profileScene.getScene()));
			loginPage.newUserButton.setOnAction(e -> mainWindow.setScene(profileCreation.getScene()));
		}
		catch(Exception e){
			System.out.println(e);
		}
		settingsCreation.confirmButton.setOnAction(e -> mainWindow.setScene(profileScene.getScene()));
		profileScene.logoutButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
		profileScene.settingsButton.setOnAction(e -> mainWindow.setScene(settingsCreation.getScene()));
		profileCreation.confirmButton.setOnAction(e -> mainWindow.setScene(profileScene.getScene()));
		profileCreation.confirmButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
		loginPage.loginButton.setOnAction(e -> mainWindow.setScene(profileCreation.getScene())); //temporary guaranteed move to profile Creation. CHANGE THIS LATER
		//still need to setup all button actions for profile scene

	}

	public void createUser (String username, String password, String firstName, String lastName, String about, String location, String gender, int age, String education) throws Exception{
		Connection connect = getConnection();
		String status = "Just joined Facebook Lite!";
		String image = "default.jpg";

		PreparedStatement statement = connect.prepareStatement(
				"INSERT INTO users (" +
						"id, " +
						"first_name," +
						"password," +
						"username," +
						"age," +
						"gender," +
						"bio," +
						"image," +
						"age_visibility," +
						"friend_visibility," +
						"post_visibility," +
						"status," +
						"last_name," +
						"education," +
						"location " +
						")" +
				" VALUES (NULL, '"+firstName+"', '"+password+"', '"+username+"', '"+age+"', '"+gender+"', '"+about+"', '"+image+"', 1, 1, 1, '"+status+"', '"+lastName+"', '"+education+"', '"+location+"' )");
		statement.executeUpdate();
	}

	public void login(String user, String pass) throws Exception{
		System.out.println("username: " + user);
		System.out.println("password: " + pass);
		Connection connect = getConnection();
		PreparedStatement statement = connect.prepareStatement("SELECT * FROM users WHERE username=" + "'" + user + "'" + " AND password=" + "'" + pass + "'");
		ResultSet result = statement.executeQuery();
		if (!result.next() ) {
			System.out.println("Incorrect Credentials");
			return;
		}
		int id = result.getInt("id");
		System.out.println("ID: " + result.getString("id"));
		System.out.println("Name: " + result.getString("first_name"));
		System.out.println("Username: " + result.getString("username"));
		System.out.println("Password: " + result.getString("password"));
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
