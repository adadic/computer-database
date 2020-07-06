package hr.excilys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true")
				.permitAll().and().logout().deleteCookies("JSESSIONID").logoutUrl("/login");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {

		// authManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}
}
