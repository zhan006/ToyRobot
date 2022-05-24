## Toy Robot

The application is a simulation of a toy robot moving on a square table top, of dimensions 5 units x 5 units. There are no
other obstructions on the table surface. The robot is free to roam around the surface of the table, but must be prevented
from falling to destruction. Any movement that would result in the robot falling from the table must be prevented,
however further valid movement commands must still be allowed.

# Set up
### Environment

This application is developed under Java8 with unit test setting up with JUnit4

To run this application, please make sure appropiate java runtime enviroment is installed on your device and you can run this application under any IDE such as Eclipes, IntelliJ or VSCode with Console.java as the entry point of the program.

Alternatively, if you want to run the application in a terminal, make sure the appropiate java sdk is installed and environment variable is set up on your device. To test if they are successfully set up, open a termial such as commandline in windows, and type

`
java
javac
`