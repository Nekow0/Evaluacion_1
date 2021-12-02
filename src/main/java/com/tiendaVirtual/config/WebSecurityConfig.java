package com.tiendaVirtual.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    UserDetailsService usd;

	
	
	protected void configure(HttpSecurity http) throws Exception {
		CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
		http.authorizeRequests()
		.antMatchers("/crearUsuario", "/", "/usuario/create", "/usuario/login") // Para agregar mas ,"/url"														// //css/** js/**
		.permitAll()
				// .hasRole("USER")
		.antMatchers("/**").authenticated()
		.and()
		.formLogin()
			.loginPage("/login").permitAll()
			.usernameParameter("email").passwordParameter("password")
			.loginProcessingUrl("/mylogin")
			.successHandler(customAuthenticationSuccessHandler)
		.and()		
		.logout()
			.permitAll();
		
	}

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
    }

	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(usd).passwordEncoder(bCryptPasswordEncoder());
            }

}
