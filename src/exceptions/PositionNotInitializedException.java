package exceptions;

public class PositionNotInitializedException extends Exception{
	public PositionNotInitializedException() {
		super("Command ignored, direction and place have not been initalized");
	}
}
