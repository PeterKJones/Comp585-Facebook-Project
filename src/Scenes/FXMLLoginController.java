package Scenes;

import java.io.File;
import java.awt.Desktop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.IOException;


public class FXMLLoginController {

    //@FXML GridPane pane;
    @FXML private GridPane rootPane;
    private Stage stg;
    final FileChooser fileChooser = new FileChooser();
    private Desktop desktop;
//login page
    @FXML PasswordField passField;
    @FXML TextField userField;
    @FXML Text password;
    @FXML Text username;
    @FXML Button newUserButton;
    @FXML public Button loginButton;


    public FXMLLoginController(){



    }


    @FXML void handleLoginButton(ActionEvent e) throws IOException{

        GridPane pane = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        rootPane.getChildren().setAll(pane);



        /* Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Profile");
        alert2.setHeaderText("This is the Profile");
        alert2.setContentText("This is the Profile text");
        alert2.showAndWait();*/
    }
    @FXML void handleNewUserButton(ActionEvent e){
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("New User");
        alert2.setHeaderText("This is the New User");
        alert2.setContentText("This is the New User text");
        alert2.showAndWait();
    }




}
