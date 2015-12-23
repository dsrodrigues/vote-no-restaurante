package br.com.bluesoft.votenorestaurante.repository;

import java.util.List;

import br.com.bluesoft.votenorestaurante.model.Restaurant;

public interface RestaurantRepository {

	void save(Restaurant restaurant);

	void update(Restaurant restaurant);

	Restaurant findById(Integer id);

	List<Restaurant> findAll();

	void refresh(Restaurant restaurant);

	void remove(Restaurant restaurant);

	List<Restaurant> listByVoteDesc();

}
