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
	
	@Column(name="email")
	private String email;
	
	@Column(name = "enabled", nullable = false)
    private boolean enabled = true;
	
	@ManyToOne(targetEntity = Role.class)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
	
	public User() {
	}

	public User(UserBuilder builder) {

		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj){
			
			return true;
		}
		if (obj == null){
			
			return false;
		}
		if (getClass() != obj.getClass()){
			
			return false;
		}
		User other = (User) obj;
		if (enabled != other.enabled){
			
			return false;
		}
		if (id != other.id){
			
			return false;
		}
		if (password == null) {
			if (other.password != null){
				
				return false;
			}
		} else if (!password.equals(other.password)){
			
			return false;
		}
		if (role == null) {
			if (other.role != null){
				
				return false;
			}
		} else if (!role.equals(other.role)){
			
			return false;
		}
		if (username == null) {
			if (other.username != null){
				
				return false;
			}
		} else if (!username.equals(other.username)){
			
			return false;
		}
		
		return true;
	}

	public static class UserBuilder {

		
		private String username;
		private String password;
		private Role role;
		@SuppressWarnings("unused")
		private long id;
		private String email;


		public UserBuilder() {}
		
		public UserBuilder (String username, String password, Role role, String email) {

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
		public UserBuilder setEmail(String email) {
			this.email = email;
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

		public String getEmail() {
			return email;
		}

	}
}
