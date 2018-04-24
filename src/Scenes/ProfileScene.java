package Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import Classes.Post;

import java.util.ArrayList;

public class ProfileScene
{
	//This covers both the logged in account's profile AND friend's profiles that are clicked.
    final int TITLE_FONT_SIZE = 14;
    final int AVATAR_DIMENSIONS = 80; //both width and height.

    private Scene scene;
    private SplitPane splitPane;
    private ScrollPane middleSP;
    private BorderPane leftBP, middleBP;
    private VBox leftTopVB, leftBottomVB, topMiddleVB, bottomMiddleVB, rightVB;
    private HBox bottomMiddleHB;

    private ImageView avatarImageV;
    private Image avatarImage;
    private Text ageLabel, statusLabel, statusDescription, friendsListLabel, myPostsLabel;
    private TextArea postDescription;
    private ArrayList<Post> postArray;
    private Button writeMsgButton, postButton, cancelButton;
    public Button settingsButton,logoutButton;



    public ProfileScene()
    {
        splitPane = new SplitPane();

        avatarImageV = new ImageView();
        avatarImage = new Image("Images/fbl_default.png",AVATAR_DIMENSIONS,AVATAR_DIMENSIONS,false,true);
        avatarImageV.setImage(avatarImage);

        ageLabel = new Text("Age: ");
        statusLabel = new Text("Status: ");
        statusDescription = new Text("");
        settingsButton = new Button("Settings");
        settingsButton.setText("Settings");
        logoutButton = new Button("Logout");

        //Handles the left side of the profile window. ==========================================
        leftBP = new BorderPane();
        leftTopVB = new VBox();
        leftBottomVB = new VBox();
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
        middleBP = new BorderPane();
        topMiddleVB = new VBox();
        bottomMiddleVB = new VBox();

        //topmiddle section
        middleSP = new ScrollPane();
        topMiddleVB.setAlignment(Pos.TOP_CENTER);
        myPostsLabel = new Text("My Posts");
        myPostsLabel.setFont(Font.font("Arial",FontWeight.BOLD,TITLE_FONT_SIZE));
        postArray = initializePosts();
        topMiddleVB.getChildren().add(myPostsLabel);
        middleSP.setContent(topMiddleVB);

        //bottommiddle section
        Separator separator = new Separator();
        writeMsgButton = new Button("Write Message");
        writeMsgButton.setOnAction(e -> loadMakePostComponent());
        bottomMiddleHB = new HBox();
        bottomMiddleHB.setSpacing(8);
        bottomMiddleHB.setAlignment(Pos.BOTTOM_RIGHT);
        postButton = new Button("Post");
        postButton.setOnAction(e -> collapseMakePostComponent());
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> collapseMakePostComponent());
        bottomMiddleHB.getChildren().addAll(postButton,cancelButton);
        postDescription = new TextArea();
        postDescription.setWrapText(true);
        postDescription.setMinHeight(80);
        postDescription.setMaxHeight(100);

        postDescription.setVisible(false);
        postDescription.setManaged(false);
        bottomMiddleHB.setVisible(false);
        bottomMiddleHB.setManaged(false);



        bottomMiddleVB.setPadding(new Insets(5));
        bottomMiddleVB.setSpacing(8);
        bottomMiddleVB.getChildren().addAll(separator,postDescription,bottomMiddleHB,writeMsgButton);
        bottomMiddleVB.setAlignment(Pos.BOTTOM_RIGHT);
        middleBP.setBottom(bottomMiddleVB);

        //

        //insert post class
        middleBP.setTop(topMiddleVB);
        splitPane.getItems().add(middleBP);

        //Handles the right side of the profile window. ========================================================
        rightVB = new VBox();
        rightVB.setAlignment(Pos.TOP_CENTER);
        rightVB.minWidthProperty().bind(splitPane.widthProperty().multiply(.2)); //Sets the minimum width of the right divider.
        friendsListLabel = new Text("Friends List");
        friendsListLabel.setFont(Font.font("Arial",FontWeight.BOLD,TITLE_FONT_SIZE));
        rightVB.getChildren().add(friendsListLabel);

        splitPane.getItems().add(rightVB);

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

    private void loadMakePostComponent()
    {
        writeMsgButton.setVisible(false);
        writeMsgButton.setManaged(false); //button becomes not visible and collapsed(doesnt intervene with other controls)
        postDescription.setText("");
        postDescription.setVisible(true);
        postDescription.setManaged(true);
        postDescription.requestFocus();
        bottomMiddleHB.setVisible(true);
        bottomMiddleHB.setManaged(true);

        //Insert here: add the new post to the arraylist and database.
    }

    private void collapseMakePostComponent()
    {
        writeMsgButton.setVisible(true);
        writeMsgButton.setManaged(true); //button becomes not visible and collapsed(doesnt intervene with other controls)
        postDescription.setVisible(false);
        postDescription.setManaged(false);
        bottomMiddleHB.setVisible(false);
        bottomMiddleHB.setManaged(false);
    }

}
