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
@Table( name = "user" )
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name ="username", unique = true)
	private String username;
	
	@Column(name ="password")
	private String password;
	
	@Column(name = "enabled", nullable = false)
    private boolean enabled = true;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Role.class)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
	
	public User() {
	}

	public User(UserBuilder builder) {

		this.id = builder.id;
		this.username = builder.username;
		this.password = builder.password;
		this.role = builder.role;
	}

	public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
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

	public Role getRole() {
		
		return role;
	}

	public void setRole(Role role) {
		
		this.role = role;
	}

	public boolean isEnabled() {
		
		return enabled;
	}

	@Override
	public String toString() {
		
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	public static class UserBuilder {

		private long id;
		private String username;
		private String password;
		private Role role;

		public UserBuilder() {}
		
		public UserBuilder (String username, String password, Role role) {

			this.username = username;
			this.password = password;
			this.role = role;
		}

		public User build() {

			return new User(this);
		}

		public UserBuilder id(long id) {
			
			this.id = id;
			
			return this;
		}
		
		public UserBuilder setUsername(String username) {
			this.username = username;
			return this;
		}
		
		public UserBuilder setPassword(String password) {
			this.password = password;
			return this;
		}
		
		public UserBuilder setRole(Role role) {
			this.role = role;
			return this;
		}
		
	}
}
