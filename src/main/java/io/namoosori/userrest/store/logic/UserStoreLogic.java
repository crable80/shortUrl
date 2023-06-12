package io.namoosori.userrest.store.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import io.namoosori.userrest.entity.User;
import io.namoosori.userrest.store.UserStore;

@Repository
public class UserStoreLogic implements UserStore{
	
	private Map<String, User> userMap; //DB 용도로 사용
	
	public UserStoreLogic() {
		this.userMap = new HashMap<>();
	}

	@Override
	public String create(User user) {
		// TODO Auto-generated method stub
		this.userMap.put(user.getId(), user);
		return user.getId();
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		this.userMap.put(user.getId(), user);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		this.userMap.remove(id);
	}

	@Override
	public User retrieve(String id) {
		// TODO Auto-generated method stub
		return this.userMap.get(id);
	}

	@Override
	public List<User> retrieveAll() {
		// TODO Auto-generated method stub
		return this.userMap.values().stream().collect(Collectors.toList());
	}
	

}
