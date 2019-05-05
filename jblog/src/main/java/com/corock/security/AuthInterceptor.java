package com.corock.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.corock.jblog.vo.UserVO;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if ( !(handler instanceof HandlerMethod) ) {
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		Auth auth = handlerMethod.getMethodAnnotation( Auth.class );
		
		if ( auth == null ) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation( Auth.class );
		}
		
		if ( auth == null ) {
			return true;
		}
		
		HttpSession session = request.getSession();
		UserVO authUser = ( session == null ) ? null : (UserVO) session.getAttribute( "authUser" );
		
		if ( authUser == null ) {
			response.sendRedirect( request.getContextPath() + "/user/login" );
			return false;
		}

		return true;
	}

	
	
}
