package hr.excilys.model;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private User user;
	Set<GrantedAuthority> authorities;

	public CustomUserDetails(CustomBuilder builder) {

		this.user = builder.user;
		this.setAuthorities();
	}
	
	public User getUser() {
		
		return user;
	}

	public void setUser(User user) {
		
		this.user = user;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	private void setAuthorities() {
		
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName());
		authorities.add(authority);
	}

	public String getPassword() {
		
		return user.getPassword();
	}

	public String getUsername() {
		
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {

		return true;
	}

	public boolean isAccountNonLocked() {

		return true;
	}

	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	public boolean isEnabled() {
		
		return user.isEnabled();
	}
	
	public static class CustomBuilder {

		private User user;

		public CustomBuilder (User user) {

			this.user = user;
		}

		public CustomUserDetails build() {

			return new CustomUserDetails(this);
		}
	}

}