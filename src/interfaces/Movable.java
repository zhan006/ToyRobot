package interfaces;
import enums.Direction;
import enums.TurningDirection;
import exceptions.OutOfTableBoundException;
import exceptions.PositionNotInitializedException;
//A movable can move and turn
public interface Movable {
	public void move() throws OutOfTableBoundException,PositionNotInitializedException;
	public void turn(TurningDirection direction) throws PositionNotInitializedException;
	public void moveTo(int x,int y) throws OutOfTableBoundException;
	public void turnTo(Direction direction);
}
