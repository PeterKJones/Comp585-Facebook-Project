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
	String status;


    
    public Profile(String firstName , String lastName , int age, String gender, String location, String education, String aboutMe,
				   String profileImage, ArrayList<Profile> friends, ArrayList<Post> posts, int ageVis, int friendVis, int postVis, String status)
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
		this.status = status;
    }

	public Profile(String firstName, String lastName, int age) //RELIC CONSTRUCTOR. SHOULD BE REMOVED EVENTUALLY
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getLocation() {
		return location;
	}

	public String getEducation() {
		return education;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public String getStatus(){return status;}

	public ArrayList<Profile> getFriends() {
		return friends;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public int getAgeVis() {
		return ageVis;
	}

	public int getFriendVis() {
		return friendVis;
	}

	public int getPostVis() {
		return postVis;
	}
}