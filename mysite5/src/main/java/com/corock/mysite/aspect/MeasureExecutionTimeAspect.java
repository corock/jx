package com.corock.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {

	@Around( "execution( * *..repository.*.*(..) ) || execution( * *..service.*.*(..) ) || execution( * *..controller.*.*(..) )" )
	public Object aroundAdvice( ProceedingJoinPoint pjp ) throws Throwable {
		// Before
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// Method execution
		Object result = pjp.proceed();
		
		// After
		stopWatch.stop();
		Long totalTime = stopWatch.getTotalTimeMillis();
		
		
		// Reflection
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String taskName = className + "." + methodName;

		// Working with logging
		System.out.println("[ExecutionTime][" + taskName + "] " + totalTime + " mils");
		
		return result;
	}
	
}
