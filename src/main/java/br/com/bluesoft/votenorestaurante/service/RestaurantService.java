package br.com.bluesoft.votenorestaurante.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.bluesoft.votenorestaurante.dto.RestaurantCombinationDTO;
import br.com.bluesoft.votenorestaurante.model.Restaurant;
import br.com.bluesoft.votenorestaurante.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	@Qualifier("restaurantRepositoryImpl")
	private RestaurantRepository restaurantRepository;

	public void updateVote(Restaurant restaurant, Integer vote) {
		restaurant.setVote(restaurant.getVote() + vote);
		restaurantRepository.update(restaurant);
	}

	public List<RestaurantCombinationDTO> restaurantCombinations() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		Map<String, RestaurantCombinationDTO> restaurantCombinations = new HashMap<String, RestaurantCombinationDTO>();

		for (int i = 0; i < restaurants.size(); i++) {
			for (int j = 0; j < restaurants.size(); j++) {
				Integer idRestaurantLeft = restaurants.get(i).getId();
				Integer idRestaurantRight = restaurants.get(j).getId();
				if (idRestaurantLeft != idRestaurantRight
						&& !restaurantCombinations.containsKey(String.format("%d-%d", idRestaurantLeft, idRestaurantRight))
						&& !restaurantCombinations.containsKey(String.format("%d-%d", idRestaurantRight, idRestaurantLeft)))
					restaurantCombinations.put(String.format("%d-%d", restaurants.get(i).getId(), restaurants.get(j).getId()),
							new RestaurantCombinationDTO(restaurants.get(i), restaurants.get(j)));
			}
		}

		// Not Need
		List<String> keySet = new ArrayList<>(restaurantCombinations.keySet());
		Collections.shuffle(keySet);

		List<RestaurantCombinationDTO> restaurantCombinationDTO = new ArrayList<>();
		for (String key : keySet)
			restaurantCombinationDTO.add(restaurantCombinations.get(key));
		return restaurantCombinationDTO;
	}

	public List<Restaurant> getRestaurantVoting() {
		return restaurantRepository.listByVoteDesc();
	}
}
