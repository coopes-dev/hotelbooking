import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {
	
	private String string1;
	private String string2;

	@Before
	public void setUp() throws Exception {
		string2="ABC";
		System.out.println("Set up");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Tear down");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testA() {
		assertEquals("inf ",1.0/0,1.0/0,0.0);
		assertEquals("Square root test",Math.sqrt(-10),Double.NaN,1000.0);
		assertEquals("Divide test1",1/5,0.2,0.01);
		assertEquals("Divide test2",1.0/5,0.2,0.0);
		
		
		
		
		
		System.out.println("Running test A");
	//	assertTrue("Test string1 ",string1.length()==3);
	}
	
	@Test
	public void testB() {
		System.out.println("Test B");
		assertTrue("Test string2 ",string2.length()==3);
	}
	

}
