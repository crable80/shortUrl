package io.namoosori.userrest.entity;

import java.util.UUID;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private String id;
	private String name;
	private String email;
	
	public User() {
		this.id = UUID.randomUUID().toString();
	}
	
	public User(String name, String email) {
		this();
		this.name = name;
		this.email = email;
	}
	
	public static User sample() {
		return new User("crable", "crable@naver.com");
	}
	
	public static void main(String[] args) {
		User user = new User("frodo", "frodo80@gmail.com");
		System.out.println(new Gson().toJson(user));
		
	}

}
