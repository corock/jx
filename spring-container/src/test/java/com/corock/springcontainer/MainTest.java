package com.corock.springcontainer;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.corock.springcontainer.user.User;

public class MainTest {

	@Test
	public void testMethod1() {
		int x = 10;
		assert( 2 + x > 1 );
	}

	@Test
	public void testMethod2() {
		fail();
	}
	
	@Test
	public void testMethod3() {
		// User user = dao.getUser( "corock@gmail.com", "1234" );
		User user = null;
		assert( user != null );
	}
	
	@Test
	public void testMethod4() {
		fail();
	}
	
}
