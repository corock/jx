package config.soundsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( basePackages = "com.corock.springcontainer.soundsystem" )
// @ComponentScan(basePackages = { "com.corock.springcontainer.soundsystem", "com.corock.springcontainer.videosystem" })
/**
 * @ComponentScan(basePackageClasses = Index.class)
 * Error!
 * basePackageClasses에 지정한 클래스가 속한 패키지를 Base Package로 사용한다.
 */
public class CDPlayerConfig {

//	@Bean
//	public User method() {
//		return new User();
//	}

}
