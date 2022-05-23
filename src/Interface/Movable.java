package Interface;
import enums.TurningDirection;
import Exception.OutOfTableBoundException;
import Exception.PositionNotInitializedException;
//A movable can move and turn
public interface Movable {
	public void move() throws OutOfTableBoundException,PositionNotInitializedException;
	public void turn(TurningDirection direction) throws PositionNotInitializedException;
}
