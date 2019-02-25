package com.corock.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface Auth {
	
	public enum Role { ADMIN, USER }
	Role value() default Role.USER;

	/**
	 * test
	 * 
	 * String value() default "USER";
	 * int method() default 1;
	 */
}
