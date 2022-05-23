import java.util.Scanner;
import enums.Direction;
import enums.TurningDirection;
import Exception.OutOfTableBoundException;
import Exception.InvalidCommandException;
import Exception.PositionNotInitializedException;
public class Console {
	private Robot robot;
	private Table table;
	public Console() {
		this.robot = new Robot();
		this.table = Table.initTable();
		robot.setTable(table);
	}
	public Console(Robot robot,Table table) {
		this.robot = robot;
		this.table = table;
		robot.setTable(table);
	}
	public void handlePlace(String[] commandArgs) {
		String xStr,yStr,directionStr = null;
		int x = 0,y = 0;
		Direction direction = null;
		//argument list should contain x,y,direction
		if(commandArgs.length != 3) {
			System.out.println("Invalid arguments!");
			return;
		}
		xStr = commandArgs[0];
		yStr = commandArgs[1];
		directionStr = commandArgs[2];
		//VALIDATE: direction input
		try {
			 direction = Direction.valueOf(directionStr);
		}catch(IllegalArgumentException e) {
			System.out.println("Invalid direction");
			return;
		}
		//VALIDATE: position input
		try {
			x = Integer.parseInt(xStr);
			y = Integer.parseInt(yStr);
		}catch(NumberFormatException e) {
			System.out.println("Invalid position!");
			return;
		}
		
		try {
			this.robot.setPosition(new Position(x,y));
			this.robot.setDirection(direction);
		}
		catch(OutOfTableBoundException exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	public void handleMove() {
		try {
			this.robot.move();
		}catch(OutOfTableBoundException exception) {
			System.out.println(exception.getMessage());
		}catch(PositionNotInitializedException positionException) {
			System.out.println(positionException.getMessage());
		}
	}
	
	public void handleTurn(TurningDirection direction) {
		try {
			this.robot.turn(direction);

		}catch(PositionNotInitializedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void handleReport() {
		try {
			System.out.println(this.robot.report());
		}catch(PositionNotInitializedException e) {
			System.out.println(e.getMessage());
		}		
	}
	

	public static void main(String[] args) {
		System.out.println("Ready to serve commands!");
		try (Scanner scanner = new Scanner(System.in)) {
			Console console = new Console();
			while(true) {
				try {
					String commandStr = scanner.nextLine().trim();
					Command command = null;
					try {				
						command = new Command(commandStr);
					}
					catch(InvalidCommandException e) {
						System.out.println(e.getMessage());
						continue;
					}
					switch(command.commandType) {
					case PLACE:
						console.handlePlace(command.commandArgs);
						break;
					case RIGHT:
						console.handleTurn(TurningDirection.RIGHT);
						break;
					case LEFT:
						console.handleTurn(TurningDirection.LEFT);
						break;
					case MOVE:
						console.handleMove();
						break;
					case REPORT:
						console.handleReport();
						break;
					default:
						System.out.println("Unknown command!");
					}	
				}catch(Exception e) {
					System.out.println("unknown error!");
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
