package br.com.bluesoft.votenorestaurante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.bluesoft.votenorestaurante.model.User;
import br.com.bluesoft.votenorestaurante.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	@Qualifier("userRepositoryImpl")
	private UserRepository userRepository;

	public User registerUser(String name, String email) {
		return userRepository.insert(new User(name, email));
	}
	
}
