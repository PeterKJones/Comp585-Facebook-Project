package Classes;

public class Account
{
	String username;
	String password; //temporary. Will be replaced with javax.crypto's encryption architecture
	Profile profile;
	int user_id;

	public Account (int id){
		user_id = id;
	}
	public int getId() {
		return user_id;
	}
}
