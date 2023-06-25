package studio.thinkground.aroundhub.data.entity.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import studio.thinkground.aroundhub.data.entity.Listener;

public class CustomListener {

	private final Logger LOGGER = LoggerFactory.getLogger(CustomListener.class);
	
	//get 후 
	@PostLoad
	public void postLoad(Listener entity) {
		LOGGER.info("[postLoad] called!!");
	}
	
	//save 전
	@PrePersist
	public void prePersist(Listener entity) {
		LOGGER.info("[prePersist] called!!");
	}
	
	//save 후
	@PostPersist
	public void postPersist(Listener entity) {
		LOGGER.info("[postPersist] called!!");
	}
	
	@PreUpdate
	public void preUpdate(Listener entity) {
		LOGGER.info("[preUpdate] called!!");
	}
	
	@PostUpdate
	public void postUpdate(Listener entity) {
		LOGGER.info("[postUpdate] called!!");
	}
	
	@PreRemove
	public void preRemove(Listener entity) {
		LOGGER.info("[preRemove] called!!");
	}
	
	@PostRemove
	public void postRemoeve(Listener entity) {
		LOGGER.info("[postRemove] called!!");
	}
	
	
}
