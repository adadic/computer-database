package hr.excilys.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table( name = "USER" )
public class User implements UserDetails{
	
	private static final long serialVersionUID = 6076716518355255683L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name ="username", unique = true)
	private String username;
	
	@Column(name ="password")
	private String password;
	
	@Column(name = "enabled", nullable = false)
    private boolean enabled;
	
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}


	public static class UserBuilder {

		private long id;
		private String username;
		private String password;
		private Role role;

		public UserBuilder (String username, String password, Role role) {

			this.username = username;
			this.password = password;
			this.role = role;
		}

		public User build() {

			return new User(this);
		}
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + this.getRole().getRoleName());
		grantList.add(authority);
		
		return grantList;
	}

	@Override
	public boolean isAccountNonExpired() {

		return false;
	}

	@Override
	public boolean isAccountNonLocked() {

		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return false;
	}

	@Override
	public boolean isEnabled() {

		return enabled;
	}
}
