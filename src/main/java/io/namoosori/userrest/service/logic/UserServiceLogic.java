package io.namoosori.userrest.service.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import io.namoosori.userrest.entity.User;
import io.namoosori.userrest.service.UserService;
import io.namoosori.userrest.store.UserStore;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceLogic implements UserService{
	private final UserStore userStore;

	@Override
	public String register(User user) {
		// TODO Auto-generated method stub
		return this.userStore.create(user);
	}

	@Override
	public void modify(User user) {
		// TODO Auto-generated method stub
		this.userStore.update(user);
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		this.userStore.delete(id);
	}

	@Override
	public User find(String id) {
		// TODO Auto-generated method stub
		return this.userStore.retrieve(id);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return this.userStore.retrieveAll();
	}

}
