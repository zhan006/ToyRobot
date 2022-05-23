import java.util.Scanner;
import enums.Direction;
import enums.TurningDirection;
import Exception.OutOfTableBoundException;
import Exception.InvalidCommandException;
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
		int x,y;
		xStr = commandArgs[0];
		yStr = commandArgs[1];
		directionStr = commandArgs[2];
		Direction direction = Direction.valueOf(directionStr);
		x = Integer.parseInt(xStr);
		y = Integer.parseInt(yStr);
		
		try {
			this.robot.setPosition(x, y);
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
		}
	}
	
	public void handleTurn(TurningDirection direction) {
		this.robot.turn(direction);
	}
	
	public void handleReport() {
		String message = this.robot.getDirection().toString()+", "+this.robot.getPosition().getX()+", "+this.robot.getPosition().getY();
		System.out.println(message);
	}
	

	public static void main(String[] args) {
		System.out.println("hello word");
		try (Scanner scanner = new Scanner(System.in)) {
			Console console = new Console();
			while(true) {
				String commandStr = scanner.nextLine().trim();
				Command command=null;
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
			}
		}
	}
}
