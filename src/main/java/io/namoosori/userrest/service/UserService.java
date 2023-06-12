package io.namoosori.userrest.service;

import java.util.List;

import io.namoosori.userrest.entity.User;

public interface UserService {

	String register(User user);
	void modify(User user);
	void remove(String id);
	
	User find(String id);
	List<User> findAll();	
}
