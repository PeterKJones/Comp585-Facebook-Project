package Classes;

import java.sql.Timestamp;
import java.time.LocalTime;

public class Post
{
	String message;
	Timestamp timestamp;
	int creatorId;
	int postId;

	public Post(String msg, int userId, Timestamp time, int postId)
	{
		this.postId = postId;
		creatorId = userId;
		message = msg;
		timestamp = time;
	}

	public String getMessage()
	{
		return message;
	}

	public int getPostId() {
		return postId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public int getCreatorId() {
		return creatorId;
	}
}
