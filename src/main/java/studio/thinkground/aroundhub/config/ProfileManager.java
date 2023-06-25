package studio.thinkground.aroundhub.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author frodo
 * 프로파일 매니저
 */
@Component
public class ProfileManager {

	private final Logger LOGGER = LoggerFactory.getLogger(ProfileManager.class);
	private final Environment environment;
	
	@Autowired
	public ProfileManager(Environment environment) {
		this.environment = environment;
	}
	
	/**
	 *  활성화된 프로파일을 프린트한다.
	 */
	public void printActiveProfiles() {
		LOGGER.info("[printActiveProfiles] active profiles size : {}", environment.getActiveProfiles().length);
		for(String profile : environment.getActiveProfiles()) {
			LOGGER.info("[printActiveProfiles] profile : {}", profile);
		}
	}
}
