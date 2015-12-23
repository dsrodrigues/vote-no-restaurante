package br.com.bluesoft.votenorestaurante.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.bluesoft.votenorestaurante.model.Restaurant;

@Controller
public class HomeController {

	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
}
