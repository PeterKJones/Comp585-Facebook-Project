package Classes;

import java.time.LocalTime;

public class Post
{
	String message;
	int creatorId;
	
	public Post(String msg, int userId)
	{
		creatorId = userId;
		message = msg;
	}
}
