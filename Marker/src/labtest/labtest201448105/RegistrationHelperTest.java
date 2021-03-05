package labtest.labtest201448105;

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
	public void test() {
		
		/**
		 *  Valid cases for username:
		 *  
		 *  1. The first character is alphabetic character &  length of username = 9
		 *  2. The first character is alphabetic character &  length of username = 10
		 *  3. The first character is alphabetic character &  length of username = 11
		 */
		
		
		
		/**
		 *  Valid cases for password:
		 *  
		 *  4. Passward contains only one lower case letter, some upper case letter, some digit and three special character ^, & and )  & length of password = 14
		 *  5. Passward contains only one upper case letter, some upper case letter, some digit and three special character ^, & and )  & length of password = 14
		 *  6. Passward contains only one digit, some lower case letter, some upper case letter and three special character ^, & and )  & length of password = 14
		 *  7. Passward contains only one special character ^, some lower case letter, some upper case letter and three digit  & length of password = 14
		 *  8. Passward contains only one special character & , some lower case letter, some upper case letter and three digit  & length of password = 14
		 *  9. Passward contains only one special character ) , some lower case letter, some upper case letter and three digit  & length of password = 14
		 *  10. Passward contains only one lower case letter, some upper case letter, some digit and three special character ^, & and )  & length of password = 15
		 *  11. Passward contains only one upper case letter, some upper case letter, some digit and three special character ^, & and )  & length of password = 15
		 *  12. Passward contains only one digit, some lower case letter, one upper case letter and three special character ^, & and )  & length of password = 15
		 *  13. Passward contains only one special character ^, some lower case letter, some upper case letter and some digit  & length of password = 15
		 *  14. Passward contains only one special character & , some lower case letter, some upper case letter and some digit  & length of password = 15
		 *  15. Passward contains only one special character ) , some lower case letter, some upper case letter and some digit  & length of password = 15
		 */
		 
		
		
		/**
		 * valid equivalent partition:
		 */ 
		 
		assertTrue("checking correct credentials 1",helper.checkUsernamePassword("a123^1234","aABCDE^&)12345"));  //  satisfy 1 and 4	
		assertTrue("checking correct credentials 2",helper.checkUsernamePassword("a123^1234","Aabcde^&)12345"));  // satisfy 1 and 5		
		assertTrue("checking correct credentials 3",helper.checkUsernamePassword("a123^1234","1^&)ABCDEabcde"));  // satisfy 1 and 6		
		assertTrue("checking correct credentials 4",helper.checkUsernamePassword("a123^1234","^ABCDabcd12345"));  // satisfy 1 and 7		
		assertTrue("checking correct credentials 5",helper.checkUsernamePassword("a123^1234","&ABCDabcd12345"));  // satisfy 1 and 8		
		assertTrue("checking correct credentials 6",helper.checkUsernamePassword("a123^1234",")ABCDabcd12345"));  // satisfy 1 and 9
		
		assertTrue("checking correct credentials 7",helper.checkUsernamePassword("a123^1234","AaBCDE^&)123456"));  //  satisfy 1 and 10		
		assertTrue("checking correct credentials 8",helper.checkUsernamePassword("a123^1234","aAbcde^&)123456"));  // satisfy 1 and 11		
		assertTrue("checking correct credentials 9",helper.checkUsernamePassword("a123^1234","^1&)ABCDEabcdef"));  // satisfy 1 and 12	
		assertTrue("checking correct credentials 10",helper.checkUsernamePassword("a123^1234","A^BCDabcd123456"));  // satisfy 1 and 13		
		assertTrue("checking correct credentials 11",helper.checkUsernamePassword("a123^1234","A&BCDabcd123456"));  // satisfy 1 and 14		
		assertTrue("checking correct credentials 12",helper.checkUsernamePassword("a123^1234","A)BCDabcd123456"));  // satisfy 1 and 15
		
		
		
		assertTrue("checking correct credentials 13",helper.checkUsernamePassword("a123^12345","ABCDE^&)12345a"));  //  satisfy 2 and 4		
		assertTrue("checking correct credentials 14",helper.checkUsernamePassword("a123^12345","abcde^&)12345A"));  // satisfy 2 and 5		
		assertTrue("checking correct credentials 15",helper.checkUsernamePassword("a123^12345","ABCDEabcde^&)1"));  // satisfy 2 and 6		
		assertTrue("checking correct credentials 16",helper.checkUsernamePassword("a123^12345","ABCDabcd12345^"));  // satisfy 2 and 7		
		assertTrue("checking correct credentials 17",helper.checkUsernamePassword("a123^12345","ABCDabcd12345&"));  // satisfy 2 and 8		
		assertTrue("checking correct credentials 18",helper.checkUsernamePassword("a123^12345","ABCDabcd12345)"));  // satisfy 2 and 9
		
		assertTrue("checking correct credentials 19",helper.checkUsernamePassword("a123^12345","ABCDE^&)12345a6"));  //  satisfy 2 and 10		
		assertTrue("checking correct credentials 20",helper.checkUsernamePassword("a123^12345","abcde^&)12345A6"));  // satisfy 2 and 11		
		assertTrue("checking correct credentials 21",helper.checkUsernamePassword("a123^12345","ABCDEabcde^&)1f"));  // satisfy 2 and 12	
		assertTrue("checking correct credentials 22",helper.checkUsernamePassword("a123^12345","ABCDabcd12345^6"));  // satisfy 2 and 13		
		assertTrue("checking correct credentials 23",helper.checkUsernamePassword("a123^12345","ABCDabcd12345&6"));  // satisfy 2 and 14		
		assertTrue("checking correct credentials 24",helper.checkUsernamePassword("a123^12345","ABCDabcd12345)6"));  // satisfy 2 and 15
		
		
		
		assertTrue("checking correct credentials 25",helper.checkUsernamePassword("abcd^123456","aABCDE^&)12345"));  //  satisfy 3 and 4		
		assertTrue("checking correct credentials 26",helper.checkUsernamePassword("abcd^123456","Aabcde^&)12345"));  // satisfy 3 and 5		
		assertTrue("checking correct credentials 27",helper.checkUsernamePassword("abcd^123456","1^&)ABCDEabcde"));  // satisfy 3 and 6		
		assertTrue("checking correct credentials 28",helper.checkUsernamePassword("abcd^123456","^ABCDabcd12345"));  // satisfy 3 and 7		
		assertTrue("checking correct credentials 29",helper.checkUsernamePassword("abcd^123456","&ABCDabcd12345"));  // satisfy 3 and 8		
		assertTrue("checking correct credentials 30",helper.checkUsernamePassword("abcd^123456",")ABCDabcd12345"));  // satisfy 3 and 9
		
		assertTrue("checking correct credentials 31",helper.checkUsernamePassword("abcd^123456","AaBCDE^&)123456"));  //  satisfy 3 and 10		
		assertTrue("checking correct credentials 32",helper.checkUsernamePassword("abcd^123456","aAbcde^&)123456"));  // satisfy 3 and 11		
		assertTrue("checking correct credentials 33",helper.checkUsernamePassword("abcd^123456","^1&)ABCDEabcdef"));  // satisfy 3 and 12	
		assertTrue("checking correct credentials 34",helper.checkUsernamePassword("abcd^123456","A^BCDabcd123456"));  // satisfy 3 and 13		
		assertTrue("checking correct credentials 35",helper.checkUsernamePassword("abcd^123456","A&BCDabcd123456"));  // satisfy 3 and 14		
		assertTrue("checking correct credentials 36",helper.checkUsernamePassword("abcd^123456","A)BCDabcd123456"));  // satisfy 3 and 15
		
		
		
		/**
		 *  Invalid cases for username :
		 *  
		 *  16. The first character is alphabetic character &  length of username < 9 choosing 8
		 *  17. The first character is alphabetic  character &  length of username > 11 choosing 12
		 *  18. The first character is not alphabetic character &  length of username = 9
		 *  19. The first character is not alphabetic character &  length of username = 10
		 *  20. The first character is not alphabetic  character&  length of username = 11
		 */  		
		
		
		
		/**
		 *  Invalid cases for password :
		 *  
		 *  21. Passward not contains lower case letter but contains some upper case letter, some digit and three special character ^, & and )  & length of password = 14
		 *  22. Passward not contains lower case letter but contains some upper case letter, some digit and three special character ^, & and )  & length of password = 15
		 *  23. Passward not contains upper case letter but contains some lower case letter, some digit and three special character ^, & and )  & length of password = 14
		 *  24. Passward not contains upper case letter but contains some lower case letter, some digit and three special character ^, & and )  & length of password = 15
		 *  25. Passward not contains digit but contains some upper case letter, some lower case letter and three special character ^, & and )  & length of password = 14
		 *  26. Passward not contains digit but contains some upper case letter, some lower case letter and three special character ^, & and )  & length of password = 15 
		 *  27. Passward not contains special character ^, & and ) but contains some upper case letter, some lower case letter & length of password = 14 
		 *  28. Passward not contains special character ^, & and ) but contains some upper case letter, some lower case letter & length of password = 15 
		 *  29. Passward contains some lower case letter, some upper case letter, some digit and three special character ^, & and )  & length of password  < 14 choosing 13
		 */
		 

		
		/**
		 * invalid equivalent partition:
		 */ 
		
		assertFalse("checking bad credentials 37",helper.checkUsernamePassword("abc^1234","ABCabc^&)12345")); //  satisfy 16
		assertFalse("checking bad credentials 38",helper.checkUsernamePassword("abcdef^12345","ABCabc^&)12345")); //  satisfy 17
		assertFalse("checking bad credentials 39",helper.checkUsernamePassword("1a2b^3c4d","ABCabc^&)12345")); //  satisfy 18	
		assertFalse("checking bad credentials 40",helper.checkUsernamePassword("1a2b^3c4d5","ABCabc^&)12345")); //  satisfy 19	
		assertFalse("checking bad credentials 41",helper.checkUsernamePassword("1a2b^3c4d5f","ABCabc^&)12345")); //  satisfy 20
		
		
		
		assertFalse("checking bad credentials 42",helper.checkUsernamePassword("a1b2^c3d4","ABCDEF^&)12345")); //  satisfy 21	
		assertFalse("checking bad credentials 43",helper.checkUsernamePassword("a1b2^c3d4","ABCDEF^&)123456")); //  satisfy 22	
		assertFalse("checking bad credentials 44",helper.checkUsernamePassword("a1b2^c3d4","abcdef^&)12345")); //  satisfy 23	
		assertFalse("checking bad credentials 45",helper.checkUsernamePassword("a1b2^c3d4","abcdef^&)123456")); //  satisfy 24	
		assertFalse("checking bad credentials 46",helper.checkUsernamePassword("a1b2^c3d4","abcdef^&)ABCDE")); //  satisfy 25	
		assertFalse("checking bad credentials 47",helper.checkUsernamePassword("a1b2^c3d4","abcdef^&)ABCDEF")); //  satisfy 26	
		assertFalse("checking bad credentials 48",helper.checkUsernamePassword("a1b2^c3d4","abcdefABC12345")); //  satisfy 27
		assertFalse("checking bad credentials 49",helper.checkUsernamePassword("a1b2^c3d4","abcdefABC123456")); //  satisfy 28
		assertFalse("checking bad credentials 50",helper.checkUsernamePassword("a1b2^c3d4","ABCabc^&)1234")); //  satisfy 29
		
		
		
		assertFalse("checking bad credentials 51",helper.checkUsernamePassword(null,"ABCabc^&)12345"));  
		assertFalse("checking bad credentials 52",helper.checkUsernamePassword("abcd^1234",null)); 
		assertFalse("checking bad credentials 53",helper.checkUsernamePassword(null,null)); 
		
		
	}

}
