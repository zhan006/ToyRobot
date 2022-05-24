package classes;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import Test.AllTests;

public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(AllTests.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("Successfully runed " + result.getRunCount() + " tests");
		} else {
			System.out.println("Failed " + result.getFailureCount());
		}
	}
}
