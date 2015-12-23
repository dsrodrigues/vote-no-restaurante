package br.com.bluesoft.votenorestaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.bluesoft.votenorestaurante.service.RestaurantService;
import br.com.bluesoft.votenorestaurante.service.UserService;
import br.com.bluesoft.votenorestaurante.service.VotingService;

@Controller
public class VotingController {

	@Autowired
	VotingService votingService;

	@Autowired
	UserService userService;

	@Autowired
	RestaurantService restaurantService;

	@RequestMapping(value = "/voting", method = RequestMethod.GET)
	public String voting(String name, String email, String votes) {
		votingService.registerUserVotes(name, email, votes);
		return "forward:/votingRanked";
	}

	@RequestMapping(value = "/votingRanked", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String ranking(Model model, String email) {
		model.addAttribute("userRestaurantRanking", votingService.getUserRestaurantVoting(email));
		model.addAttribute("restaurantRanking", restaurantService.getRestaurantVoting());
		return "votingResult";
	}
}
