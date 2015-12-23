package br.com.bluesoft.votenorestaurante.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votenorestaurante.model.User;
import br.com.bluesoft.votenorestaurante.repository.UserRepository;

@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User insert(User user) {
		User userFinder = this.findByEmail(user.getEmail());
		if (userFinder == null) {
			entityManager.persist(user);
			return user;
		}
		return userFinder;
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

	@Override
	public User findById(Integer id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		return entityManager.createNamedQuery("userFindAll", User.class).getResultList();
	}

	@Override
	public void refresh(User user) {
		entityManager.refresh(user);
	}

	@Override
	public void remove(User user) {
		entityManager.remove(user);
	}

	@Override
	public User findByEmail(String email) {
		try {
			return entityManager.createNamedQuery("findByEmail", User.class).setParameter(1, email).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

}
