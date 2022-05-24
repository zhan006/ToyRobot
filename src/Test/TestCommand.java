package test;

import static org.junit.Assert.*;

import org.junit.Test;
import classes.Command;
import exceptions.InvalidCommandException;
public class TestCommand {

	@Test
	public final void testPlaceCommand() {
		Command command;
		try {
			command = new Command("PLACE 1,1,NORTH");
			assertEquals(command.commandType,Command.CommandType.PLACE);
			assertTrue(command.commandArgs.length==3);
		} catch (InvalidCommandException e) {
			fail("command is correct");
		}
	}
	@Test
	public final void testTurnCommand() {
		Command command;
		try {
			command = new Command("RIGHT");
			assertEquals(command.commandType,Command.CommandType.RIGHT);
			command = new Command("LEFT");
			assertEquals(command.commandType,Command.CommandType.LEFT);

		}catch(InvalidCommandException e) {
			fail("command is correct");
		}
	}
	@Test
	public final void testMoveCommand() {
		Command command;
		try {
			command = new Command("MOVE");
			assertEquals(command.commandType,Command.CommandType.MOVE);
			assertEquals(command.commandType,Command.CommandType.MOVE);
		}catch(InvalidCommandException e) {
			fail("command is correct");
		}
	}
	@Test
	public final void testReportCommand() {
		Command command;
		try {
			command = new Command("REPORT");
			assertEquals(command.commandType,Command.CommandType.REPORT);
			assertEquals(command.commandType,Command.CommandType.REPORT);
		}catch(InvalidCommandException e) {
			fail("command is correct");
		}
	}
	@Test
	public final void testWrongCommand() {
		Command command;
		try {
			command = new Command("wrong command");
			fail("wrong command should throw exceptions");
		}catch(InvalidCommandException e) {
			assertEquals("Invalid Command!",e.getMessage());
		}
	}

}
