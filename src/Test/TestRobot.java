package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.Position;
import classes.Robot;
import classes.Table;
import enums.Direction;
import enums.TurningDirection;
import exceptions.OutOfTableBoundException;
import exceptions.PositionNotInitializedException;
import interfaces.Placable;
public class TestRobot {

	@Test
	public final void testRobot() {
		Robot robot = new Robot((x,y)->{return true;}) ;
		assertTrue(robot.getTable().isValidPlace(100, 100));
	}
	@Test
	public final void testSetPositionIntInt() {
		Robot robot = new Robot(Table.initTable());
		robot.setDirection(Direction.EAST);
		try {
			robot.setPosition(new Position(0,0));
			robot.setPosition(1, 1);
		} catch (OutOfTableBoundException | PositionNotInitializedException e) {
			fail("Exceptions should not throw");
		}
		assertEquals(1,robot.getPosition().getX());
	}
	@Test
	public final void testSetPositionIntIntThrowException() {
		Robot robot = new Robot(Table.initTable());
		robot.setDirection(Direction.EAST);
		try {
			robot.setPosition(1, 1);
		} catch (OutOfTableBoundException | PositionNotInitializedException e) {
			assertEquals(e.getMessage(),"Command ignored, direction and place have not been initalized");
		}
	}
	@Test
	public final void testSetPositionPosition() {
		Robot robot = new Robot(Table.initTable());
		robot.setDirection(Direction.EAST);
		try {
			robot.setPosition(new Position(1,1));
		} catch (OutOfTableBoundException e) {
			fail("Exceptions should not throw");
		}
		assertEquals(1,robot.getPosition().getX());
	}
	@Test()
	public final void testSetPositionPositionNotInitializedException() {
		Robot robot = new Robot(Table.initTable());
		try{
			robot.setPosition(1,1);
			fail("PositionNotInitializedException should be thrown");
		}catch(PositionNotInitializedException e) {
			assertEquals(e.getMessage(),"Command ignored, direction and place have not been initalized");
		} catch (OutOfTableBoundException e) {
			fail("outofbound exception should not be thrown");
		}
	}
	@Test
	public final void testSetDirection() {
		Robot robot = new Robot(Table.initTable());
		robot.setDirection(Direction.WEST);
		assertEquals(Direction.WEST,robot.getDirection());
	}

	@Test
	public final void testTurnTo() {
		Robot robot = new Robot(Table.initTable());
		robot.turnTo(Direction.WEST);
		assertEquals(Direction.WEST,robot.getDirection());
	}

	@Test
	public final void testMoveTo() {
		Robot robot = new Robot(Table.initTable());
		try {
			robot.moveTo(3, 3);
			assertEquals(robot.getPosition().getX(),3);
			assertEquals(robot.getPosition().getY(),3);
		} catch (OutOfTableBoundException e) {
			fail("Should not throw outoftablebound exception when to a valid position");
		}
		
	}
	@Test
	public final void testMoveToThrowException() {
		Robot robot = new Robot(Table.initTable());
		try {
			robot.moveTo(5, 5);
			fail("should throw exception when move to a invalid position");
		} catch (OutOfTableBoundException e) {
			assertEquals(e.getMessage(), "Command ignored, the robot will fall out of the table");
		}
	}
	@Test
	public final void testMove() {
		Robot robot = new Robot(Table.initTable());
		try {
			robot.setPosition(new Position(1,1));
			robot.setDirection(Direction.EAST);
			robot.move();
			assertEquals(robot.getPosition().getX(),2);
			assertEquals(robot.getPosition().getY(),1);
		} catch (OutOfTableBoundException | PositionNotInitializedException e) {
			fail("Exception should not thow in valid move");
		}

	}
	@Test
	public final void testMoveWhenNotInitalizedPosition() {
		Robot robot = new Robot(Table.initTable());
		try {
			robot.move();
			fail("should throw exception when not initalizing position");
		} catch (PositionNotInitializedException | OutOfTableBoundException e) {
			assertEquals(e.getMessage(),"Command ignored, direction and place have not been initalized");
		}
	}
	@Test
	public final void testMoveWhenGoingOutOfTheTable() {
		Robot robot = new Robot(Table.initTable());
		try {
			robot.moveTo(4,4);
			robot.setDirection(Direction.EAST);
			robot.move();
			fail("should throw exception when going out of the table");
		} catch (PositionNotInitializedException | OutOfTableBoundException e) {
			assertEquals(e.getMessage(),"Command ignored, the robot will fall out of the table");
		}
	}
	@Test
	public final void testTurnRight() {
		Robot robot = new Robot(Table.initTable());
		robot.setDirection(Direction.NORTH);
		try {
			robot.turn(TurningDirection.RIGHT);
			assertEquals(robot.getDirection(),Direction.EAST);
		} catch (PositionNotInitializedException e) {
			fail("should not throw exception when making a valid turn");
		}
	}
	@Test
	public final void testTurnLeft() {
		Robot robot = new Robot(Table.initTable());
		robot.setDirection(Direction.NORTH);
		try {
			robot.turn(TurningDirection.LEFT);
			assertEquals(robot.getDirection(),Direction.WEST);
		} catch (PositionNotInitializedException e) {
			fail("should not throw exception when making a valid turn");
		}
	}
	@Test
	public final void testReport() {
		Robot robot = new Robot(Table.initTable());
		robot.setDirection(Direction.NORTH);
		try {
			robot.setPosition(new Position(3,3));
		} catch (OutOfTableBoundException e) {
			fail("should not throw exception when setting valid position");
		}
		try {
			assertEquals(robot.report(),"NORTH, 3, 3");
		} catch (PositionNotInitializedException e) {
			fail("should not throw exception when having valid position");
		}
	}

}
