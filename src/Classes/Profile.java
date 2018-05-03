package Classes;

import java.util.ArrayList;

public class Profile
{
	String firstName;
	String lastName;
	int age;
	String gender;
	String location;
	String education;
	String aboutMe;
	String profileImage;
	ArrayList<Profile> friends;
	ArrayList<Post> posts;
	int ageVis;
	int friendVis;
	int postVis;


    
    public Profile(String firstName , String lastName , int age, String gender, String location, String education, String aboutMe,
				   String profileImage, ArrayList<Profile> friends, ArrayList<Post> posts, int ageVis, int friendVis, int postVis)
    {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.location = location;
		this.education = education;
		this.aboutMe = aboutMe;
		this.profileImage = profileImage;
		this.friends = friends;
		this.posts = posts;
		this.ageVis = ageVis;
		this.friendVis = friendVis;
		this.postVis = postVis;
    }
    

	public Profile(String firstName, String lastName, int age) //RELIC CONSTRUCTOR. SHOULD BE REMOVED EVENTUALLY
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
}