package com.cab.booking.Exception;

public class CbException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8145701179081599518L;

	public CbException() {
		super();
	}
	
	public CbException(String message) {
		super(message);
	}

	public CbException(Throwable cause) {
		super(cause);
	}

}
