package br.com.bluesoft.votenorestaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.bluesoft.votenorestaurante.model.Restaurant;
import br.com.bluesoft.votenorestaurante.model.User;
import br.com.bluesoft.votenorestaurante.model.Voting;
import br.com.bluesoft.votenorestaurante.repository.RestaurantRepository;
import br.com.bluesoft.votenorestaurante.repository.UserRepository;
import br.com.bluesoft.votenorestaurante.repository.VotingRepository;

@Service
public class VotingService {

	@Autowired
	@Qualifier("votingRepositoryImpl")
	VotingRepository votingRepository;

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RestaurantRepository restaurantRepository;

	public void registerUserVotes(String name, String email, String votes) {
		User user = userRepository.findByEmail(email);
		if (user != null)
			return;

		user = userService.registerUser(name, email);
		String[] votesSplit = votes.split(",");
		for (String id : votesSplit) {
			Integer restaurantId = Integer.parseInt(id);
			this.registerVotes(user, restaurantId);
		}
	}

	private void registerVotes(User user, Integer restaurantId) {
		Restaurant restaurant = restaurantRepository.findById(restaurantId);

		Voting voting = votingRepository.findByUserAndRestaurant(user, restaurant);
		if (voting == null)
			voting = votingRepository.insert(new Voting(user, restaurant));

		voting.addVote();
		votingRepository.update(voting);

		restaurant.addVote();
		restaurantRepository.update(restaurant);
	}

	public List<Voting> getUserRestaurantVoting(String email) {
		User user = userRepository.findByEmail(email);
		return votingRepository.findByUser(user);
	}
}