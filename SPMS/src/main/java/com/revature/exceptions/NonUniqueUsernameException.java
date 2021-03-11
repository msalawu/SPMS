package com.revature.exceptions;

@SuppressWarnings("serial")
public class NonUniqueUsernameException extends Exception {

	public NonUniqueUsernameException() {
		super ("That username is already taken!!");
	}
}
