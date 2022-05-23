package Interface;
import enums.TurningDirection;
import Exception.OutOfTableBoundException;
//A movable can move and turn
public interface Movable {
	public void move() throws OutOfTableBoundException;
	public void turn(TurningDirection direction);
}
