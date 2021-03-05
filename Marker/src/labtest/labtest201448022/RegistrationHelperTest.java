package labtest.labtest201448022;


import labtest.RegistrationHelper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegistrationHelperTest {
	/**
	 * Test fixture
	 */
	private RegistrationHelper helper = null;

	


	@Before
	public void setUp() throws Exception {
		helper = new RegistrationHelper();
	}


	@After
	public void tearDown() throws Exception {
		helper = new RegistrationHelper();
	}

	@Test
	public void test() {
		String goodPassword1="abcABCdef1&";
		String goodUsername1= "ABCabc123456789012)!";;
		String nullUsername=null;
		String nullPassword=null;
		 String error_rule_1 = "Either the username or password are null";
		 String error_rule_2 = "The username must be at least 9 characters long and must start with an alphabetic character (A-Z, a-z)";
		 String error_rule_3 = "The username must be no more than 14 characters long";
		 String error_rule_4 = "The password must be at least 10 characters long";
		 String error_rule_5 = "The password must contain at least one lower case letter";
		 String error_rule_6 = "The password must contain at least one upper case letter";
		 String error_rule_7 = "The password must contain at least one a digit";
		 String error_rule_8 = "The password must have a special character one of the following: )&\"";
//rule1
		 assertFalse(error_rule_1, helper.checkUsernamePassword(nullUsername, goodPassword1));
			assertFalse(error_rule_1, helper.checkUsernamePassword(goodUsername1, nullPassword));
			assertFalse(error_rule_1, helper.checkUsernamePassword(nullUsername, nullPassword));
			
//rule2	
			assertFalse(error_rule_2, helper.checkUsernamePassword("a", goodPassword1));
			assertFalse(error_rule_2, helper.checkUsernamePassword("ab", goodPassword1));
			assertFalse(error_rule_2, helper.checkUsernamePassword("abc", goodPassword1));
			assertFalse(error_rule_2, helper.checkUsernamePassword("abcd", goodPassword1));
			assertFalse(error_rule_2, helper.checkUsernamePassword("abcde", goodPassword1));
			assertFalse(error_rule_2, helper.checkUsernamePassword("abcdef", goodPassword1));
			assertFalse(error_rule_2, helper.checkUsernamePassword("abcdefg", goodPassword1));
			assertFalse(error_rule_2, helper.checkUsernamePassword("abcdefgh", goodPassword1));
			assertFalse(error_rule_2, helper.checkUsernamePassword("1bcABCdef1&", goodPassword1));
			assertFalse(error_rule_2, helper.checkUsernamePassword("2bcABCdef1&", goodPassword1));
			assertFalse(error_rule_2, helper.checkUsernamePassword("3bcABCdef1&", goodPassword1));
			assertFalse(error_rule_2, helper.checkUsernamePassword("4bcABCdef1&", goodPassword1));
			assertFalse(error_rule_2, helper.checkUsernamePassword("5bcABCdef1&", goodPassword1));
//rule3
			assertFalse(error_rule_3, helper.checkUsernamePassword("abcABCdef1&1234", goodPassword1));
			assertFalse(error_rule_3, helper.checkUsernamePassword("abcABCdef1&12345", goodPassword1));
			assertFalse(error_rule_3, helper.checkUsernamePassword("abcABCdef1&12346", goodPassword1));
			assertFalse(error_rule_3, helper.checkUsernamePassword("abcABCdef1&12347", goodPassword1));
			assertFalse(error_rule_3, helper.checkUsernamePassword("abcABCdef1&12348", goodPassword1));	
			
//rule4
			assertFalse(error_rule_4, helper.checkUsernamePassword(goodUsername1, "ABCa12)!1"));
			assertFalse(error_rule_4, helper.checkUsernamePassword(goodUsername1, "ACa12)!a"));
			assertFalse(error_rule_4, helper.checkUsernamePassword(goodUsername1, "AB12b)!"));
			assertFalse(error_rule_4, helper.checkUsernamePassword(goodUsername1, "A12c)!"));
			assertFalse(error_rule_4, helper.checkUsernamePassword(goodUsername1, "A12)!"));
			assertFalse(error_rule_4, helper.checkUsernamePassword(goodUsername1, "Ac)!"));
			assertFalse(error_rule_4, helper.checkUsernamePassword(goodUsername1, "A1!"));		
		
//rule5
			assertFalse(error_rule_5, helper.checkUsernamePassword(goodUsername1, goodPassword1.toUpperCase()));
			
//rule6
			assertFalse(error_rule_6, helper.checkUsernamePassword(goodUsername1, goodPassword1.toLowerCase()));
			
//rule7
			assertFalse(error_rule_7, helper.checkUsernamePassword(goodUsername1, "ABCabcasdasdasdasdasd)!"));
			assertFalse(error_rule_7, helper.checkUsernamePassword(goodUsername1, "ABCab1asdasdasdasdasd)!"));
			assertFalse(error_rule_7, helper.checkUsernamePassword(goodUsername1, "ABCabc22dasdasdasdasd)!"));
			
//rule8
			assertFalse(error_rule_8, helper.checkUsernamePassword(goodUsername1, "ABCabc1234567890121!"));
			assertFalse(error_rule_8, helper.checkUsernamePassword(goodUsername1, "ABCabc1234567890121&!"));
			assertFalse(error_rule_8, helper.checkUsernamePassword(goodUsername1, "ABCabc1234567890121\"!"));
			
			
			assertFalse("checking correct credentials", helper.checkUsernamePassword(goodUsername1, goodPassword1));

		assertFalse("checking bad credentials", helper.checkUsernamePassword(null, null));
		
	}

}
