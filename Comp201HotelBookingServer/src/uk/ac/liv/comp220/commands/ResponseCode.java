package uk.ac.liv.comp220.commands;

public enum ResponseCode {
	OK,
	BAD_USERNAME_PASSWORD,
	INVALID_COMMAND,
	USER_EXISTS,
	USERN_MISSING,
	PASSWORD_MISSING,
	PASSWORD_TOO_SHORT,
	PASSWORD_MISSING_UPPER_CASE,
	PASSWORD_MISSING_LOWER_CASE,
	PASSWORD_MISSING_DIGIT,
	PENDING,
	UNAUTHORISED,	// not allowed to execute this command based on role
	EXCEPTION,		// server exception
	DUPLICATE_GUEST,
	DUPLICATE_USER,
	INVALID_FIELDS,
	GUEST_ADDED_OK,
	GUEST_NOT_FOUND
}