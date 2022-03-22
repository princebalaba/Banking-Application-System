package com.learning.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.learning.jwt.AuthEntryPointJwt;
import com.learning.jwt.AuthTokenFilter;
import com.learning.security.service.UserDetailsServiceImpl;

/**
 * @author : Ki Beom Lee
 * @time : 2022. 2. 23.-오후 4:41:40
 */
@Configuration
@EnableWebSecurity // it will make sure that security env. is enabled
@EnableGlobalMethodSecurity(prePostEnabled = true) //
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
//	@Autowired
//	private ControllerAdvice unauthorizedHandler ;
//	@Scope("prototype") for more than one 
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override // auth manager
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//core part of the security -> we can restrict the access of endpoints through this config.
		//we can set unauthorized access through this. 
		//we can provide direct go  access for sign up and sign in(authorizing hte res).
		//apply token validation for end points.
		//cors:
		//
		http.cors().and().csrf().disable().exceptionHandling() // end points 
		.authenticationEntryPoint(unauthorizedHandler)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()


		.authorizeRequests().antMatchers("/api/customer/register").permitAll()
		.antMatchers("/api/customer/authenticate").permitAll()
		.antMatchers("/api/staff/authenticate").permitAll()
		.antMatchers("/api/admin/authenticate").permitAll()

		.antMatchers("/api/customer/**").authenticated()
		.antMatchers("/api/staff/**").authenticated()
		.antMatchers("/api/admin/**").authenticated()
		.anyRequest().permitAll();

		

		

		
		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		
	
	}
	
	

}
