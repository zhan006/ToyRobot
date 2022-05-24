## Toy Robot

The application is a simulation of a toy robot moving on a square table top, of dimensions 5 units x 5 units. There are no
other obstructions on the table surface. The robot is free to roam around the surface of the table, but must be prevented
from falling to destruction. Any movement that would result in the robot falling from the table must be prevented,
however further valid movement commands must still be allowed.

# Set up
## Environment

This application is developed under Java8 with unit test setting up with JUnit4

To run this application, please make sure appropiate java runtime enviroment is installed on your device and you can run this application under any IDE such as Eclipes, IntelliJ or VSCode with Console.java as the entry point of the program.

Alternatively, if you want to run the application in a terminal, make sure the appropiate java sdk and **maven** is installed and environment variable is set up on your device. To test if they are successfully set up, open a termial such as commandline in windows, and type

`java -v`

`javac`

A sample out put should be

`java version "1.8.0_333"
Java(TM) SE Runtime Environment (build 1.8.0_333-b02)
Java HotSpot(TM) 64-Bit Server VM (build 25.333-b02, mixed mode)`

and    
`javac 1.8.0_202
`

## RUN the application

It is highly recommended to run the project in an IDE such as Eclipse or VSCode with Extension Pack for Java as it can arrange all the necessary dependencies set-up
### Running in IDE
To run the application in IDE, set the main class to be **Console.java** in the run configurations. Then if you click run button in the IDE, you should see 
`Ready to serve commands!` in a pop-up console indicating the application is successfully launched and you can input any available command as listed in the following section.

### Running in terminal

To run the application in the termial, you should

1. Dive into the project root folder `ToyRobot/`
2. Compile the java file by typing `mvn package` this will compile the java files and put the compiled binary to the target folder under the project

3. Go to target/classes folder and run the java class 
```
cd target/classes
java classes.Console
```
  The command will drive you to the bin folder and run the entry point under classes folder

4. You should see the `Ready to serve commands!` pop in the terminal!

## Test

It is highly recommended to run the tests in an IDE such as Eclipse or VSCode with Extension Pack for Java as it can arrange all the necessary dependencies set-up

### To run test in IDE

Set the main class to be **TestRunner.java** in the run configuration and click run button to run the test suite

## Available commands

PLACE X,Y,F

MOVE

LEFT

RIGHT

REPORT

PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST. The origin (0,0)
can be considered to be the SOUTH WEST most corner. It is required that the first command to the robot is a PLACE
command, after that, any sequence of commands may be issued, in any order, including another PLACE command. The
application should discard all commands in the sequence until a valid PLACE command has been executed. MOVE will
move the toy robot one unit forward in the direction it is currently facing.
LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the
robot. REPORT will announce the X,Y and F of the robot. This can be in any form, but standard output is sufficient.
A robot that is not on the table can choose to ignore the MOVE, LEFT, RIGHT and REPORT commands.

## Sample input
```
PLACE 1,1,NORTH
MOVE
MOVE
REPORT
RIGHT
MOVE
REPORT
```

The output would be

```
NORTH, 1, 3
EAST, 2, 3
```