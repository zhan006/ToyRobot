package Exception;

public class OutOfTableBoundException extends Exception{
	public OutOfTableBoundException() {
		super("The robot fell out of the table");
	}

}
