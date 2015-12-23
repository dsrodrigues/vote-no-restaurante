package br.com.bluesoft.votenorestaurante.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.bluesoft.votenorestaurante.config.AppConfig;
import br.com.bluesoft.votenorestaurante.model.Restaurant;
import br.com.bluesoft.votenorestaurante.model.Voting;
import br.com.bluesoft.votenorestaurante.repository.RestaurantRepository;
import br.com.bluesoft.votenorestaurante.repository.VotingRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class VotingServiceTest {

	@Autowired
	VotingRepository votingRepository;

	@Autowired
	VotingService votingService;

	@Autowired
	RestaurantRepository restaurantRepository;

	@Before
	public void setup() {
		restaurantRepository.save(new Restaurant("Outback Steakhouse", "resources/image/outback.PNG"));
		restaurantRepository.save(new Restaurant("Baby Beef Steakhouse", "resources/image/babybeef.PNG"));
		restaurantRepository.save(new Restaurant("Ávila Steakhouse", "resources/image/avila.PNG"));
		restaurantRepository.save(new Restaurant("Snap Steakhouse & Bar", "resources/image/snap.PNG"));
		restaurantRepository.save(new Restaurant("west Steakhouse", "resources/image/west.PNG"));
	}

	@Test
	public void registerUserVotesTest() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		votingService.registerUserVotes("Diego", "dev.dsrodrigues@gmail.com", String.format("%d,", restaurants.get(0).getId()));
		List<Voting> userRestaurantVoting = votingService.getUserRestaurantVoting("dev.dsrodrigues@gmail.com");
		Assert.assertEquals((Integer) 1, (Integer) userRestaurantVoting.size());
		Assert.assertEquals(restaurants.get(0).getId(), userRestaurantVoting.get(0).getRestaurant().getId());
	}
}
