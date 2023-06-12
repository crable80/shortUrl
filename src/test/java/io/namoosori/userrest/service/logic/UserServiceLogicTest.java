package io.namoosori.userrest.service.logic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.namoosori.userrest.entity.User;
import io.namoosori.userrest.service.UserService;

/**
 * @author frodo
 * 단위 테스트
 */
@SpringBootTest
public class UserServiceLogicTest {

	@Autowired
	private UserService userService;
	
	private User kim;
	private User lee;
	
	@BeforeEach
	public void startUp() {
		this.kim = new User("kim", "kim@namoosori.io");
		this.lee = new User("lee", "lee@namoosori.io");
		userService.register(kim);
		userService.register(lee);
	}
	
	@Test
	public void registerTest() {
		User sample = User.sample();
		assertThat(this.userService.register(sample)).isEqualTo(sample.getId());
		assertThat(this.userService.findAll().size()).isEqualTo(3);
		this.userService.remove(sample.getId());
	}
	
	@Test
	public void find() {
		assertThat(this.userService.find(lee.getId())).isNotNull();
		assertThat(this.userService.find(lee.getId()).getEmail()).isEqualTo(lee.getEmail());
	}
	
	@AfterEach
	public void cleanUp() {
		this.userService.remove(kim.getId());
		this.userService.remove(lee.getId());
	}
}
