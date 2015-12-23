package br.com.bluesoft.votenorestaurante.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.bluesoft.votenorestaurante.dto.RestaurantCombinationDTO;
import br.com.bluesoft.votenorestaurante.model.Restaurant;
import br.com.bluesoft.votenorestaurante.repository.RestaurantRepository;
import br.com.bluesoft.votenorestaurante.service.RestaurantService;

@Controller
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;

	@Autowired
	RestaurantRepository restaurantRepository;

	@PostConstruct
	public void populaRestaurantes() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		if (restaurants == null || restaurants.isEmpty()) {
			restaurantRepository.save(new Restaurant("Outback Steakhouse", "resources/image/outback.PNG"));
			restaurantRepository.save(new Restaurant("Baby Beef Steakhouse", "resources/image/babybeef.PNG"));
			restaurantRepository.save(new Restaurant("Ávila Steakhouse", "resources/image/avila.PNG"));
			restaurantRepository.save(new Restaurant("Snap Steakhouse & Bar", "resources/image/snap.PNG"));
			restaurantRepository.save(new Restaurant("west Steakhouse", "resources/image/west.PNG"));
		}
	}

	@RequestMapping(value = "/restaurants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<RestaurantCombinationDTO> restaurantes() {
		List<RestaurantCombinationDTO> restaurantCombinations = restaurantService.restaurantCombinations();
		System.out.println(restaurantCombinations.size());
		return restaurantCombinations;
	}

}
