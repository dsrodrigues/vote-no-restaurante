package br.com.bluesoft.votenorestaurante.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.bluesoft.votenorestaurante.config.AppConfig;
import br.com.bluesoft.votenorestaurante.model.User;
import br.com.bluesoft.votenorestaurante.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class UserServiceTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	User userDefault = null;

	@Before
	public void setup() {
		userDefault = userService.registerUser("Diego da Silva Rodrigues", "dev.dsrodrigues@gmail.com");
	}

	@Test
	public void findUserByEmail() {
		User find = userRepository.findByEmail("dev.dsrodrigues@gmail.com");
		Assert.assertEquals(userDefault.getId(), find.getId());
	}

	@Test
	public void registerNewUserTest() {
		User register = userService.registerUser("Vivian Campos", "viviancampos@gmail.com");
		User find = userRepository.findByEmail("viviancampos@gmail.com");
		Assert.assertEquals(register.getId(), find.getId());
	}
}
