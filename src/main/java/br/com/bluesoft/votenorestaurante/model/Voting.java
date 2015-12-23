package br.com.bluesoft.votenorestaurante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries(value = { @NamedQuery(name = "findByUser", query = "SELECT v FROM Voting v WHERE v.user.id = ? ORDER BY v.vote DESC"),
		@NamedQuery(name = "removeByUser", query = "DELETE FROM Voting v WHERE v.user.id = ?"),
		@NamedQuery(name = "findByUserAndRestaurant", query = "SELECT v FROM Voting v WHERE v.user.id = ? AND v.restaurant.id = ?") })
public class Voting {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="voting_id_seq")
	@SequenceGenerator(name="voting_id_seq", sequenceName="voting_id_seq")
	Integer id;

	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

	@Column(nullable = false)
	Integer vote = 0;

	public Voting() {
	}

	public Voting(User user, Restaurant restaurant) {
		this.user = user;
		this.restaurant = restaurant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "Voting [id=" + id + ", restaurant=" + restaurant.getName() + "(" + restaurant.getId() + ")" + ", user=" + user.getName()
				+ "(" + user.getId() + ")" + ", vote=" + vote + "]";
	}

	public void addVote() {
		this.vote += 1;
	}

}
