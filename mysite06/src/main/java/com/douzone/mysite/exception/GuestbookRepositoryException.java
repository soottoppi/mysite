package com.douzone.mysite.exception;

public class GuestbookRepositoryException extends RuntimeException {

	/**
	 * 
	 */
	

	public GuestbookRepositoryException() {
		super("GuestbookRepositoryException Occurs");
	}

	public GuestbookRepositoryException(String message) {
		super(message);
	}
}
