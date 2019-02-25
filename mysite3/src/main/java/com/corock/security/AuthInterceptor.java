package com.corock.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.corock.mysite.vo.UserVO;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. Check types of handler
		if ( handler instanceof HandlerMethod == false ) {
			return true;
		}
		
		// 2. Casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// 3. Get @Auth from method
		Auth auth = handlerMethod.getMethodAnnotation( Auth.class );
		
		// 4. If @Auth isn't found from method
		if ( auth == null ) {
			return true;
		}
		
		// Role role = auth.value();
		
		// 5. Because of setting @Auth, check whether sign in(authentication) succeed or not
		HttpSession session = request.getSession();
		UserVO authUser = null;
		if ( session != null ) {
			authUser = (UserVO) session.getAttribute( "authUser" );
		}
		
		if ( authUser == null ) {
			response.sendRedirect( request.getContextPath() + "/user/login" );
			return false;
		}
		
		// 6. Access permit
		return true;
	}

}
