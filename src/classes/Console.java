package classes;

import java.util.Scanner;
import enums.Direction;
import enums.TurningDirection;
import exceptions.InvalidCommandException;
import exceptions.OutOfTableBoundException;
import exceptions.PositionNotInitializedException;
import interfaces.Movable;
import interfaces.Placable;
import interfaces.Reportable;

public class Console {
	private Movable robot;

	public Console() {
		Placable table = (Placable) Table.initTable();
		robot = (Movable) new Robot(table);
	}

	public Console(Movable robot) {
		this.robot = robot;
	}

	public void handlePlace(String[] commandArgs) {
		String xStr, yStr, directionStr = null;
		int x = 0, y = 0;
		Direction direction = null;

		// argument list should contain x,y,direction
		if (commandArgs==null ||commandArgs.length != 3) {
			System.out.println("Invalid arguments!");
			return;
		}
		xStr = commandArgs[0];
		yStr = commandArgs[1];
		directionStr = commandArgs[2];

		// VALIDATE: direction input
		try {
			direction = Direction.valueOf(directionStr);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid direction");
			return;
		}

		// VALIDATE: position input
		try {
			x = Integer.parseInt(xStr);
			y = Integer.parseInt(yStr);
		} catch (NumberFormatException e) {
			System.out.println("Invalid position!");
			return;
		}

		try {
			this.robot.moveTo(x, y);
			this.robot.turnTo(direction);
		} catch (OutOfTableBoundException exception) {
			System.out.println(exception.getMessage());
		}
	}

	public void handleMove() {
		try {
			this.robot.move();
		} catch (OutOfTableBoundException exception) {
			System.out.println(exception.getMessage());
		} catch (PositionNotInitializedException positionException) {
			System.out.println(positionException.getMessage());
		}
	}

	public void handleTurn(TurningDirection direction) {
		try {
			this.robot.turn(direction);

		} catch (PositionNotInitializedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void handleReport() {
		try {
			System.out.println(((Reportable) this.robot).report());
		} catch (PositionNotInitializedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void executeCommand(Command command) {
		switch (command.commandType) {
			case PLACE:
				this.handlePlace(command.commandArgs);
				break;
			case RIGHT:
				this.handleTurn(TurningDirection.RIGHT);
				break;
			case LEFT:
				this.handleTurn(TurningDirection.LEFT);
				break;
			case MOVE:
				this.handleMove();
				break;
			case REPORT:
				this.handleReport();
				break;
			default:
				System.out.println("Unknown command!");
		}
	}

	public static void main(String[] args) {
		System.out.println("Ready to serve commands!");
		try (Scanner scanner = new Scanner(System.in)) {
			Console console = new Console();
			while (true) {
				try {
					String commandStr = scanner.nextLine().trim();
					Command command = null;
					try {
						command = new Command(commandStr);
					} catch (InvalidCommandException e) {
						System.out.println(e.getMessage());
						continue;
					}
					console.executeCommand(command);
				} catch (Exception e) {
					System.out.println("unknown error!");
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
