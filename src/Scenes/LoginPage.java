package Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage
{
	StackPane layout;
	Scene scene;
	public Button newUserButton;
	public Button loginButton;

	public TextField userField;
	public PasswordField passField;
	public LoginPage()
	{
		Text username = new Text("username: ");
		Text password = new Text("password: ");
		userField = new TextField();
		passField = new PasswordField();
		newUserButton = new Button("New User");
		loginButton = new Button("Login");
		
		GridPane gridPane = new GridPane();
		gridPane.setMinSize(400, 200); 
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 
		gridPane.setVgap(5); 
	    gridPane.setHgap(5);       
	    gridPane.setAlignment(Pos.CENTER); 
	    gridPane.add(username, 0, 0); 
	    gridPane.add(userField, 1, 0); 
	    gridPane.add(password, 0, 1);
	    gridPane.add(passField, 1, 1); 
	    gridPane.add(newUserButton, 0, 2);
	    gridPane.add(loginButton, 1, 2); 

		
	    layout = new StackPane();
		
		layout.getChildren().add(gridPane);
		
		scene = new Scene(layout, 400, 200);
	}
	
	public Scene getScene()
	{
		return scene;
	}
}
