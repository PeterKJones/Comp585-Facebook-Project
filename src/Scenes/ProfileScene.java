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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import Other.Post;

import java.util.ArrayList;

public class ProfileScene
{
	//This covers both the logged in account's profile AND friend's profiles that are clicked.

    Scene scene;
    final int TITLE_FONT_SIZE = 14;
    final int AVATAR_DIMENSIONS = 80; //both width and height.

    public ProfileScene()
    {
        SplitPane splitPane = new SplitPane();

        ImageView avatarImageV = new ImageView();
        Image avatarImage = new Image("Images/fbl_default.png",AVATAR_DIMENSIONS,AVATAR_DIMENSIONS,false,true);
        avatarImageV.setImage(avatarImage);

        Text ageLabel = new Text("Age: ");
        Text statusLabel = new Text("Status: ");
        Text statusDescription = new Text("");
        Button settingsButton = new Button("Settings");
        settingsButton.setText("Settings");
        Button logoutButton = new Button("Logout");

        //Handles the left side of the profile window. ==========================================
        BorderPane leftBP = new BorderPane();
        VBox leftTopVB = new VBox();
        VBox leftBottomVB = new VBox();
        leftTopVB.minWidthProperty().bind(splitPane.widthProperty().multiply(.15)); //Sets the minimum width of the left divider.
        leftTopVB.setPadding(new Insets(10));
        leftTopVB.setSpacing(8);
        leftTopVB.getChildren().add(avatarImageV);
        leftTopVB.getChildren().add(ageLabel);
        leftTopVB.getChildren().add(statusLabel);
        leftTopVB.getChildren().add(statusDescription);

        leftBottomVB.setPadding(new Insets(10));
        leftBottomVB.setSpacing(8);
        leftBottomVB.getChildren().add(settingsButton);
        leftBottomVB.getChildren().add(logoutButton);

        //adding both VBoxes to the left border pane and then sending that BP to the splitpane parent.
        leftBP.setLeft(leftTopVB);
        leftBP.setBottom(leftBottomVB);
        splitPane.getItems().add(leftBP);


        //Handles the center of the profile window. ===========================================================
        VBox middleVP = new VBox();
        middleVP.setAlignment(Pos.TOP_CENTER);
        Text myPostsLabel = new Text("My Posts");
        myPostsLabel.setFont(Font.font("Arial",FontWeight.BOLD,TITLE_FONT_SIZE));
        ArrayList<Post> postArray = initializePosts();
        middleVP.getChildren().add(myPostsLabel);

        //insert post class

        splitPane.getItems().add(middleVP);

        //Handles the right side of the profile window. ========================================================
        VBox rightVP = new VBox();
        rightVP.setAlignment(Pos.TOP_CENTER);
        rightVP.minWidthProperty().bind(splitPane.widthProperty().multiply(.2)); //Sets the minimum width of the right divider.
        Label friendsListLabel = new Label("Friends List");
        friendsListLabel.setFont(Font.font("Arial",FontWeight.BOLD,TITLE_FONT_SIZE));
        rightVP.getChildren().add(friendsListLabel);

        splitPane.getItems().add(rightVP);

        splitPane.setDividerPositions(0.2f, 0.8f);

        scene = new Scene(splitPane, 800, 500);


    }

    public Scene getScene()
    {
        return scene;
    }

    public ArrayList<Post> initializePosts()
    {
        ArrayList<Post> temp = new ArrayList<Post>();
        //get all posts here from the database and return them as a post array.
        return temp;
    }

}
