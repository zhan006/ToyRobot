package interfaces;
import exceptions.PositionNotInitializedException;
public interface Reportable {
	public String report() throws PositionNotInitializedException;
}
