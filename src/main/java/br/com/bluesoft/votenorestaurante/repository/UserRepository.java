package br.com.bluesoft.votenorestaurante.repository;

import java.util.List;

import br.com.bluesoft.votenorestaurante.model.User;

public interface UserRepository {

	User insert(User user);

	void update(User user);

	User findById(Integer id);

	List<User> findAll();

	void refresh(User user);

	void remove(User user);

	User findByEmail(String email);

}
