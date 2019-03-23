package config.mixing.videosystem;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.corock.springcontainer.videosystem.DVDPlayer;
import com.corock.springcontainer.videosystem.DigitalVideoDisc;

@Configuration
@Import( DVDConfig.class )
public class DVDPlayerConfig {

	/* cf. @Qualifier("avengers") */
	@Bean
	public DVDPlayer dvdPlayer( @Qualifier("avengersInfinityWar") DigitalVideoDisc dvd ) {
		return new DVDPlayer( dvd );
	}
	
}
