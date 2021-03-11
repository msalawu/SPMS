package com.revature.exceptions;

@SuppressWarnings("serial")
public class NotEnoughPointsException extends Exception {

	public NotEnoughPointsException() {
		super("You do not have enough points to submit this particular pitch "
				+ ", and as a result this story pitch will have an on-hold status "
				+ "until there is enough points available to submit the pitch.");
	}
}
