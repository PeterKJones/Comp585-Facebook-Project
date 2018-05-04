package Classes;

public class Account
{
	private String username;
	private String password; //temporary. Will be replaced with javax.crypto's encryption architecture
	private Profile profile;
	int user_id;

	public Account (String userF, String passF, Profile profileObj, int id)
	{
		username = userF;
		password = passF;
		profile = profileObj;
		user_id	 = id;
	}


	public Account (int id){
		user_id = id;
	}
	public int getId() {
		return user_id;
	}
}
