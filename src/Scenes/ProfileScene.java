package Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProfileScene
{
	//This covers both the logged in account's profile AND friend's profiles that are clicked.

    Scene scene;
    final int TITLE_FONT_SIZE = 14;

    public ProfileScene()
    {
        ImageView avatarImageV = new ImageView();
        Image avatarImage = new Image("file:fbl_default.png");
        avatarImageV.setImage(avatarImage);

        Label ageLabel = new Label("Age: ");
        Label statusLabel = new Label("Status: ");
        statusLabel.setFont(Font.font("Arial",FontWeight.BOLD,TITLE_FONT_SIZE));
        Text statusDescription = new Text();
        Button settingsButton = new Button("Settings");
        Button logoutButton = new Button("Logout");

        //Handles left side of the profile window.
        BorderPane leftBP = new BorderPane();
        VBox leftVB = new VBox();
        leftVB.setPadding(new Insets(10));
        leftVB.setSpacing(8);
        leftVB.getChildren().add(avatarImageV);
        leftVB.getChildren().add(ageLabel);
        leftVB.getChildren().add(statusLabel);
        leftVB.getChildren().add(statusDescription);
        leftVB.getChildren().add(settingsButton);
        leftVB.getChildren().add(logoutButton);

        leftBP.getChildren().add(leftVB);

        //Handles the center of the profile window.
        BorderPane middleBP = new BorderPane();
        VBox middleVP = new VBox();
        Label myPostsLabel = new Label("My Posts");
        myPostsLabel.setFont(Font.font("Arial",FontWeight.BOLD,TITLE_FONT_SIZE));
        middleVP.getChildren().add(myPostsLabel);

        middleBP.getChildren().add(leftVB);
        //insert post class


        BorderPane rightBP = new BorderPane();
        Label friendsListLabel = new Label("Friends List");
        myPostsLabel.setFont(Font.font("Arial",FontWeight.BOLD,TITLE_FONT_SIZE));




        SplitPane splitPaneLeft, splitPaneRight; //may not need this.





        StackPane layout = new StackPane();

        layout.getChildren().add(leftBP);
        layout.getChildren().add(middleBP);
        layout.getChildren().add(rightBP);

        scene = new Scene(layout, 400, 200);
    }

    public Scene getScene()
    {
        return scene;
    }
}
