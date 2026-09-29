package com.mnu.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/User/**").hasAnyRole("USER")
				.requestMatchers("/Admin/**").hasAnyRole("ADMIN")
				.anyRequest().permitAll()
				)
		.formLogin(login -> login
				.loginPage("/Join/user_login")
				.loginProcessingUrl("/")
				.usernameParameter("userid")
				.passwordParameter("passwd")
				.failureUrl("/Join/login_error")
				);
		
		return http.build();
	}
	
	//비밀번호 암호화
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
