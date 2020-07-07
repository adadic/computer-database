package hr.excilys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hr.excilys.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserService userService;

	@Autowired
	public WebSecurityConfig(UserService userService) {

		this.userService = userService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/*", "/login").hasRole("ADMIN").and().httpBasic();

//		http.cors().and().csrf().disable();
//
//		http.formLogin().loginPage("/login").permitAll().and().logout().logoutUrl("redirect:/login")
//				.logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID").clearAuthentication(true)
//				.permitAll();
//
//		http.csrf().disable();
//
//		http.authorizeRequests().antMatchers("/", "/login").permitAll();
//
//		http.authorizeRequests().antMatchers("/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
//
//		http.authorizeRequests().antMatchers("/editComputer", "/deleteComputer", "/addComputer")
//				.access("hasRole('ROLE_ADMIN')");
//
//		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//
//		http.authorizeRequests().and().formLogin().loginProcessingUrl("/j_spring_security_check").loginPage("/login")
//				.defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").usernameParameter("username")
//				.passwordParameter("password").and().logout().logoutUrl("/logout")
//				.logoutSuccessUrl("/logoutSuccessful");

	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}
}
