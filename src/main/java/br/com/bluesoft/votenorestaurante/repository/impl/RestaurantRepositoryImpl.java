package br.com.bluesoft.votenorestaurante.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votenorestaurante.model.Restaurant;
import br.com.bluesoft.votenorestaurante.repository.RestaurantRepository;

@Transactional
@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void save(Restaurant restaurant) {
		entityManager.persist(restaurant);
	}

	@Override
	public void update(Restaurant restaurant) {
		entityManager.merge(restaurant);
	}

	@Override
	public Restaurant findById(Integer id) {
		return entityManager.createNamedQuery("restaurantById", Restaurant.class).setParameter(1, id).getSingleResult();
	}

	@Override
	public List<Restaurant> findAll() {
		return entityManager.createNamedQuery("restaurantFindAll", Restaurant.class).getResultList();
	}

	@Override
	public void refresh(Restaurant restaurant) {
		entityManager.refresh(restaurant);
	}

	@Override
	public void remove(Restaurant restaurant) {
		entityManager.remove(restaurant);
		entityManager.flush();
	}

	@Override
	public List<Restaurant> listByVoteDesc() {
		return entityManager.createNamedQuery("listByVoteDesc", Restaurant.class).getResultList();
	}

}
