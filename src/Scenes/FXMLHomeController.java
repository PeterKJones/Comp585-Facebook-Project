package Scenes;

import Classes.Post;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;

public class FXMLHomeController
{
	//This covers both the logged in account's profile AND friend's profiles that are clicked.
    final int TITLE_FONT_SIZE = 14;
    final int AVATAR_DIMENSIONS = 80; //both width and height.

    @FXML private Scene scene;
    @FXML SplitPane splitPane;
    @FXML private ScrollPane middleSP;
    @FXML private BorderPane leftBP; @FXML private BorderPane middleBP;
    @FXML private VBox leftTopVB, leftBottomVB, topMiddleVB, bottomMiddleVB, rightVB;
    @FXML private HBox bottomMiddleHB;

    @FXML private ImageView avatarImageV;
    @FXML private Image avatarImage;
    @FXML private Text ageLabel, statusLabel, statusDescription, friendsListLabel, myPostsLabel;
    @FXML private TextArea postDescription;
    @FXML private ArrayList<Post> postArray;
    @FXML private Button writeMsgButton, postButton, cancelButton;
    @FXML public Button settingsButton,logoutButton;



    public FXMLHomeController()
    {
       /* splitPane = new SplitPane();

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

*/
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

    @FXML void handleSettingsButton(ActionEvent e) throws IOException {
        Node source = (Node) e.getSource();
        Window theStage = source.getScene().getWindow();
        GridPane pane = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        theStage.setHeight(400);
        theStage.setWidth(400);

        splitPane.getItems().setAll(pane);

    }
}
