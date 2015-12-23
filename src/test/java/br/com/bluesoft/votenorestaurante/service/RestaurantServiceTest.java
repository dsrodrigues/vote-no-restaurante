package br.com.bluesoft.votenorestaurante.service;

import java.util.List;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.bluesoft.votenorestaurante.config.AppConfig;
import br.com.bluesoft.votenorestaurante.dto.RestaurantCombinationDTO;
import br.com.bluesoft.votenorestaurante.model.Restaurant;
import br.com.bluesoft.votenorestaurante.repository.RestaurantRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class RestaurantServiceTest {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	RestaurantService restaurantService;

	@Before
	public void setup() {
		restaurantRepository.save(new Restaurant("Outback Steakhouse", "resources/image/outback.PNG"));
		restaurantRepository.save(new Restaurant("Baby Beef Steakhouse", "resources/image/babybeef.PNG"));
		restaurantRepository.save(new Restaurant("Ávila Steakhouse", "resources/image/avila.PNG"));
		restaurantRepository.save(new Restaurant("Snap Steakhouse & Bar", "resources/image/snap.PNG"));
		restaurantRepository.save(new Restaurant("west Steakhouse", "resources/image/west.PNG"));
	}

	@Test
	public void updateVoteTest() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		for (Restaurant restaurant : restaurants) {
			restaurantService.updateVote(restaurant, 1);
			Assert.assertEquals((Integer) 1, restaurant.getVote());
		}
	}

	@Test
	public void restaurantCombinationsTest() {
		List<RestaurantCombinationDTO> combinations = restaurantService.restaurantCombinations();
		System.out.println(combinations.size());
		List<Restaurant> restaurants = restaurantRepository.findAll();
		System.out.println(restaurants.size());
		Assert.assertEquals(caculateCombination(restaurants.size()), (Integer) combinations.size());
	}
	
	@Test
	public void getRestaurantVotingTest() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		restaurantService.updateVote(restaurants.get(0), 15);
		restaurantService.updateVote(restaurants.get(1), 25);
		restaurantService.updateVote(restaurants.get(2), 35);
		
		List<Restaurant> restaurantVoting = restaurantService.getRestaurantVoting();
		Assert.assertEquals(restaurants.get(2).getId(), restaurantVoting.get(0).getId());
		Assert.assertEquals(restaurants.get(1).getId(), restaurantVoting.get(1).getId());
		Assert.assertEquals(restaurants.get(0).getId(), restaurantVoting.get(2).getId());
	}

	private Integer caculateCombination(int size) {
		// n! / (p! (n - p)!
		Long n = CombinatoricsUtils.factorial(size);
		Long p = CombinatoricsUtils.factorial(2);
		Long np = CombinatoricsUtils.factorial(size - 2);
		Long result = n / (p * np);
		return Integer.parseInt(String.valueOf(result));
	}
}
