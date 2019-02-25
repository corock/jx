package com.corock.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @see #preHandle
 * @see #postHandle
 * @see #afterCompletion
 * 
 * @author CoRock
 */
public class MyInterceptor01 implements HandlerInterceptor {

	/**
	 * handler 요청 전 boolean 반환 값에 따라 호출 여부를 결정
	 * 
	 * @param  Object는 handler 정보를 의미 (RequestMapping, DefaultServletHandler)
	 * @return false라면 controller로 요청을 하지 않음
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("MyInterceptor01:preHandle");
		return false;
	}

	/** Controller의 Handler 호출이 끝난 뒤에 처리됨(응답 후) */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("MyInterceptor01:postHandle");
	}

	/** View의 rendering 작업까지 끝난 뒤에 처리됨(응답 후) */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("MyInterceptor01:afterCompletion");
	}

}
