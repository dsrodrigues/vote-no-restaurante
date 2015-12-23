package br.com.bluesoft.votenorestaurante.repository;

import java.util.List;

import br.com.bluesoft.votenorestaurante.model.Restaurant;
import br.com.bluesoft.votenorestaurante.model.User;
import br.com.bluesoft.votenorestaurante.model.Voting;

public interface VotingRepository {

	Voting insert(Voting voting);

	void update(Voting voting);

	List<Voting> findByUser(User user);

	void removeByUser(User user);

	Voting findByUserAndRestaurant(User user, Restaurant restaurant);

}
