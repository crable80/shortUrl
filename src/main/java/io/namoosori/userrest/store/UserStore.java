package io.namoosori.userrest.store;

import java.util.List;

import io.namoosori.userrest.entity.User;

public interface UserStore {
	String create(User user);
	void update(User user);
	void delete(String id);
	
	User retrieve(String id);
	List<User> retrieveAll();

}
