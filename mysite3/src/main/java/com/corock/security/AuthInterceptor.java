package com.corock.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.corock.mysite.vo.UserVO;
import com.corock.security.Auth.Role;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/**
		 * 1. Handler 종류 확인
		 * 	(Check types of handler)
		 */
		if ( handler instanceof HandlerMethod == false ) {
			return true;
		}
		
		/** 2. Casting */
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		/**
		 * 3-1. Method에 @Auth 받아오기
		 * 	(Get @Auth from method)
		 */
		Auth auth = handlerMethod.getMethodAnnotation( Auth.class );
		
		/**
		 * 3-2. Method에 @Auth가 안 붙어 있으면 class(type)의 @Auth 받아오기
		 * 	If @Auth doesn't exist from method, get @Auth of class type
		 */
		if ( auth == null ) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation( Auth.class );
		}
		
		/**
		 * 4. Method와 class에 @Auth가 안 붙어 있으면
		 * 	(If @Auth doesn't exist from methods and classes)
		 */
		if ( auth == null ) {
			return true;
		}
		
		/**
		 * 5-1. @Auth가 붙어 있기 때문에 로그인 여부(인증여부) 확인
		 * 	Because of setting @Auth, check whether sign in(authentication) succeed or not
		 */
		HttpSession session = request.getSession();
		UserVO authUser = ( session == null ) ? null : (UserVO) session.getAttribute( "authUser" );
		
		if ( authUser == null ) {
			response.sendRedirect( request.getContextPath() + "/user/login" );
			return false;
		}
		
		/**
		 * 5-2. Role 가져오기(권한, Authentication)
		 * 	Compare role
		 */
		Role role = auth.value();
		
		// 8. User Role 접근이면 인증만 되어 있으면 허용
		if (role == Role.USER) {
			return true;
		}
		
		// 9. ADMIN Role 접근
		// ADMIN 권한이 없는 사용자면 메인으로 ㄱㄱ
		if ( "ADMIN".equals(authUser.getRole()) == false ) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		// 6. Access permit
		return true;
	}

}
