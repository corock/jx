package com.corock.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	@Before( "execution( ProductVO com.corock.aoptest.ProductService.find(..) )" )
	public void beforeAdvice() {
		System.out.println( "call [before advice]" );
	}
	
	@After( "execution( * *..*.ProductService.*(..) )" )
	public void afterAdvice() {
		System.out.println( "call [after advice]" );
	}
	
	@AfterReturning( "execution( * *..*.ProductService.*(..) )" )
	public void afterReturningAdvice() {
		System.out.println( "call [afterReturning advice]" );
	}

	@AfterThrowing( value = "execution( * *..*.ProductService.*(..) )", throwing = "ex" )
	public void afterThrowingAdvice( Throwable ex ) {
		System.out.println( "call [afterThrowing advice]" + ex);
	}

	@Around( "execution( * *..*.ProductService.*(..) )" )
	public Object aroundAdvice( ProceedingJoinPoint pjp ) throws Throwable {
		// before
		System.out.println( "call [around advice : before]" );
		
		// Pointcut되는 메서드 호출
	/*
		Object[] parameters = { "camera" };
		Object result = pjp.proceed();
	*/
		Object result = pjp.proceed();
		
		// after
		System.out.println( "call [around advice : after]" );
		
		return result;
	}
	
}
