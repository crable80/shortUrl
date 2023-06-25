package studio.thinkground.aroundhub.service;

import studio.thinkground.aroundhub.data.entity.Listener;

public interface ListenerService {

	Listener getEntity(Long id);
	
	void saveEntity(Listener listenerEntity);
	
	void updateEntity(Listener listenerEntity);
	
	void removeEntity(Listener listenerEntity);
	
}
