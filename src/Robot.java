import Interface.Movable;
import enums.Direction;
import enums.TurningDirection;
import Exception.OutOfTableBoundException;
import Exception.PositionNotInitializedException;
public class Robot implements Movable{
	private Position position = null;
	private Direction direction = null;
	private final int STEP=1;
	private Table table = Table.initTable();
	
	public Robot() {
		
	}
	
	public Table getTable() {
		return this.table;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public void setTable(Table table) {
		this.table = table;
	}
	
	public void setPosition(int x,int y) throws OutOfTableBoundException,PositionNotInitializedException {
		if(x < this.table.getLeft() || x > this.table.getRight() || y < this.table.getBottom() || y > this.table.getTop()) {
			throw new OutOfTableBoundException();
		}
		if(position==null || direction==null) {
			throw new PositionNotInitializedException();
		}
		this.position.setX(x);
		this.position.setY(y);
	}
	
	public void setPosition(Position position) throws OutOfTableBoundException{
		if(position.getX() < this.table.getLeft() || position.getX() > this.table.getRight() || position.getY() < this.table.getBottom() || position.getY() > this.table.getTop()) {
			throw new OutOfTableBoundException();
		}
		this.position = position;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public void setDirection(Direction newDirection) {
		this.direction = newDirection;
	}
	
	@Override
	public void move() throws OutOfTableBoundException,PositionNotInitializedException {
		if(this.direction==null) {
			throw new PositionNotInitializedException();
		}
		switch(this.direction) {
		  case NORTH:
			  this.setPosition(this.position.getX(),this.position.getY()+STEP);
			  break;
		  case SOUTH:
			  this.setPosition(this.position.getX(), this.position.getY()-STEP);
			  break;
		  case EAST:
			  this.setPosition(this.position.getX()+STEP, this.position.getY());
			  break;
		  case WEST:
			  this.setPosition(this.position.getX()-STEP, this.position.getY());
			  break;
		}
	}
	
	@Override
	public void turn(TurningDirection direction) throws PositionNotInitializedException {
		if(this.direction == null) {
			throw new PositionNotInitializedException();
		}
		switch(direction) {
		  case RIGHT:
			switch(this.direction) {
			  case NORTH:
				  this.direction = Direction.EAST;
				  break;
			  case SOUTH:
				  this.direction = Direction.WEST;
				  break;
			  case EAST:
				  this.direction = Direction.SOUTH;
				  break;
			  case WEST:
				  this.direction = Direction.NORTH;
				  break;
			}
		  break;
		  case LEFT:
			switch(this.direction) {
			  case NORTH:
				  this.direction = Direction.WEST;
				  break;
			  case SOUTH:
				  this.direction = Direction.EAST;
				  break;
			  case EAST:
				  this.direction = Direction.NORTH;
				  break;
			  case WEST:
				  this.direction = Direction.SOUTH;
				  break;
			}
		  break;
		}
	}
	
	public String report() throws PositionNotInitializedException {
		if(this.getDirection()==null ||this.getPosition()==null) {
			throw new PositionNotInitializedException();

		}
		return this.getDirection().toString()+", "+this.getPosition().getX()+", "+this.getPosition().getY();
	}
}
