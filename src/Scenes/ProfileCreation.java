package Scenes;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
			updateGridPane(mainWindow);
			addSettingControls();
		}else{
			rwSettings=0;
			updateGridPane(mainWindow);
		}

		addConfirmButton();
		StackPane layout = new StackPane();

		layout.getChildren().add(gridPane);

		scene = new Scene(layout, 400, 400);
	}

	public void updateGridPane(Stage mainWindow){
		//left column
		Text firstName = new Text("First Name: ");
		Text lastName = new Text("Last Name: ");
		Text age = new Text("Age: ");
		Text gender = new Text("Gender: ");
		Text location = new Text("Location: ");
		Text education = new Text("Level of Education: ");
		Text aboutMe = new Text("About Me: ");
		Text profileImage = new Text("Profile Picture: ");

		//right column
		TextField fNameField = new TextField();
		TextField lNameField = new TextField();
		ComboBox<String> ageBox = new ComboBox<String>();
		for (int i = 1; i <= 100; i++) //populates the age box for ages 1 to 100
		{
			ageBox.getItems().add(String.valueOf(i));
		}
		ToggleGroup genderGroup = new ToggleGroup();
		RadioButton genderMale = new RadioButton("Male");
		genderMale.setToggleGroup(genderGroup);
		RadioButton genderFemale = new RadioButton("Female");
		genderFemale.setToggleGroup(genderGroup);
		TextField locationField = new TextField();
		ComboBox<String> levOfEduBox = new ComboBox<String>();
		levOfEduBox.getItems().addAll("None","Some High School","High School","College Undergraduate","College Graduate","PhD");
		TextField aboutMeField = new TextField();
		browseButton = new Button("Browse");

		browseButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						File file = fileChooser.showOpenDialog(mainWindow);
						if (file != null) {
							openFile(file);
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


		gridPane.add(fNameField, 1, 0 + rwSettings);
		gridPane.add(lNameField, 1, 1 + rwSettings);
		gridPane.add(ageBox, 1, 2 + rwSettings);
		gridPane.add(genderMale, 1, 3 + rwSettings);
		gridPane.add(genderFemale, 1, 4 + rwSettings);
		gridPane.add(locationField, 1, 5 + rwSettings);
		gridPane.add(levOfEduBox, 1, 6 + rwSettings);
		gridPane.add(aboutMeField, 1, 7 + rwSettings);
		gridPane.add(browseButton, 1, 8 + rwSettings);

	}

	private void addSettingControls(){
		Text ageVis = new Text("Age Visability: ");
		Text friendVis = new Text("Friend Visability: ");
		Text postVis = new Text("Post Visability: ");
		CheckBox ageVisField = new CheckBox();
		CheckBox friendVisField = new CheckBox();
		CheckBox postVisField = new CheckBox();

		gridPane.add(ageVis, 0, 0);
		gridPane.add(ageVisField, 1, 0);
		gridPane.add(friendVis, 0, 1);
		gridPane.add(friendVisField, 1, 1);
		gridPane.add(postVis, 0, 2);
		gridPane.add(postVisField, 1, 2);
	}

	private void addConfirmButton(){
		confirmButton = new Button("Confirm");
		gridPane.add(confirmButton, 1, 9 + rwSettings);
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
}
