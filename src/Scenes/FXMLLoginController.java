package Scenes;

import java.io.File;
import java.awt.Desktop;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;


public class FXMLLoginController {

    //@FXML GridPane pane;
    @FXML private GridPane rootPane;
    @FXML Scene scene;
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

        Node source = (Node) e.getSource();
        Window theStage = source.getScene().getWindow();
        GridPane pane = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        theStage.setHeight(400);
        theStage.setWidth(400);
        rootPane.getChildren().setAll(pane);

    }
    @FXML void handleNewUserButton(ActionEvent e) throws IOException{
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("New User");
        alert2.setHeaderText("This is the New User");
        alert2.setContentText("This is the New User text");
        alert2.showAndWait();
        Node source = (Node) e.getSource();
        Window theStage = source.getScene().getWindow();
        GridPane pane = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        theStage.setHeight(400);
        theStage.setWidth(400);
        rootPane.getChildren().setAll(pane);
    }




}

