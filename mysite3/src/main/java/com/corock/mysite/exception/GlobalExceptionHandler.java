package com.corock.mysite.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.corock.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Log LOG = LogFactory.getLog( GlobalExceptionHandler.class );
	
	@ExceptionHandler( Exception.class )
	public ModelAndView handlerException( HttpServletRequest request,
										  HttpServletResponse response,
										  Exception e ) throws Exception {
		// 1. 로깅 작업
		StringWriter errors = new StringWriter();
		e.printStackTrace( new PrintWriter( errors ) );
		LOG.error( errors.toString() );
		
		// 2. 시스템 오류 안내 화면 전환
//		ModelAndView mav = new ModelAndView();
//		mav.addObject( "errors", errors.toString() );
//		mav.setViewName( "error/exception" );
		
		String accept = request.getHeader( "accept" );
		if ( accept.matches(".*application/json.*") ) {
			// JSON으로 응답 요청
			response.setStatus( HttpServletResponse.SC_OK );
			
			OutputStream out = response.getOutputStream();
			out.write( new ObjectMapper().writeValueAsString( JSONResult.fail(errors.toString()) )
										 .getBytes("utf-8") );
			out.flush();
			out.close();
			
		} else {
			// HTML로 응답 요청
			request.setAttribute( "uri", request.getRequestURI() );
			request.setAttribute( "exception", errors.toString() );
			request.getRequestDispatcher( "/WEB-INF/views/error/exception.jsp" )
				   .forward( request, response );
		}
		
		return null;
	}
	
}
