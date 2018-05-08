package Scenes;

import Classes.Account;
import Classes.Profile;
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
import javafx.scene.text.TextAlignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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
    public TextArea postDescription;
    private ArrayList<Post> postArray;
    private Button writeMsgButton, cancelButton;
    public Button settingsButton,logoutButton, postButton;


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
        //postButton.setOnAction(e -> collapseMakePostComponent());
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

    public void collapseMakePostComponent()
    {
        writeMsgButton.setVisible(true);
        writeMsgButton.setManaged(true); //button becomes not visible and collapsed(doesnt intervene with other controls)
        postDescription.setVisible(false);
        postDescription.setManaged(false);
        bottomMiddleHB.setVisible(false);
        bottomMiddleHB.setManaged(false);
    }

    //loads posts and friends
    public void loadToScene(int id, Profile profile, Boolean isSettings)
    {
        //load profile stuff for a single person
        avatarImage = new Image(profile.getProfileImage(),AVATAR_DIMENSIONS,AVATAR_DIMENSIONS,false,true);
        ageLabel.setText( "Age:  " + profile.getAge());
        statusDescription.setText("    " + profile.getStatus());

        //load all posts
        if(!isSettings) {
                postArray = profile.getPosts();
                //for each post make a new vbox and insert all info into it
                for (Post p : postArray) {
                    VBox vBox = new VBox();
                    vBox.setAlignment(Pos.CENTER_LEFT);
                    vBox.setPadding(new Insets(10));
                    vBox.setSpacing(5);

                    HBox friendsHBox = new HBox();
                    friendsHBox.setAlignment(Pos.CENTER_LEFT);
                    friendsHBox.setPadding(new Insets(10));
                    friendsHBox.setSpacing(5);

                    Text message = new Text(p.getMessage() + " - From " + getPoster(p.getCreatorId()));
                    message.setFont(new Font("Serif", 16));
                    Text time = new Text(new SimpleDateFormat("MMM dd, yyyy 'at' hh:mma").format(p.getTimestamp()));

                    vBox.getChildren().add(message);
                    vBox.getChildren().add(time);

                    //if the post belong to the user, give option to delete
                    if (id == p.getCreatorId()) {
                        Button delete = new Button("Delete");
                        delete.setOnAction(e -> deletePost(p.getPostId(), vBox));
                    /*deleteButtons.add(delete);
                    deleteButtonsId.add(new Integer(p.getPostId()));*/

                        vBox.getChildren().add(delete);
                    }

                    //Now time to load friends of the user
                    Text friendName = new Text("Shaq Eel Oneel");
                    Button friendButton = new Button("View");

                    friendsHBox.getChildren().add(friendName);
                    friendsHBox.getChildren().add(friendButton);

                    topMiddleVB.getChildren().add(vBox);
                    rightVB.getChildren().add(friendsHBox);
                }
        }


    }
    private void deletePost(int postID, VBox vBoxToDelete)
    {
        //when a post is deleted it needs to be removed from the detabase and then need to removed from topMiddleVB

        System.out.println("Deleting post...");
        try {
            Connection connect = getConnection();
            connect.prepareStatement("DELETE FROM posts WHERE id = '" + postID + "';").executeUpdate();
            topMiddleVB.getChildren().remove(vBoxToDelete);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    private String getPoster(int id){
        try {
            Connection connect = getConnection();
            ResultSet result = connect.prepareStatement("SELECT * FROM users WHERE id = '" + id + "';").executeQuery();
            if(result.next()) {
                return result.getString("first_name") + " " + result.getString("last_name");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/facebooklite";
            String username = "root";
            String password = "";
            Class.forName(driver);
            Connection connect = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Established");
            return connect;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
