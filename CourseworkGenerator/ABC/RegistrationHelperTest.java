package labtest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



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
	public void test() {
        assertTrue("checking correct credentials",helper.checkUsernamePassword("abcABCdef1$","ABCabc123456789012%"));

		assertFalse("checking bad credentials",helper.checkUsernamePassword(null,null));		
	}

}
