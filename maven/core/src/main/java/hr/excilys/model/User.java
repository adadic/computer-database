package hr.excilys.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Entity
@Table( name = "USER" )
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name ="firstname")
	private String firstname;
	@Column(name ="lastname")
	private String lastname;
	@Column(name ="username")
	private String username;
	@Column(name ="password")
	private String password;
	@Column(name ="email", unique = true)
	private String email;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Role.class)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
	
	public User() {
	}

	public User(UserBuilder builder) {

		this.id = builder.id;
		this.firstname = builder.firstname;
		this.lastname = builder.lastname;
		this.username = builder.username;
		this.password = builder.password;
		this.email = builder.email;
		this.role = builder.role;
	}

	public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
	}

	public String getFirstname() {
		
		return firstname;
	}

	public void setFirstname(String firstname) {
		
		this.firstname = firstname;
	}

	public String getLastname() {
		
		return lastname;
	}

	public void setLastname(String lastname) {
		
		this.lastname = lastname;
	}

	public String getUsername() {
		
		return username;
	}

	public void setUsername(String username) {
		
		this.username = username;
	}

	public String getPassword() {
		
		return password;
	}

	public void setPassword(String password) {
		
		this.password = password;
	}

	public String getEmail() {
		
		return email;
	}

	public void setEmail(String email) {
		
		this.email = email;
	}

	public Role getRole() {
		
		return role;
	}

	public void setRole(Role role) {
		
		this.role = role;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}


	public static class UserBuilder {

		private long id;
		private String firstname;
		private String lastname;
		private String username;
		private String password;
		private String email;
		private Role role;

		public UserBuilder firstname(String username, String email, String password, Role role) {

			this.username = username;
			this.email = email;
			this.password = password;
			this.role = role;

			return this;
		}
		
		public UserBuilder firstname(String firstname) {

			this.firstname = firstname;

			return this;
		}
		
		public UserBuilder lastname(String lastname) {

			this.lastname = lastname;

			return this;
		}

		public User build() {

			return new User(this);
		}
	}
}
