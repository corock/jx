package com.corock.springcontainer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corock.springcontainer.soundsystem.CDPlayer;
import com.corock.springcontainer.soundsystem.CompactDisc;
import com.corock.springcontainer.user.User;
import com.corock.springcontainer.videosystem.DVDPlayer;
import com.corock.springcontainer.videosystem.DigitalVideoDisc;

import config.mixing.videosystem.DVDPlayerConfig;
import config.mixing.videosystem.VideoSystemConfig;
import config.user.AppConfig01;

public class JavaConfigTest {

	public static void main(String[] args) {
//		testJavaConfig01();
//		testJavaConfig02();
//		testJavaConfig03();
//		testJavaConfig04();
		testJavaConfig05();
		testJavaConfig06();
	}
	
	/**
	 * Java Config 01
	 * 직접 설정 자바 클래스를 전달하는 경우
	 * 설정 자바 클래스에 @Configuration 필요 없음
	 */
	public static void testJavaConfig01() {
		ApplicationContext appCtx = new AnnotationConfigApplicationContext( AppConfig01.class );
		
		User user = appCtx.getBean( User.class );
		System.out.println( user );
		
		((ConfigurableApplicationContext) appCtx).close();
	}
	
	/**
	 * Java Config 02
	 * 직접 설정 자바 클래스가 있는 베이스 패키지를 지정하는 방법
	 * 설정 자바 클래스에 @Configuration 필수
	 */
	public static void testJavaConfig02() {
		ApplicationContext appCtx = new AnnotationConfigApplicationContext( "config.user" );
		
		User user = appCtx.getBean( User.class );
		System.out.println( user );
		
		((ConfigurableApplicationContext) appCtx).close();
	}
	
	/**
	 * Java Config 03 - 자동 설정
	 * Component Scanning( @Component, @Autowired )
	 */
	public static void testJavaConfig03() {
		ApplicationContext appCtx = new AnnotationConfigApplicationContext( "config.soundsystem" );

		CompactDisc cd = appCtx.getBean( CompactDisc.class );
		System.out.println( cd );

		cd = (CompactDisc) appCtx.getBean( "Parachutes" );
		System.out.println( cd );

		CDPlayer cdPlayer = appCtx.getBean( CDPlayer.class );
		cdPlayer.play();

		((ConfigurableApplicationContext) appCtx).close();
	}
	
	/**
	 * Java Config 04 - 명시적 설정
	 * Don't use @ComponentScan
	 * Use @Bean of method in java config class
	 */
	public static void testJavaConfig04() {
		ApplicationContext appCtx = new AnnotationConfigApplicationContext( "config.videosystem" );
		
		DigitalVideoDisc dvd = appCtx.getBean( DigitalVideoDisc.class );
		System.out.println( dvd );
		
		DVDPlayer dvdPlayer = appCtx.getBean( DVDPlayer.class );
		dvdPlayer.play();

//		DVDPlayer dvdPlayer = (DVDPlayer) appCtx.getBean( "dvdPlayer2" );
//		dvdPlayer.play();
		
		((ConfigurableApplicationContext) appCtx).close();
	}
	
	/**
	 * Java Config 05 - mixing 01
	 * Java Config <- Java Config
	 */
	public static void testJavaConfig05() {
		ApplicationContext appCtx = new AnnotationConfigApplicationContext( DVDPlayerConfig.class );
		
		DVDPlayer dvdPlayer = appCtx.getBean( DVDPlayer.class );
		dvdPlayer.play();
		
		((ConfigurableApplicationContext) appCtx).close();
	}

	/**
	 * Java Config 06 - mixing 02
	 * Java Config <- Java Config + Java Config
	 */
	public static void testJavaConfig06() {
		ApplicationContext appCtx = new AnnotationConfigApplicationContext( VideoSystemConfig.class );
		
		DVDPlayer dvdPlayer = appCtx.getBean( DVDPlayer.class );
		dvdPlayer.play();
		
		((ConfigurableApplicationContext) appCtx).close();
	}

	/**
	 * Java Config 07 - mixing 03
	 * Java Config <- Java Config + XML Config
	 */
	public static void testJavaConfig07() {
	}

}
