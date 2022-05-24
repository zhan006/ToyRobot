package test;

import static org.junit.Assert.*;


import org.junit.Test;

import classes.Table;
import enums.Direction;
import enums.TurningDirection;
import exceptions.InvalidCommandException;
import exceptions.OutOfTableBoundException;
import classes.Robot;
import classes.Command;
import classes.Console;
import classes.Position;
public class TestConsole { 
	private Robot robot = new Robot(Table.initTable());
	private Console console = new Console(robot);
	
	@Test
	public final void testHandlePlace() {
		console.handlePlace(new String[] {"3","4","NORTH"});
		assertEquals(robot.getPosition().getX(), 3);
		assertEquals(robot.getPosition().getY(),4);
		assertEquals(robot.getDirection(),Direction.NORTH);
	}

	@Test
	public final void testHandleInvalidMove() {
		try {
			robot.setPosition(new Position(4,4));
			robot.setDirection(Direction.NORTH);
		} catch (OutOfTableBoundException e) {
			fail("should not throw exception at (4,4)");
		}
		console.handleMove();
		assertEquals(4,robot.getPosition().getX());
		assertEquals(4,robot.getPosition().getY());
	}
	@Test
	public final void testHandleValidMove() {
		try {
			robot.moveTo(1, 1);
			robot.setDirection(Direction.EAST);
		} catch (OutOfTableBoundException e) {
			fail("should not throw exception at (1,1)");
		}
		console.handleMove();
		assertEquals(robot.getPosition().getX(),2);
		assertEquals(robot.getPosition().getY(),1);
	}
	@Test
	public final void testHandleTurn() {
		robot.turnTo(Direction.EAST);
		console.handleTurn(TurningDirection.LEFT);
		assertEquals(robot.getDirection(),Direction.NORTH);
	}

	@Test
	public final void testHandleReport() {
		try {
			robot.setPosition(new Position(3,4));
		} catch (OutOfTableBoundException e) {
			fail("should not throw exception at (3,4)");
		}
		robot.setDirection(Direction.WEST);
		console.handleReport();
	}

	@Test
	public final void testExecuteCommand() {
		try {
			robot.setPosition(new Position(1,1));
		} catch (OutOfTableBoundException e1) {
			fail("should not throw exception at (1,1)");
		}
		robot.setDirection(Direction.EAST);
		try {
			console.executeCommand(new Command("MOVE"));
			assertEquals(robot.getPosition().getX(),2);
			assertEquals(robot.getPosition().getY(),1);
		} catch (InvalidCommandException e) {
			fail("should not throw exception in valid move");
		}
	}

}
