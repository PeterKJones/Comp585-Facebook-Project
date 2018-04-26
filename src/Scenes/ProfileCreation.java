package Scenes;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProfileCreation
{
	StackPane layout;
	Scene scene;
	private Desktop desktop;
	public Button confirmButton;
	public Button browseButton;
	final FileChooser fileChooser = new FileChooser();
	public ToggleGroup genderGroup;
	public TextField fNameField;
	public TextField lNameField;
	public ComboBox<String> ageBox;
	public TextField locationField;
	public ComboBox<String> levOfEduBox;
	public TextField aboutMeField;
	public ProfileCreation(Stage mainWindow)
	{
		
		//left column
		Text firstName = new Text("First Name: ");
		Text lastName = new Text("Last Name: ");
		Text age = new Text("Age: ");
		Text gender = new Text("Classes.Gender: ");
		Text location = new Text("Location: ");
		Text education = new Text("Level of Education: ");
		Text aboutMe = new Text("About Me: ");
		Text profileImage = new Text("Profile Picture: ");
		
		//right column
		 fNameField = new TextField();
		 lNameField = new TextField();
		 ageBox = new ComboBox<String>();
		for (int i = 1; i <= 100; i++) //populates the age box for ages 1 to 100
		{
			ageBox.getItems().add(String.valueOf(i));
		}
		genderGroup = new ToggleGroup();
		RadioButton genderMale = new RadioButton("Male");
		genderMale.setToggleGroup(genderGroup);
		RadioButton genderFemale = new RadioButton("Female");
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
	                File file = fileChooser.showOpenDialog(mainWindow);
	                if (file != null) {
	                    openFile(file);
	                }
	            }
	        });
		
		confirmButton = new Button("Confirm");
		
		//setting location
		GridPane gridPane = new GridPane();
		gridPane.setMinSize(400, 200); 
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 
		gridPane.setVgap(5); 
	    gridPane.setHgap(5);       
	    gridPane.setAlignment(Pos.CENTER); 
	    gridPane.add(firstName, 0, 0); 
	    gridPane.add(lastName, 0, 1); 
	    gridPane.add(age, 0, 2); 
	    gridPane.add(gender, 0, 3);
	    gridPane.add(location, 0, 5);
	    gridPane.add(education, 0, 6);
	    gridPane.add(aboutMe, 0, 7);
	    gridPane.add(profileImage, 0, 8);
	    
	    
	    gridPane.add(fNameField, 1, 0); 
	    gridPane.add(lNameField, 1, 1); 
	    gridPane.add(ageBox, 1, 2);
	    gridPane.add(genderMale, 1, 3); 
	    gridPane.add(genderFemale, 1, 4); 
	    gridPane.add(locationField, 1, 5);
	    gridPane.add(levOfEduBox, 1, 6);
	    gridPane.add(aboutMeField, 1, 7);
	    gridPane.add(browseButton, 1, 8);
	    gridPane.add(confirmButton, 1, 9);
		
		
		StackPane layout = new StackPane();
		
		layout.getChildren().add(gridPane);
		
		scene = new Scene(layout, 400, 400);
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
