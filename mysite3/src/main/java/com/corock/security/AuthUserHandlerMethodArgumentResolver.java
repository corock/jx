package com.corock.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.corock.mysite.vo.UserVO;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		if ( supportsParameter(parameter) == false ) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		// @AuthUser exists and type is UserVO
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		if ( session == null ) {
			return null;
		}
		
		return session.getAttribute( "authUser" );
	}

	@Override
	public boolean supportsParameter( MethodParameter parameter ) {
		AuthUser authUser = parameter.getParameterAnnotation( AuthUser.class );
		
		// If @AuthUser does not exist
		if ( authUser == null ) {
			return false;
		}
		
		// If parameter type is whether UserVO or not
		if ( parameter.getParameterType().equals(UserVO.class) == false ) {
			return false;
		}
		
		return true;
	}

}
