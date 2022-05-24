package exceptions;

public class OutOfTableBoundException extends Exception{
	public OutOfTableBoundException() {
		super("Command ignored, the robot will fall out of the table");
	}

}
