package Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Settings {

    StackPane layout;
    Scene scene;
    Button btnSave, btnCancel;


    public Settings(Stage mainWindow)
    {
        ToggleGroup genderGrp = new ToggleGroup();
        Text ageVis = new Text("Age Visability: ");
        Text friendVis = new Text("Friend Visability: ");
        Text postVis = new Text("Post Visability: ");
        Text status = new Text("Status: ");
        Text age = new Text("Age: ");
        Text education = new Text("Level of Education: ");
        RadioButton m = new RadioButton("Male");
        m.setToggleGroup(genderGrp);
        RadioButton f = new RadioButton("Female");
        f.setToggleGroup(genderGrp);
        ComboBox<String> educationCmbBx = new ComboBox<String>();
        educationCmbBx.getItems().addAll("None","Some High School","High School","College Undergraduate","College Graduate","PhD");
        Text aboutMe = new Text("About Me: ");

        CheckBox ageVisField = new CheckBox();
        CheckBox friendVisField = new CheckBox();
        CheckBox postVisField = new CheckBox();
        TextField statusField = new TextField();
        TextField ageField = new TextField();
        //TextField genderField = new TextField();
        //TextField educationField = new TextField();
        TextField aboutMeField = new TextField("");

        btnSave = new Button("Save");
        btnCancel = new Button("Cancel");

        ScrollPane sp = new ScrollPane();
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 300);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(ageVis, 0, 0);
        gridPane.add(ageVisField, 1, 0);
        gridPane.add(friendVis, 0, 1);
        gridPane.add(friendVisField, 1, 1);
        gridPane.add(postVis, 0, 2);
        gridPane.add(postVisField, 1, 2);
        gridPane.add(status, 0, 3);
        gridPane.add(statusField, 1, 3);
        gridPane.add(age, 0, 4);
        gridPane.add(ageField, 1, 4);
        gridPane.add(m, 0, 5);
        gridPane.add(f, 1, 5);
        gridPane.add(education, 0, 6);
        gridPane.add(educationCmbBx, 1, 6);
        gridPane.add(aboutMe, 0,7);
        gridPane.add(aboutMeField, 1, 7);
        sp.setContent(gridPane);
//        gridPane.add(username, 0, 0);
//        gridPane.add(userField, 1, 0);
//        gridPane.add(password, 0, 1);
//        gridPane.add(passField, 1, 1);
//        gridPane.add(newUserButton, 0, 2);
//        gridPane.add(loginButton, 1, 2);



        StackPane layout = new StackPane();

        layout.getChildren().add(sp);

        scene = new Scene(layout, 400, 200);
    }

    public Scene getScene()
    {
        return scene;
    }
}
