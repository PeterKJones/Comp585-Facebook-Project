import java.time.LocalTime;

public class Post
{
	String message;
	String time;
	
	public Post(String msg)
	{
		message = msg;
		LocalTime temp = LocalTime.now();
		time = temp.toString();
	}
}
