package br.com.bluesoft.votenorestaurante.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries(value = { @NamedQuery(name = "userFindAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "findByEmail", query = "SELECT u FROM User u WHERE u.email = ?") })
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="user_id_seq")
	@SequenceGenerator(name="user_id_seq", sequenceName="user_id_seq")
	Integer id;

	@Column(length = 100, nullable = false)
	String name;

	@Column(length = 150, nullable = false)
	String email;

	@OneToMany(mappedBy = "user")
	@OrderBy("vote DESC")
	List<Voting> votings;

	public User() {
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}

	public List<Voting> getVotings() {
		return votings;
	}

	public void setVotings(List<Voting> votings) {
		this.votings = votings;
	}
}
