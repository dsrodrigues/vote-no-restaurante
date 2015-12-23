package br.com.bluesoft.votenorestaurante.dto;

import javax.persistence.Entity;

import br.com.bluesoft.votenorestaurante.model.Restaurant;

public class RestaurantCombinationDTO {

	Restaurant restaurantLeft;
	Restaurant restaurantRight;

	public RestaurantCombinationDTO() {
	}

	public RestaurantCombinationDTO(Restaurant restaurantLeft, Restaurant restaurantRight) {
		this.restaurantLeft = restaurantLeft;
		this.restaurantRight = restaurantRight;
	}

	public Restaurant getRestaurantLeft() {
		return restaurantLeft;
	}

	public void setRestaurantLeft(Restaurant restaurantLeft) {
		this.restaurantLeft = restaurantLeft;
	}

	public Restaurant getRestaurantRight() {
		return restaurantRight;
	}

	public void setRestaurantRight(Restaurant restaurantRight) {
		this.restaurantRight = restaurantRight;
	}

	@Override
	public String toString() {
		return "RestaurantCombinationDTO [restaurantLeft=" + restaurantLeft + ", restaurantRight=" + restaurantRight + "]";
	}

}
