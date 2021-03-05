package labtest.labtest201449171;

import labtest.RegistrationHelper;

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

	/**
	 *Test fixtures for testing usernames
	 */
	//The length should be between 9 and 15 inclusive
	//The usernames must begin with an alphabet
	// so the valid equivalence set should be:
	// a username: legnth 9, an alphabetic head
	// a username: length 15, an alphabetic head
    private String userName_Length9_RightHead;
	private String userName_Length15_RightHead;
	// the invalid set should be:
	// a uername: length 8, an alphabetic head
	// a username: length 16, an alphabetic head
	// a username: length 9, a non-alphabetic head
	// a username: length 15, a non-alphabetic head
	private String userName_Length8_RightHead;
	private String userName_Length16_RightHead;
	private String userName_Length9_WrongHead;
	private String userName_Length15_WrongHead;
	

    /**
	 *Test fixtures for testing passwords
	 */
	//The length should not be less than 12
	//There should exists at least one lower case letter 
	//There should exits at least one upper case letter
	//There should exists at least one digit
	//It must have one special characters @)^ 
	// So, the valid set should be:
	// a password: every constraints are satisfied
	// a password: length 12, exactly one lowercase letter, others all right
	// a password: length 12, exactly one uppercase letter, others all right
	// a password: length 12, exactly one digit, others all right
	// a password: length 12, exactly one special symbol, others all right
	private String password_Length12_AllRight;
	private String password_Length12_1LowerCase;
	private String password_Length12_1UpperCase;
	private String password_Length12_1Digit;
	private String password_Length12_SymbolType1;
	private String password_Length12_SymbolType2;
	private String password_Length12_SymbolType3;

	//The invalid set should be:
	//a password: length 11, others are all right
	//a password: length 12, zero lowercase letter, others all right
	//a password: length 12, zero uppercase letter, others all right
	//a password: length 12, zero digit, others all right
	//a password: length 12, zero special symbol, others all right
    private String password_Length11_AllRight;
	private String password_Length12_0LowerCase;
	private String password_Length12_0UpperCase;
	private String password_Length12_0Digit;
	private String password_Length12_0Symbol;
	

   
    /**
	 *Test fixtures for testing index in a "for" loop
	 */
    private String password_LowerFirst; // this is to test the first index of the password
    private String password_LowerLast;  // this is to test the last index of the password
    private String password_UpperFirst;
    private String password_UpperLast;
    private String password_DigitFirst;
    private String password_DigitLast;
    private String password_SpecialFirst1;
    private String password_SpecialLast1;
    private String password_SpecialFirst2;
	private String password_SpecialLast2;
	private String password_SpecialFirst3;
    private String password_SpecialLast3;
    
    
	@Before
	public void setUp() throws Exception {
		helper=new RegistrationHelper();
		
		//initialization of fixtures for user name testing
		userName_Length9_RightHead = "aabcABC)1";
		userName_Length15_RightHead = "aabcABC)124567";

		userName_Length8_RightHead = "aabcABC)";
		userName_Length16_RightHead = "aabcABC)12345678";
		userName_Length9_WrongHead = "0Ab)12345";
		userName_Length15_WrongHead = "0Ab)12345678910";
	

		//initialization of fixtures for password testing
		password_Length12_AllRight = "ABCabc@)^123";

		password_Length11_AllRight = "ABCabc@)^12";
		password_Length12_0LowerCase = "ABCABC@)^123";
		password_Length12_1LowerCase = "ABCaBC@)^123";
		
		password_Length12_0UpperCase = "abcabc@)^123";
		password_Length12_1UpperCase = "abcAbc@)^123";
		
		password_Length12_0Digit = "ABC@)^abcabc";
		password_Length12_1Digit = "ABC@)^1abcab";
		
		password_Length12_0Symbol = "ABCabc123456";
		password_Length12_SymbolType1 = "ABCabc@12345";
		password_Length12_SymbolType2 = "ABCabc)12345";
		password_Length12_SymbolType3 = "ABCabc^12345";

		
		//initialization of fixtures for index testing
		password_LowerFirst = "aBCABC@)^1234";
		password_LowerLast = "123456@)^ABCa";
		password_UpperFirst = "Abcabc@)^1234";
		password_UpperLast = "1234@)^abcabC";
		password_DigitFirst = "1@)^ABCabcde";
		password_DigitLast = "ABCabcdefg@)^1";
		password_SpecialFirst1 = "@ABCabc12345";
		password_SpecialLast1 = "ABCabc12345@";
		password_SpecialFirst2 = ")ABCabc12345";
		password_SpecialLast2 = "ABCabc12345)";
		password_SpecialFirst3 = "^ABCabc12345";
		password_SpecialLast3 = "ABCabc12345^";
	}

	@After
	public void tearDown() throws Exception {
		helper=new RegistrationHelper();
	}

	@Test
	public void test() {
		
        /**
		 * Tests on null inputs
		 */
		assertFalse("Fail: Null value cheking is invalid", helper.checkUsernamePassword(null,null));
		assertFalse("Fail: Null value cheking is invalid",helper.checkUsernamePassword(userName_Length9_RightHead,null));
		assertFalse("Fail: Null value cheking is invalid",helper.checkUsernamePassword(null,password_Length12_AllRight));
		
		/**
		 * Tests on usernames 
		 */
		// Valid set of usernames combined with a valid password
		assertTrue("Fail: Correct credentials didn't pass the test",helper.checkUsernamePassword(userName_Length9_RightHead, password_Length12_AllRight));
		assertTrue("Fail: Correct credentials didn't pass the test",helper.checkUsernamePassword(userName_Length15_RightHead, password_Length12_AllRight));
		// Invalid set of usernames combined with a valid password
        assertFalse("Fail: The lower bound on username length is invalid",helper.checkUsernamePassword(userName_Length8_RightHead, password_Length12_AllRight));
		assertFalse("Fail: The upper bound on username length is invalid",helper.checkUsernamePassword(userName_Length16_RightHead, password_Length12_AllRight));
		assertFalse("Fail: The alphabetic checking on username is invalid",helper.checkUsernamePassword(userName_Length9_WrongHead, password_Length12_AllRight));
		assertFalse("Fail: The alphabetic checking on username is invalid",helper.checkUsernamePassword(userName_Length15_WrongHead, password_Length12_AllRight));
		

		/**
		 * Tests on passwords
		 */
		// Valid set of passwords combined with a valid username
		assertTrue("Fail: The number checking on lowercase letters is invalid",helper.checkUsernamePassword(userName_Length9_RightHead, password_Length12_1LowerCase));
		assertTrue("Fail: The number checking on uppercase letters is invalid",helper.checkUsernamePassword(userName_Length9_RightHead, password_Length12_1UpperCase));
		assertTrue("Fail: The number checking on digits is invalid",helper.checkUsernamePassword(userName_Length9_RightHead, password_Length12_1Digit));
		assertTrue("Fail: The checking on Symbol1 letters is invalid",helper.checkUsernamePassword(userName_Length9_RightHead, password_Length12_SymbolType1));
		assertTrue("Fail: The checking on Symbol2 letters is invalid",helper.checkUsernamePassword(userName_Length9_RightHead, password_Length12_SymbolType2));
		assertTrue("Fail: The checking on Symbol3 letters is invalid",helper.checkUsernamePassword(userName_Length9_RightHead, password_Length12_SymbolType3));
        // Invalid set of passwords combined with a valid username
		assertFalse("Fail: The lower bound on password length is invalid",helper.checkUsernamePassword(userName_Length9_RightHead, password_Length11_AllRight));
		assertFalse("Fail: The number checking on lowercase letters is invalid",helper.checkUsernamePassword(userName_Length9_RightHead, password_Length12_0LowerCase));
		assertFalse("Fail: The number checking on uppercase letters is invalid",helper.checkUsernamePassword(userName_Length9_RightHead, password_Length12_0UpperCase));
		assertFalse("Fail: The number checking on digits is invalid",helper.checkUsernamePassword(userName_Length9_RightHead, password_Length12_0Digit));
		assertFalse("Fail: The number checking on special symbols is invalid",helper.checkUsernamePassword(userName_Length9_RightHead, password_Length12_0Symbol));
        
		
		/**
		 * Tests on indeces
		 */
		assertTrue("Fail: the beginning index of checking lowercase letters in password is wrong", helper.checkUsernamePassword(userName_Length15_RightHead,password_LowerFirst));
		assertTrue("Fail: the ending index of checking lowercase letters in password is wrong", helper.checkUsernamePassword(userName_Length15_RightHead,password_LowerLast));
		assertTrue("Fail: the beginning index of checking upperrcase letters in password is wrong", helper.checkUsernamePassword(userName_Length15_RightHead,password_UpperFirst));
		assertTrue("Fail: the ending index of checking uppercase letters in password is wrong", helper.checkUsernamePassword(userName_Length15_RightHead,password_UpperLast));
		assertTrue("Fail: the beginning index of checking digit in password is wrong", helper.checkUsernamePassword(userName_Length15_RightHead,password_DigitFirst));
		assertTrue("Fail: the ending index of checking digit in password is wrong", helper.checkUsernamePassword(userName_Length15_RightHead,password_DigitLast));
		assertTrue("Fail: the beginning index of checking special symbols in password is wrong", helper.checkUsernamePassword(userName_Length15_RightHead,password_SpecialFirst1));
		assertTrue("Fail: the ending index of checking special symbols in password is wrong", helper.checkUsernamePassword(userName_Length15_RightHead,password_SpecialLast2));
		assertTrue("Fail: the beginning index of checking special symbols in password is wrong", helper.checkUsernamePassword(userName_Length15_RightHead,password_SpecialFirst2));
		assertTrue("Fail: the ending index of checking special symbols in password is wrong", helper.checkUsernamePassword(userName_Length15_RightHead,password_SpecialLast1));
		assertTrue("Fail: the beginning index of checking special symbols in password is wrong", helper.checkUsernamePassword(userName_Length15_RightHead,password_SpecialFirst3));
		assertTrue("Fail: the ending index of checking special symbols in password is wrong", helper.checkUsernamePassword(userName_Length15_RightHead,password_SpecialLast3));
	}

}
