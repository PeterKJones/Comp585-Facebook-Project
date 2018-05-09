package Scenes;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Random;
import java.util.logging.Logger;

import Classes.Account;
import Classes.Profile;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class ProfileCreation
{
	StackPane layout;
	Scene scene;
	GridPane gridPane = new GridPane();
	private Desktop desktop;
	private int rwSettings;
	public Button confirmButton;
	public Button browseButton;
	final FileChooser fileChooser = new FileChooser();
	public ToggleGroup genderGroup;
	public TextField fNameField;
	public TextField lNameField;
	public TextField usernameField;
	public PasswordField passwordField;
	public ComboBox<String> ageBox;
	public TextField locationField;
	public ComboBox<String> levOfEduBox;
	public TextField aboutMeField;
	public CheckBox ageVisField;
	public CheckBox friendVisField;
	public CheckBox postVisField;
	public String imageString;
	public String status;
	public RadioButton genderMale;
	public RadioButton genderFemale;
	public Label errorNotif;
    public TextField statusField;

	public ProfileCreation(Stage mainWindow)
	{

		
		updateGridPane(mainWindow);
		addConfirmButton();
		StackPane layout = new StackPane();
		
		layout.getChildren().add(gridPane);
		
		scene = new Scene(layout, 400, 400);

	}

	public ProfileCreation(Stage mainWindow, Boolean isSettings){
		if (isSettings){
			rwSettings=3;
			addSettingControls();
			updateGridPane(mainWindow);
		}else{
			rwSettings=0;
			updateGridPane(mainWindow);
		}


		addConfirmButton();
		StackPane layout = new StackPane();

		layout.getChildren().add(gridPane);

		scene = new Scene(layout, 400, 500);
	}

	public void updateGridPane(Stage mainWindow){
		//left column
		Text username = new Text("Username: ");
		Text password = new Text("Password: ");
		Text firstName = new Text("First Name: ");
		Text lastName = new Text("Last Name: ");
		Text age = new Text("Age: ");
		Text gender = new Text("Gender: ");
		Text location = new Text("Location: ");
		Text education = new Text("Level of Education: ");
		Text aboutMe = new Text("About Me: ");
		Text profileImage = new Text("Profile Picture: ");
		Text statusTxt = new Text("Status: ");
		Label errorNotif = new Label();

		//right column
		usernameField = new TextField();
		passwordField = new PasswordField();
		 fNameField = new TextField();
		 lNameField = new TextField();
		 statusField =  new TextField();


		 ageBox = new ComboBox<String>();
		for (int i = 1; i <= 100; i++) //populates the age box for ages 1 to 100
		{
			ageBox.getItems().add(String.valueOf(i));
		}
		genderGroup = new ToggleGroup();
		genderMale = new RadioButton("Male");
		genderMale.setToggleGroup(genderGroup);
		genderFemale = new RadioButton("Female");
		genderFemale.setToggleGroup(genderGroup);
		locationField = new TextField();
		levOfEduBox = new ComboBox<String>();
		levOfEduBox.getItems().addAll("None","Some High School","High School","College Undergraduate","College Graduate","PhD");
		aboutMeField = new TextField();
		browseButton = new Button("Browse");

		browseButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						FileChooser.ExtensionFilter imageFilter
								= new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.gif", "*.jpeg", "*.png");
						FileChooser fc = new FileChooser();

						fc.getExtensionFilters().add(imageFilter);
						File file = fc.showOpenDialog(mainWindow);

						if (file != null) {
							try {
								ImageView imageView = new ImageView();
								BufferedImage bufferedImage = ImageIO.read(file);
								Image image = SwingFXUtils.toFXImage(bufferedImage, null);
								imageView.setImage(image);
								imageView.setFitHeight(80);
								imageView.setFitWidth(80);

								Alert alert = new Alert(Alert.AlertType.INFORMATION);
								alert.setContentText(file.getAbsoluteFile().getAbsolutePath());
								alert.setGraphic(imageView);
								alert.setHeaderText("Your avatar has been updated.");
								alert.setTitle("Avatar");
								alert.showAndWait();


								//generate random name for file and saved
								String number = "";
								Random rnd = new Random();
								for (int i = 0; i<24; i++)
									number = number + rnd.nextInt(10);
								File directoryinfo = new File("");
								String formatName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1);
								String fileName = "UserImages/" + number + "." + formatName;
								File outputfile = new File(directoryinfo.getAbsolutePath() + "/src/" +fileName);
								ImageIO.write(bufferedImage, formatName, outputfile);

								// save a reference to the file to the db
								imageString = fileName;
							} catch (IOException ex) {
								Logger.getLogger("Image Error");
							}
						}
					}
				});



		//setting location

		gridPane.setMinSize(400, 200);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(firstName, 0, 0 + rwSettings);
		gridPane.add(lastName, 0, 1 + rwSettings);
		gridPane.add(age, 0, 2 + rwSettings);
		gridPane.add(gender, 0, 3 + rwSettings);
		gridPane.add(location, 0, 5 + rwSettings);
		gridPane.add(education, 0, 6 + rwSettings);
		gridPane.add(aboutMe, 0, 7 + rwSettings);
		gridPane.add(profileImage, 0, 8 + rwSettings);
		gridPane.add(username, 0 , 9 + rwSettings);
		gridPane.add(password, 0 , 10 + rwSettings);
		gridPane.add(statusTxt, 0, 11 + rwSettings);


		gridPane.add(fNameField, 1, 0 + rwSettings);
		gridPane.add(lNameField, 1, 1 + rwSettings);
		gridPane.add(ageBox, 1, 2 + rwSettings);
		gridPane.add(genderMale, 1, 3 + rwSettings);
		gridPane.add(genderFemale, 1, 4 + rwSettings);
		gridPane.add(locationField, 1, 5 + rwSettings);
		gridPane.add(levOfEduBox, 1, 6 + rwSettings);
		gridPane.add(aboutMeField, 1, 7 + rwSettings);
		gridPane.add(browseButton, 1, 8 + rwSettings);
		gridPane.add(usernameField, 1, 9 + rwSettings);
		gridPane.add(passwordField, 1 , 10 + rwSettings);
        gridPane.add(statusField, 1 , 11 + rwSettings);
	}

	private void addSettingControls(){
		Text ageVis = new Text("Age Visability: ");
		Text friendVis = new Text("Friend Visability: ");
		Text postVis = new Text("Post Visability: ");
		ageVisField = new CheckBox();
		friendVisField = new CheckBox();
		postVisField = new CheckBox();

		gridPane.add(ageVis, 0, 0);
		gridPane.add(ageVisField, 1, 0);
		gridPane.add(friendVis, 0, 1);
		gridPane.add(friendVisField, 1, 1);
		gridPane.add(postVis, 0, 2);
		gridPane.add(postVisField, 1, 2);
	}

	private void addConfirmButton(){
		confirmButton = new Button("Confirm");
		gridPane.add(confirmButton, 1, 12 + rwSettings);
	}
	public Scene getScene()
	{
		return scene;
	}

	private void openFile(File file) {
		desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException ex) { System.out.println("Could not open file."); }
    }

    public void loadContent(int accountID, Profile profile)
	{
		try {
			Connection connect = getConnection();
			ResultSet result = connect.prepareStatement("SELECT * FROM users WHERE id = '" + accountID + "';").executeQuery();
			if(result.next()) {
				if(result.getBoolean("age_visibility"))
					ageVisField.setSelected(true);
				else
					ageVisField.setSelected(false);

				if(result.getBoolean("friend_visibility"))
					friendVisField.setSelected(true);
				else
					friendVisField.setSelected(false);

				if(result.getBoolean("post_visibility"))
					postVisField.setSelected(true);
				else
					postVisField.setSelected(false);

				fNameField.setText(result.getString("first_name"));
				lNameField.setText(result.getString("last_name"));
				ageBox.setValue(result.getString("age"));
				if(result.getString("gender").toLowerCase().equals("m") ||
						result.getString("gender").toLowerCase().equals("male"))
					genderMale.setSelected(true);
				else
					genderFemale.setSelected(true);
				aboutMeField.setText(result.getString("bio"));
				levOfEduBox.setValue(result.getString("education"));
				imageString = result.getString("image");
				status = result.getString("status");
				locationField.setText(result.getString("location"));
				usernameField.setText(result.getString("username"));
				passwordField.setText(result.getString("password"));
				statusField.setText(result.getString("status"));
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
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
