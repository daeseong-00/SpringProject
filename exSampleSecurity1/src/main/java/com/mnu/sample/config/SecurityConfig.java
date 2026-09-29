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
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/User/**", "/Manager/**", "/Admin/**").permitAll() // 임시로 모두 허용
				.anyRequest().permitAll()	
		)
		.formLogin(login ->login
				.loginPage("/Join/user_login")
				.loginProcessingUrl("/Join/user_login")
				.usernameParameter("userid")
				.passwordParameter("passwd")
				.failureUrl("/Join/user_error")
				
		);
		
		return http.build();
	}
	
	@Bean
	//비밀번호 암호화
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
