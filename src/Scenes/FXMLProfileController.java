package Scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FXMLProfileController implements Initializable {

    private Stage stg;
    final FileChooser fileChooser = new FileChooser();
    private Desktop desktop;

//profile creation/settings
    @FXML GridPane profileGridPane;
    @FXML Text firstName;
    @FXML Text lastName;
    @FXML Text age;
    @FXML Text gender;
    @FXML Text location;
    @FXML Text education;
    @FXML Text aboutMe;
    @FXML Text profileImage;

    @FXML TextField fNameField;
    @FXML TextField lNameField;
    @FXML ComboBox<String> ageBox;
    @FXML ToggleGroup genderGroup;
    @FXML RadioButton genderMale;
    @FXML RadioButton genderFemale;
    @FXML ComboBox<String> levelOfEduBox;
    @FXML TextField aboutMeField;
    @FXML Button browseButton;

    public FXMLProfileController(){



    }


    @FXML
    void handleBrowseButton(ActionEvent e){
        File file = fileChooser.showOpenDialog(stg);
        if (file != null) {
            openFile(file);
        }
    }

    private void openFile(File file) {
        desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException ex) { System.out.println("Could not open file."); }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 1; i <= 100; i++) //populates the age box for ages 1 to 100
        {
            ageBox.getItems().add(String.valueOf(i));
        }
    }
}
