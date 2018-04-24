package Classes;

public class Profile
{
	String firstName;
	String lastName;
	int age;
	Gender gender;
	String Location;
	String education;
	String aboutMe;
	Profile[] newFriends; //Should be renamed to friends once we get rid of old code.
	boolean ageVis;
	boolean friendVis;
	boolean postVis;
	String profileImage;
	Post[] posts;
    
    public Profile(String firstName , String lastName , int age)
    {

    }
}