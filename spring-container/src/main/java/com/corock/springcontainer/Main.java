package com.corock.springcontainer;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Main {

	public static void main(String[] args) {
		testBeanFactory();
	}
	
	public static void testBeanFactory() {
		// @ 설정인 경우, id가 자동으로 만들어진다. ex) User1 => user1
		BeanFactory bf1 = new XmlBeanFactory( new ClassPathResource("config/applicationContext2.xml") );
		User1 user = (User1) bf1.getBean( "user1" );
		System.out.println( user.getName() );

		// XML Bean 설정인 경우, id를 주지 않으면 에러!
		BeanFactory bf2 = new XmlBeanFactory( new ClassPathResource("config/applicationContext.xml") );
		// user = (User1) bf2.getBean( "user2" );

		// id 대신 타입으로 빈을 가져올 수 있다.
		user = (User1) bf2.getBean( User1.class );
		System.out.println( user.getName() );
	}
	
}
