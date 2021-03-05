package labtest;

import java.util.Vector;

/* Missing lowercase test on password */

public class RegistrationHelper {
private static int MINUSERNAMELEN=9;
private static int MAXUSERNAMELEN=14;
private static int MINPASSWORDLEN=12;
	
	
	
	private static Vector <Character> specialCharacters=new Vector<>();
	
	
	public RegistrationHelper() {
		
	}
	
	/*
	 * Defines the mutations for the source code being tested
	 */

	public static String mutations[] = new String[Mutation.END_MUTATIONS.ordinal()];
	
	
	private static Mutation mutationIndex;
	public static void setMutation(Mutation mutation) {
		mutationIndex=mutation;
	}
	
	public static String getMutationMessage() {
		return(mutations[mutationIndex.ordinal()]);
	}
	
	private void setUpMessages() {
		mutations[Mutation.OK.ordinal()]="Working";
		mutations[Mutation.NO_NULL.ordinal()]="Missing null test";
		mutations[Mutation.NO_LOWERCASE.ordinal()]="No lowercase test";
		mutations[Mutation.NO_UPPERCASE.ordinal()]="No upper case test";
		mutations[Mutation.MIN_PASSWORD_TOO_LONG.ordinal()]="Min password too long";
		mutations[Mutation.MIN_PASSWORD_TOO_SHORT.ordinal()]="Min password too short";
		mutations[Mutation.MISSING_DIGIT_TEST.ordinal()]="Missing digit test";
		mutations[Mutation.MIN_USER_NAME_TOO_SHORT.ordinal()]="Min username too short";
		mutations[Mutation.MIN_USER_NAME_TOO_LONG.ordinal()]="Min username too long";
		mutations[Mutation.MAX_USER_NAME_TOO_SHORT.ordinal()]="Max username too short";
		mutations[Mutation.NO_USERNAME_ALPHA_CHECK.ordinal()]="Missing first alpha check";
		mutations[Mutation.UPPER_CASE_NO_CHECK_FIRST.ordinal()]="No check upper case first character";
		mutations[Mutation.UPPER_CASE_NO_CHECK_LAST.ordinal()]="No check upper case last character";
		mutations[Mutation.LOWER_CASE_NO_CHECK_FIRST.ordinal()]="No check lower case first character";
		mutations[Mutation.LOWER_CASE_NO_CHECK_LAST.ordinal()]="No check lower case last character";
		mutations[Mutation.DIGIT_NO_CHECK_FIRST.ordinal()]="No check digit first character";
		mutations[Mutation.DIGIT_NO_CHECK_LAST.ordinal()]="No check digit case last character";
	}
	
	public RegistrationHelper(Specification spec) {
		setUpMessages();
		// set up specification for helper based on generated spec
		mutationIndex=Mutation.OK;		// reset for first test run no errors
		MAXUSERNAMELEN=spec.getMaxUsernameLength();
		MINPASSWORDLEN=spec.getMinPasswordLength();
		MINUSERNAMELEN=spec.getMinUsernameLength();
		System.out.println("Min password len is "+MINPASSWORDLEN);
		System.out.println("Max username len is "+MAXUSERNAMELEN);
		    String specChars=spec.getSpecialChars();
	    specialCharacters=new Vector<Character>();
	    for (int idx=0;idx<specChars.length();idx++) {
	    	specialCharacters.add(specChars.charAt(idx));
	    }
	    
	}
	
