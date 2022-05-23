package Exception;

public class PositionNotInitializedException extends Exception{
	public PositionNotInitializedException() {
		super("direction and place have not been initalized");
	}
}
