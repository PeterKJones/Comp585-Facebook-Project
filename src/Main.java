import Classes.Account;
import Classes.Post;
import Classes.Profile;
import Scenes.LoginPage;
import Scenes.ProfileCreation;
import Scenes.ProfileScene;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application
{
    public Account account; //MAY BE USELESS soon.
    public Profile profile;

    LoginPage loginPage;
	ProfileCreation profileCreation;
	ProfileScene profileScene;
	ProfileCreation settingsCreation;
	ProfileScene settingsScene;
	//SettingsScene settingsScene;
	String css = this.getClass().getResource("/Scenes/fblStyles.css").toExternalForm();
	
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
		initializeEvents(mainWindow);
		
		mainWindow.setScene(loginPage.getScene()); //Should initialize to the login page every time. May be set otherwise for testing purposes.
		mainWindow.getScene().getStylesheets().add(css);
		mainWindow.setResizable(false);
		mainWindow.show();
	}

	public void initializeScenes(Stage mainWindow) throws Exception
	{
		loginPage = new LoginPage();
		profileCreation = new ProfileCreation(mainWindow);
		profileScene = new ProfileScene();
		settingsCreation = new ProfileCreation(mainWindow,true);
		
	}

	public void initializeEvents(Stage mainWindow) throws Exception
	{
			loginPage.loginButton.setOnAction(e ->
			{
				String userField = loginPage.userField.getText();
				String passField = loginPage.passField.getText();
				if (userField.isEmpty() == true || userField == null)
				{
					loginPage.errorNotif.setText("Please enter a username.");
				}
				else if (passField.isEmpty() == true || passField == null)
				{
					loginPage.errorNotif.setText("Please enter a password.");
				}
				else
				{
					try
					{
						login(userField, passField, mainWindow);
					} catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
			});
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
			profileScene.settingsButton.setOnAction(e -> {
				mainWindow.setScene(settingsCreation.getScene());
				mainWindow.getScene().getStylesheets().add(css);
			});
			profileScene.logoutButton.setOnAction(e -> mainWindow.setScene(loginPage.getScene()));
			profileScene.postButton.setOnAction(e -> postStatus());
			loginPage.newUserButton.setOnAction(e -> {
				mainWindow.setScene(profileCreation.getScene());
				mainWindow.getScene().getStylesheets().add(css);
			});

	}

	public void createUser (String username, String password, String firstName, String lastName, String about, String location, String gender, int age, String education) throws Exception{
		Connection connect = getConnection();

		//=========If all fields are filled (not null), account creation goes here (could be where this method is called too. up to you.==========
		//generate the profile object here with the fully filled form
		//Profile profile = new Profile(firstname, lastname, blah blah blah)
		    profile = new Profile(
		        firstName,
                lastName,
                age,
                gender,
                location,
                education,
                "Im new to Facebook Lite",
                "Images/fbl_default.png",
                getCurrentUserFriends(),
                getAllPosts(),
                1,
                1,
                1
            );

		    //CANNOT MAKE ACCOUNT HERE, WOULD REQUIRE ANOTHER QUERY AFTER INSERT. JUST REDIRECT NEW USER TO LOGIN.


		//We're leaving account declared above because it will likely be referenced later on in other events in Main. so don't declare a local variable here
		//Below, I'm calling a constructor that I just made for the account instantiating.
		//Now that both the account and profile objects are made, push the data for the profile to the database, which I beleive you already did below. Don't worry about doing that with the account.
		//it's not necessary since the account will be generated locally each time when we pull from the database in case #1(case 1: login, case 2: create new user(which is this))

		String status = "Just joined Facebook Lite!";
		String image = "Images/fbl_default.png";

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

	public void login(String user, String pass, Stage s) throws Exception{
		System.out.println("username: " + user);
		System.out.println("password: " + pass);
		Connection connect = getConnection();
		PreparedStatement statement = connect.prepareStatement("SELECT * FROM users WHERE username=" + "'" + user + "'" + " AND password=" + "'" + pass + "'");
		ResultSet result = statement.executeQuery();
		if (!result.next() ) {
			System.out.println("Incorrect Credentials");
			return;
		}
		//================================At this point, i'm assuming the login credentials are correct. If that's true,
		// We need to set the account  and the profile to what the user logged in with.
		// Pull from the database here, the user dataset related to the username and password, then create a temporary profile with that. (using Profile tempProfile = new Profile(<insert data pulled from DB into this constructor>)
		// We will call this tempProfile, a temp ArrayList<Profile>, and a temp ArrayList<Post>. the array lists will be pushed into tempProfile and then tempProfile will be passed to the tempaccount below.
		// Account tempAccount = new Account(user, pass, tempProfile, tempProfile.id);
		// I believe that's all. Make sure that all the parameters in the Profile.java class are entries in the users dataset on the DB.
		int id = result.getInt("id");
		System.out.println("ID: " + result.getString("id"));
		System.out.println("Name: " + result.getString("first_name"));
		System.out.println("Username: " + result.getString("username"));
        account = new Account(Integer.parseInt(result.getString("id")));
		System.out.println("Password: " + result.getString("password"));
		//Should return id or something to save who the current user is

        profile = new Profile(
                result.getString("first_name"),
                result.getString("last_name"),
                Integer.parseInt(result.getString("age")),
                result.getString("gender"),
                result.getString("location"),
                result.getString("education"),
                result.getString("bio"),
                result.getString("image"),
                getCurrentUserFriends(),
                getAllPosts(),
                Integer.parseInt(result.getString("age_visibility")),
                Integer.parseInt(result.getString("friend_visibility")),
                Integer.parseInt(result.getString("post_visibility"))
        );

        account = new Account(
                result.getString("username"),
                result.getString("password"),
                profile, Integer.parseInt(result.getString("id"))
        );

        profileScene.loadToScene(id, profile);

		s.setScene(profileScene.getScene());
		s.getScene().getStylesheets().add(css);

	}

	public ArrayList<Post> getAllPosts() throws Exception{
	    System.out.println("Getting Posts...");
        Connection connect = getConnection();

        //get all friends of the user
		PreparedStatement statementGetFriends = connect.prepareStatement("SELECT friend_id FROM friends WHERE user_id='" + account.getId() + "';");
		ResultSet friendsList = statementGetFriends.executeQuery();

		//with all the id's of friends, prepare a statement that will be used to get all posts where the user is of the user, and of friends of the user
		String statementString = "SELECT * FROM posts WHERE user_id='" + account.getId() + "' ";
		while (friendsList.next()){
			statementString = statementString + "OR user_id='" + Integer.parseInt(friendsList.getString("friend_id")) + "' ";
		}
		statementString = statementString + "ORDER BY timestamp DESC;";

		//now have statement ready to fire
        PreparedStatement statement = connect.prepareStatement(statementString);
        ResultSet result = statement.executeQuery();
        ArrayList<Post> posts = new ArrayList<>();

        while(result.next()){
            Post currentPost = new Post(
                    result.getString("message"),
                    Integer.parseInt(result.getString("user_id")),
					result.getTimestamp("timestamp"),
					Integer.parseInt(result.getString("id"))
            );
            posts.add(currentPost);
        }
        return posts;
    }

    public ArrayList<Profile> getCurrentUserFriends() throws Exception{
	    System.out.println("Getting Current User's Friends...");
        Connection connect = getConnection();
        //Query for all friends of current user
        PreparedStatement statement = connect.prepareStatement("SELECT * FROM friends WHERE user_id=" + "'" + account.getId() + "'");
        ResultSet result = statement.executeQuery();
        ArrayList<Profile> profiles = new ArrayList<>();
        while(result.next()){
            //Another query for each friend using friend_id
            PreparedStatement statement2 = connect.prepareStatement("SELECT * FROM users WHERE id=" + "'" + result.getString("friend_id") + "'");
            ResultSet result2 = statement2.executeQuery();
            //Create a profile for each user that corresponds to a friend_id from initial query
			while(result2.next()) {
				Profile currentProfile = new Profile(
						result2.getString("first_name"),
						result2.getString("last_name"),
						Integer.parseInt(result2.getString("age"))
				);
				//Save that profile to the ArrayList<Profile>
				profiles.add(currentProfile);
			}
        }
        //return profiles;
		return new ArrayList<Profile>();
    }

    public void addFriend(int friend_id) throws Exception{
		Connection connect = getConnection();
		PreparedStatement statement = connect.prepareStatement("INSERT INTO friends (user_id, friend_id) VALUES ('"+account.getId()+"', '"+friend_id+"')");
		statement.executeUpdate();
	}

	public Connection getConnection() throws Exception{
		try{
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/facebooklite";
			String username = "root";
			String password = "root";
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

	private void postStatus(){
		try {
			System.out.println("Posting status...");
			//check to see if the string isn't null or just spaces
			if(profileScene.postDescription.getText().trim().length() > 0) {
				Connection connect = getConnection();
				//"INSERT INTO posts (user_id, message) VALUES (1,'post');
				PreparedStatement statement = connect.prepareStatement("INSERT INTO posts (user_id, message) VALUES (" + account.getId() + ",'" + profileScene.postDescription.getText() + "');");
				statement.executeUpdate();
				profileScene.collapseMakePostComponent();
			}
			else {
				System.out.println("Post status can't be empty!");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
