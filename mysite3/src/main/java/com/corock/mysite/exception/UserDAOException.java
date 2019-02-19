package com.corock.mysite.exception;

public class UserDAOException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserDAOException() {
		super( "UserDAO Exception" );
	}
	
	public UserDAOException( String message ) {
		super( message );
	}
	
}
