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
	Profile[] friends; //Array of other profiles, people youve friend. pull avatar, name, maybe even piece of their status
						//when someone clicks on
	boolean ageVis;
	boolean friendVis;
	boolean postVis;
	String profileImage;
	Post[] posts; //Array of all posts this user has made
    
    public Profile(String firstName , String lastName , int age)
    {

    }
}