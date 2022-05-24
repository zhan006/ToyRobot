package classes;

import enums.Direction;
import enums.TurningDirection;
import exceptions.OutOfTableBoundException;
import exceptions.PositionNotInitializedException;
import interfaces.Movable;
import interfaces.Placable;
import interfaces.Reportable;

public class Robot implements Movable, Reportable {
	private Position position = null;
	private Direction direction = null;
	private final int STEP = 1;
	private Placable table = Table.initTable();

	public Robot(Placable table) {
		this.table = table;
	}

	public Placable getTable() {
		return this.table;
	}

	public Position getPosition() {
		return this.position;
	}

	/**
	 * @param table A table element implementing Placable Interface
	 */
	public void setTable(Placable table) {
		this.table = table;
	}

	/**
	 * @param x int
	 * @param y int
	 * @throws OutOfTableBoundException
	 * @throws PositionNotInitializedException
	 */
	public void setPosition(int x, int y) throws OutOfTableBoundException, PositionNotInitializedException {

		if (!table.isValidPlace(x, y)) {
			throw new OutOfTableBoundException();
		}
		if (position == null || direction == null) {
			throw new PositionNotInitializedException();
		}
		this.position.setX(x);
		this.position.setY(y);
	}

	/**
	 * @param position The position in (x,y)
	 * @throws OutOfTableBoundException when the position is out of the table
	 */
	public void setPosition(Position position) throws OutOfTableBoundException {
		if (!table.isValidPlace(position.getX(), position.getY())) {
			throw new OutOfTableBoundException();
		}
		this.position = position;
	}

	/**
	 * @return A direction in (NORTH,SOUTH,EAST,WEST)
	 */
	public Direction getDirection() {
		return this.direction;
	}

	/**
	 * @param newDirection A direction in (NORTH,SOUTH,EAST,WEST)
	 */
	public void setDirection(Direction newDirection) {
		this.direction = newDirection;
	}

	/**
	 * set the robot to the specified direction
	 * 
	 * @param direction A direction in (NORTH,SOUTH,EAST,WEST)
	 */
	@Override
	public void turnTo(Direction direction) {
		setDirection(direction);
	}

	/**
	 * set the robot to the specified position
	 * 
	 * @throws OutOfTableBoundException when the position is not within the table
	 */
	@Override
	public void moveTo(int x, int y) throws OutOfTableBoundException {
		setPosition(new Position(x, y));
	}

	/**
	 * move the robot in 1 unit in current direction
	 * 
	 * @throws OutOfTableBoundException        if next step out of the table
	 * @throws PositionNotInitializedException if the position and direction of
	 *                                         robot not initalized
	 */
	@Override
	public void move() throws OutOfTableBoundException, PositionNotInitializedException {
		if (this.direction == null) {
			throw new PositionNotInitializedException();
		}
		switch (this.direction) {
			case NORTH:
				this.setPosition(this.position.getX(), this.position.getY() + STEP);
				break;
			case SOUTH:
				this.setPosition(this.position.getX(), this.position.getY() - STEP);
				break;
			case EAST:
				this.setPosition(this.position.getX() + STEP, this.position.getY());
				break;
			case WEST:
				this.setPosition(this.position.getX() - STEP, this.position.getY());
				break;
		}
	}

	/**
	 *
	 */
	@Override
	public void turn(TurningDirection direction) throws PositionNotInitializedException {
		if (this.direction == null) {
			throw new PositionNotInitializedException();
		}
		switch (direction) {
			case RIGHT:
				switch (this.direction) {
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
				switch (this.direction) {
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

	/**
	 *
	 */
	@Override
	public String report() throws PositionNotInitializedException {
		if (this.getDirection() == null || this.getPosition() == null) {
			throw new PositionNotInitializedException();

		}
		return this.getDirection().toString() + ", " + this.getPosition().getX() + ", " + this.getPosition().getY();
	}
}