	public boolean checkUsernamePassword(String username, String password) {
		/**
		 * Check username is not null
		 */
		if (mutationIndex==Mutation.NO_NULL) {
			if ((username==null) || (password==null)) {
				return(true);	// checking to see if username or password null checked
			}
		}
		if ((username==null) || (password==null)) {
			return(false);	// checking to see if username or password null checked
		}
	
		if (username==null) return(false);
		/**
		 * 	Check password is not null
		 */
		if (password==null) return(false);
		/**
		 * Check username is long enough
		 */
		
		switch (mutationIndex) {
		case MIN_USER_NAME_TOO_SHORT :
				if (username.length()<MINUSERNAMELEN-1) {
					return(false);
				}
				break;
		case MIN_USER_NAME_TOO_LONG :
			if (username.length()<=MINUSERNAMELEN) {
				return(false);
			}
			break;
		default :
			if (username.length()<MINUSERNAMELEN) {
				return(false);
			}
			break;
		}
		/**
		 * Check username is not too long
		 */
		switch (mutationIndex) {
			case MAX_USER_NAME_TOO_SHORT :
				if (username.length()>=MAXUSERNAMELEN) {
					return(false);
				}		
				break;
			default :
				if (username.length()>MAXUSERNAMELEN) {
					return(false);
				}		
				break;
		}
		/**
		 * Check username starts with an alphabetic character
		 */
		if (mutationIndex!=Mutation.NO_USERNAME_ALPHA_CHECK) {
		if (!Character.isAlphabetic(username.charAt(0))) {
			return(false);
		}
		}
		/**
		 * Check password is long enough
		 */
		switch (mutationIndex) {
			case MIN_PASSWORD_TOO_LONG :
				if (password.length()<=MINPASSWORDLEN) {
					return(false);
				} break;
			case MIN_PASSWORD_TOO_SHORT :
				if (password.length()<MINPASSWORDLEN-1) {
					return(false);
				} break;
			default :
				if (password.length()<MINPASSWORDLEN) {
					return(false);
				}
		}
		/**
		 * Now look for a lower case char in the password, no lower case then check fails
		 */
		int first=0;
		int last=password.length();
		if (mutationIndex==Mutation.LOWER_CASE_NO_CHECK_FIRST) {
			first=1;
		}
		if (mutationIndex==Mutation.LOWER_CASE_NO_CHECK_LAST) {
			last=password.length()-1;
		}
		
		boolean isLower=false;
		for (int idx=first;idx<last;idx++) {
			if (Character.isLowerCase(password.charAt(idx))) {
				isLower=true;
			}
		}
		if (mutationIndex!=Mutation.NO_LOWERCASE) {  // mutation 2 doesn't look for lower case
			if (!isLower) return(false);
		}
		/**
		 * Now look for an upper case char in the password, no upper case then check fails
		 */

		first=0;
		last=password.length();
		if (mutationIndex==Mutation.UPPER_CASE_NO_CHECK_FIRST) {
			first=1;
		}
		if (mutationIndex==Mutation.UPPER_CASE_NO_CHECK_LAST) {
			last=password.length()-1;
		}

		boolean isUpper=false;
		for (int idx=first;idx<last;idx++) {
			if (Character.isUpperCase(password.charAt(idx))) {
				isUpper=true;
			}
		}
		
		if (mutationIndex!=Mutation.NO_UPPERCASE) {
			if (!isUpper) return(false);
		}
		/**
		 * Now look for an digit in the password, no digit then the test fails
		 */
		first=0;
		last=password.length();
		if (mutationIndex==Mutation.DIGIT_NO_CHECK_FIRST) {
			first=1;
		}
		if (mutationIndex==Mutation.DIGIT_NO_CHECK_LAST) {
			last=password.length()-1;
		}
		
		boolean isDigit=false;
		for (int idx=first;idx<last;idx++) {
			if (Character.isDigit(password.charAt(idx))) {
				isDigit=true;
			}
		}
		if (mutationIndex!=Mutation.MISSING_DIGIT_TEST) {
			if (!isDigit) {
				return(false);
			}
		} 
		/*
		 * Now look for a special char in the password, no special char then test fails
		 */
		boolean gotSpecial=false;
		for (int idx=0;idx<password.length();idx++) {
			Character character=password.charAt(idx);
			if (this.specialCharacters.contains(character)) {
				gotSpecial=true;
				break;
			}
		}	
		if (!gotSpecial) {
			return(false);
		}
		return(true);
	}

}
