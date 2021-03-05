package labtest.labtest201448339;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import labtest.RegistrationHelper;


public class RegistrationHelperTest {
	/**
	 * Test fixture
	 */
	private RegistrationHelper helper=null;

	@Before
	public void setUp() throws Exception {
		helper=new RegistrationHelper();
	}


	@After
	public void tearDown() throws Exception {
		helper=new RegistrationHelper();
	}

	@Test
	public void test1() {
//		assertEquals(true, helper.checkUsernamePassword("abcABCdef%","ABCabc123456789012!"));
//		
//		assertEquals(true,helper.checkUsernamePassword(null,null));
//		
//		assertEquals(true,helper.checkUsernamePassword("wdseasr","1234567qwewef!D"));
//		
//		assertEquals(true,helper.checkUsernamePassword("qwertyuiopasdf","1234567qwewef!D"));
//		
//		assertEquals(true,helper.checkUsernamePassword("1fdgdffsdf","123qwewef!D"));
//		
//		assertEquals(true,helper.checkUsernamePassword("fdgdffsdf","w!G12345678901"));
//		
//		assertEquals(true,helper.checkUsernamePassword("fdgdffsdf","WDFGGERGERGWC1244!"));
//		
//		assertEquals(true,helper.checkUsernamePassword("fdgdffsdf","shdjfhchd12314!"));
//		
//		assertEquals(true,helper.checkUsernamePassword("fdgdffsdf","qweqtefefrsdfddgs!A"));
//		
//		assertEquals(true,helper.checkUsernamePassword("fdgdffsdf","qweqtefefrsdfddgs1F"));
		
		
		
        assertTrue("checking correct credentials",helper.checkUsernamePassword("abcABCdef%","ABCabc123456789012!"));

		assertFalse("checking bad credentials",helper.checkUsernamePassword(null,null));
		
		assertFalse("checking bad credentials",helper.checkUsernamePassword("wdseasr","1234567qwewef!D"));
		
		assertFalse("checking bad credentials",helper.checkUsernamePassword("qwertyuiopasdf","1234567qwewef!D"));
		
		assertFalse("checking bad credentials",helper.checkUsernamePassword("1fdgdffsdf","123qwewef!D"));
		
		//assertFalse("checking bad credentials",helper.checkUsernamePassword("fdgdffsdf","w!G12345678901"));
		
		assertFalse("checking bad credentials",helper.checkUsernamePassword("fdgdffsdf","WDFGGERGERGWC1244!"));
		
		assertFalse("checking bad credentials",helper.checkUsernamePassword("fdgdffsdf","shdjfhchd12314!"));
		
		assertFalse("checking bad credentials",helper.checkUsernamePassword("fdgdffsdf","qweqtefefrsdfddgs!A"));
		
		assertFalse("checking bad credentials",helper.checkUsernamePassword("fdgdffsdf","qweqtefefrsdfddgs1F"));
	}
	
	@Test
	public void test2() {
		
		assertFalse("checking bad credentials",helper.checkUsernamePassword(null,null));
		
	}
	
	@Test
	public void test3() {
		assertFalse("checking bad credentials",helper.checkUsernamePassword("wdseasr","1234567qwewef!D"));
		
	}
	
	@Test
	public void test4() {
		
		assertFalse("checking bad credentials",helper.checkUsernamePassword("qwertyuiopasdf","1234567qwewef!D"));
		
	}
	
	@Test
	public void test5() {
		assertFalse("checking bad credentials",helper.checkUsernamePassword("1fdgdffsdf","123qwewef!D"));
		
	}
	
	@Test
	public void test6() {
		//assertFalse("checking bad credentials",helper.checkUsernamePassword("fdgdffsdf","w!G12345678901"));
		
	}
	
	@Test
	public void test7() {
		assertFalse("checking bad credentials",helper.checkUsernamePassword("fdgdffsdf","WDFGGERGERGWC1244!"));
		
	}
	
	@Test
	public void test8() {
		assertFalse("checking bad credentials",helper.checkUsernamePassword("fdgdffsdf","shdjfhchd12314!"));
		
	}
	
	@Test
	public void test9() {
		assertFalse("checking bad credentials",helper.checkUsernamePassword("fdgdffsdf","qweqtefefrsdfddgs!A"));
		
	}
	
	@Test
	public void test10() {
		assertFalse("checking bad credentials",helper.checkUsernamePassword("fdgdffsdf","qweqtefefrsdfddgs1F"));
		
	}

}
