import Exception.InvalidCommandException;
enum CommandType{
	PLACE,
	RIGHT,
	LEFT,
	MOVE,
	REPORT
}
public class Command {
	public CommandType commandType;
	public String[] commandArgs;
	public Command(String commandStr) throws InvalidCommandException{
		parseCommandString(commandStr);
	};
	private void parseCommandString(String commandStr) throws InvalidCommandException {
		String[] commandArray = commandStr.split(" ");
		switch(commandArray[0]) {
		  case "PLACE":
			this.commandType = CommandType.PLACE;
			break;
		  case "RIGHT":
			this.commandType = CommandType.RIGHT;
			break;
		  case "LEFT":
			this.commandType = CommandType.LEFT;
			break;
		  case "MOVE":
			this.commandType= CommandType.MOVE;
			break;
		  case "REPORT":
			this.commandType = CommandType.REPORT;
			break;
		  default:
			 throw new InvalidCommandException();
		}
		if(commandArray.length>1) {
			this.commandArgs = commandArray[1].split(",");
		}

	}

}
