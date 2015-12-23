package br.com.bluesoft.votenorestaurante.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votenorestaurante.model.Restaurant;
import br.com.bluesoft.votenorestaurante.model.User;
import br.com.bluesoft.votenorestaurante.model.Voting;
import br.com.bluesoft.votenorestaurante.repository.VotingRepository;

@Transactional
@Repository
public class VotingRepositoryImpl implements VotingRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Voting insert(Voting voting) {
		entityManager.persist(voting);
		return voting;
	}

	@Override
	public void update(Voting voting) {
		entityManager.merge(voting);
	}

	@Override
	public List<Voting> findByUser(User user) {
		return entityManager.createNamedQuery("findByUser", Voting.class).setParameter(1, user.getId()).getResultList();
	}

	@Override
	public void removeByUser(User user) {
		entityManager.createNamedQuery("removeByUser", Voting.class).setParameter(1, user.getId()).executeUpdate();
	}

	@Override
	public Voting findByUserAndRestaurant(User user, Restaurant restaurant) {
		try {
			return entityManager.createNamedQuery("findByUserAndRestaurant", Voting.class).setParameter(1, user.getId())
					.setParameter(2, restaurant.getId()).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

}
