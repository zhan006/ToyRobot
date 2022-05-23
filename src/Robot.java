import Interface.Movable;
import enums.Direction;
import enums.TurningDirection;
import Exception.OutOfTableBoundException;
public class Robot implements Movable{
	private Position position = null;
	//Assume the default direction set to be north
	private Direction direction = null;
	//default moving step to be 1;
	private final int STEP=1;
	//initialize table with boundary (5,5,0,0)
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
	
	public void setPosition(int x,int y) throws OutOfTableBoundException {
		if(x < this.table.getLeft() || x > this.table.getRight() || y < this.table.getBottom() || y > this.table.getTop()) {
			throw new OutOfTableBoundException();
		}
		this.position.setX(x);
		this.position.setY(y);
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public void setDirection(Direction newDirection) {
		this.direction = newDirection;
	}
	@Override
	public void move() throws OutOfTableBoundException {
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
	public void turn(TurningDirection direction) {
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
}
