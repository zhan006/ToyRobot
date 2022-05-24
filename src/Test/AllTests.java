package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCommand.class, TestConsole.class, TestRobot.class,TestTable.class })
public class AllTests {

}
