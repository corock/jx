package config.mixing.videosystem;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import( DVDPlayerConfig.class )
@ImportResource( "classpath:config/mixing/videosystem/DVDConfig.xml" )
public class VideoSystemConfig02 {
}
