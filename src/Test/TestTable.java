package Test;

import static org.junit.Assert.*;

import org.junit.Test;
import classes.Table;
public class TestTable {
	private Table table = new Table(3,3,0,0);
	@Test
	public final void testIsValidPlace() {
		assertTrue(table.isValidPlace(2, 2));
		assertFalse(table.isValidPlace(3, 3));
	}

}
