package labtest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Fred {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals("inf ",1.0/0,1.0/0,0.0);
		assertEquals("Square root test",Math.sqrt(-10),Double.NaN,1000.0); 
		//assertEquals("Divide test1",1/5,0.2,0.01);
		assertEquals("Divide test2",1.0/5,0.2,0.0001);  
	}

}
