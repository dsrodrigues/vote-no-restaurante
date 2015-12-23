package br.com.bluesoft.votenorestaurante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = { @NamedQuery(name = "restaurantFindAll", query = "SELECT r FROM Restaurant r"),
		@NamedQuery(name = "listByVoteDesc", query = "SELECT r FROM Restaurant r ORDER BY r.vote DESC"),
		@NamedQuery(name = "restaurantById", query = "SELECT r FROM Restaurant r WHERE r.id = ?")})
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(length = 45, nullable = false)
	String name;

	@Column(name = "image_path", length = 150)
	String imagePath;

	@Column(nullable = false)
	Integer vote = 0;

	public Restaurant() {
	}

	public Restaurant(int id, String name, String imagePath) {
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
	}

	public Restaurant(String name, String imagePath) {
		this.name = name;
		this.imagePath = imagePath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", imagePath=" + imagePath + ", vote=" + vote + "]";
	}

	public void addVote() {
		this.vote += 1;
	}

}
