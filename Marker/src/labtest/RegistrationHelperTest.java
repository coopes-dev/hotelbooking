package labtest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegistrationHelperTest {

	// Special chars are      ^(@
	// min password len is 11
	// min username len is 8
	// max username len is 10

	
	private String goodUserName;
	private String badUserNameTooLong;
	private String badUserName;
	
	private RegistrationHelper helper;
	private String goodPassword;
	private String shortUserName1;
	private String shortUserName2;
	private String longUserName1;
	private String longUserName2;
	private String alphaUserNameCheck;
	private String badPasswordNoLower;
	private String badPasswordNoUpper;
	private String badPasswordNoDigit;
	private String passwordLowerAtStart;
	private String goodPasswordLowerAtStart;
	private String goodPasswordLowerAtEnd;
	private String goodPasswordUpperAtStart;
	private String goodPasswordUpperAtEnd;
	private String goodPasswordDigitAtStart;
	private String goodPasswordDigitAtEnd;
	private String goodPasswordSpecialAtStart;
	private String goodPasswordSpecialAtEnd;
	private String goodPasswordSpecialChar1;
	private String goodPasswordSpecialChar2;
	private String goodPasswordSpecialChar3;
	private String badPasswordTooShort;
	private String goodPasswordOneOver;
	private String longUserName3;

	@Before
	public void setUp() throws Exception {
		goodUserName="abcdefgh";
		goodPassword="abcDEF^1234";
		shortUserName1="abcdefg";
		shortUserName2="abcdefggh";
		// These check the boundaries for the upper username length
		longUserName1="abcdefghi";
		longUserName2="abcdefghij";
		longUserName3="abcdefghijk";
		alphaUserNameCheck="1abcdefg";  // fails with alpha as first char
		badPasswordNoLower="ABCDEFG1234^"; // password fails to contain lower case char
		badPasswordNoUpper="abcdefg1234^"; // password fails to contain upper case char
		badPasswordNoDigit="abcdefgABCD^"; // password fails to contain digit char
		goodPasswordLowerAtStart="aABCDE1^23456"; // lower case at start of password boundary
		goodPasswordLowerAtEnd="ABCDE1^3456a"; // lower case at end of password boundary
		goodPasswordUpperAtStart="Abcde123456^a"; // upper case at start of password boundary
		goodPasswordUpperAtEnd="abcde123456^A"; // upper case at end of password boundary
		goodPasswordDigitAtStart="1Abcdefghij^a"; // digit at start of password boundary
		goodPasswordDigitAtEnd="Abcdefghij^a1"; // digit case at end of password boundary
		goodPasswordSpecialAtStart="^1Abcdefghija"; // digit at start of password boundary
		goodPasswordSpecialAtEnd="Abcdefghija1^"; // digit case at end of password boundary
		goodPasswordSpecialChar1="Abcde^fghija1";
		goodPasswordSpecialChar2="Abcde(fghija1";
		goodPasswordSpecialChar3="Abcde@fghija1";
		badPasswordTooShort="abcDEF^123"; // 10 long too short password
		goodPasswordOneOver="abcDEF^12345"; // 12 long 1 over min length, boundary check	
		helper=new RegistrationHelper();
	}

	@After
	public void tearDown() throws Exception {
		helper=null;
	}

	@Test
	public void testCheckUsernamePassword() {
		// First let's test the good spec
		assertTrue("Good D	Rpassword test",helper.checkUsernamePassword(goodUserName,goodPassword));
		// Now let's check the null checkiong
		assertFalse("Null test",helper.checkUsernamePassword(null,goodPassword));
		assertFalse("Null test",helper.checkUsernamePassword(goodUserName,null));
		assertFalse("Null test",helper.checkUsernamePassword(null,null));
		// Now let's check the username credential issues
		// First deal with length issues
		// check lower boundary for min length check
		assertFalse("Username too short check",helper.checkUsernamePassword(shortUserName1,goodPassword));
		// check upper boundary for min length check
		assertTrue("Username too short check",helper.checkUsernamePassword(shortUserName2,goodPassword));
		// check lower boundary for max length check
		assertTrue("Username too long check",helper.checkUsernamePassword(longUserName1,goodPassword));
		// check upper boundary for max length check
		assertTrue("Username too long check",helper.checkUsernamePassword(longUserName2,goodPassword));
		assertFalse("Username too long check",helper.checkUsernamePassword(longUserName3,goodPassword));
		// Now we check out the user name first char having to be alpha check
		assertFalse("Check for alpha check",helper.checkUsernamePassword(alphaUserNameCheck,goodPassword));
		
		// Now check the password credentiaL issues
		// First see if lowercase check working
		assertFalse("Check for lower check",helper.checkUsernamePassword(goodUserName,badPasswordNoLower));
		// Now see if uppercase check working
		assertFalse("Check for upper check",helper.checkUsernamePassword(goodUserName,badPasswordNoUpper));
		// Now see if digit check working
		assertFalse("Check for digit check",helper.checkUsernamePassword(goodUserName,badPasswordNoDigit));
		// Now check for lower at begin and end boundaries
		assertTrue("Check for lower check at begin",helper.checkUsernamePassword(goodUserName,goodPasswordLowerAtStart));
		assertTrue("Check for lower check at end",helper.checkUsernamePassword(goodUserName,goodPasswordLowerAtEnd));
		// Now check for upper at begin and end boundaries
		assertTrue("Check for upper check at begin",helper.checkUsernamePassword(goodUserName,goodPasswordUpperAtStart));
		assertTrue("Check for upper check at end",helper.checkUsernamePassword(goodUserName,goodPasswordUpperAtEnd));
		// Now check for digit at begin and end boundaries
		assertTrue("Check for digit check at begin",helper.checkUsernamePassword(goodUserName,goodPasswordDigitAtStart));
		assertTrue("Check for digit check at end",helper.checkUsernamePassword(goodUserName,goodPasswordDigitAtEnd));
		// Now check for special at begin and end
		assertTrue("Check for special check at begin",helper.checkUsernamePassword(goodUserName,goodPasswordSpecialAtStart));
		assertTrue("Check for special check at end",helper.checkUsernamePassword(goodUserName,goodPasswordSpecialAtEnd));
		// Now check for special check generally for each char
		assertTrue("Check for special check1",helper.checkUsernamePassword(goodUserName,goodPasswordSpecialChar1));
		assertTrue("Check for special check2",helper.checkUsernamePassword(goodUserName,goodPasswordSpecialChar2));
		assertTrue("Check for special check3",helper.checkUsernamePassword(goodUserName,goodPasswordSpecialChar3));
		// Now check for various password lengths
		assertFalse("Check for short password low",helper.checkUsernamePassword(goodUserName,badPasswordTooShort));
		assertTrue("Check for short password low",helper.checkUsernamePassword(goodUserName,goodPasswordOneOver));
		
		
	}

}
